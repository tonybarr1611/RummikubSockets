package com.mycompany.rummikiub;

import java.net.Socket;
import java.util.ArrayList;

import com.mycompany.rummikiub.server.SocketClienteThread;

public class Partida {
    private String nombre;
    private int cantidadJugadores;
    private String host;
    private Socket hostSocket;
    private ArrayList<String> jugadoresNombres;
    private ArrayList<Socket> jugadores;
    private ArrayList<SocketClienteThread> hilosJugadores;
    
    private String[][] matriz = new String[6][18];
    private String[][] matrizBackup = new String[6][18];
    private ArrayList<String> mazo;

    private int turno = 0;
    
    public Partida(){
        jugadores = new ArrayList<Socket>();
        jugadoresNombres = new ArrayList<String>();
        hilosJugadores = new ArrayList<SocketClienteThread>();
        mazo = new ArrayList<String>();
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 18; j++){
                matriz[i][j] = "tablero-1";
                matrizBackup[i][j] = "tablero-1";
            }
        }
        String colores[] = {"rojo", "azul", "nara", "negr"};
        for (int i = 0; i < 4; i++){
            for (int j = 1; j < 14; j++){
                mazo.add(colores[i] + j);
                mazo.add(colores[i] + j);
            }
        }
        mazo.add("como");
        mazo.add("como");
    }

    public Partida(String username, String nombrePartida, int cantidadJugadores, Socket cliente, SocketClienteThread hilo){
        this.nombre = nombrePartida;
        this.cantidadJugadores = cantidadJugadores;
        this.host = username;
        this.hostSocket = cliente;
        jugadores = new ArrayList<Socket>();
        jugadores.add(cliente);
        jugadoresNombres = new ArrayList<String>();
        jugadoresNombres.add(username);
        hilosJugadores = new ArrayList<SocketClienteThread>();
        hilosJugadores.add(hilo);
        mazo = new ArrayList<String>();
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 18; j++){
                matriz[i][j] = "tablero-1";
                matrizBackup[i][j] = "tablero-1";
            }
        }
        String colores[] = {"rojo", "azul", "nara", "negr"};
        for (int i = 0; i < 4; i++){
            for (int j = 1; j < 14; j++){
                mazo.add(colores[i] + j);
                mazo.add(colores[i] + j);
            }
        }
        mazo.add("como");
        mazo.add("como");
    }

    public void repartirCartas(SocketClienteThread hilo){
        for (int i = 0; i < 14; i++){
            int random = (int) (Math.random() * mazo.size());
            System.out.println(mazo.get(random));
            hilo.sendUTF(mazo.get(random));
            mazo.remove(random);
        }
    }

    public void addJugador(Socket cliente, SocketClienteThread hilo){
        jugadores.add(cliente);
        jugadoresNombres.add(hilo.getUsername());
        hilosJugadores.add(hilo);
    }

    public void sendTurnos(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 18; j++){
                matrizBackup[i][j] = matriz[i][j];
            }
        }
        for (int i = 0; i < hilosJugadores.size(); i++){
            if (i != turno){ // no host
                hilosJugadores.get(i).sendInt(15);
                hilosJugadores.get(i).sendUTF(turno + "");
            }else{ // host
                hilosJugadores.get(i).sendInt(16);
            }
        }
        if (turno == hilosJugadores.size() - 1)
            turno = 0;
        else
            turno++;
    }

    public void terminarTurno(){
        if (checkMatrix()){
            sendTurnos();
        }else{
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 18; j++){
                    matriz[i][j] = matrizBackup[i][j];
                    for(int k = 0; k < hilosJugadores.size(); k++){
                        hilosJugadores.get(k).sendInt(9);
                        hilosJugadores.get(k).sendUTF(i + " " + j);
                        hilosJugadores.get(k).sendUTF(matriz[i][j]);
                        if (matriz[i][j].contains("como"))
                            hilosJugadores.get(k).sendUTF("1");
                        else
                            hilosJugadores.get(k).sendUTF("0");
                        }
                    }
                }
            for (SocketClienteThread hilo : hilosJugadores) {
                hilo.sendInt(92);
            }
            sendTurnos();
        }
    }

    public void registrarMovimiento(String pos, String ficha, String isComodin){
        int j = Integer.parseInt((pos.split(" "))[0]);
        int k = Integer.parseInt((pos.split(" "))[1]);
        if (isComodin.equals("1"))
            matriz[j][k] = "como";
        else{
            matriz[j][k] = ficha;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public String getHost() {
        return host;
    }

    public Socket getHostSocket() {
        return hostSocket;
    }

    public ArrayList<Socket> getJugadores() {
        return jugadores;
    }

    public ArrayList<String> getJugadoresNombres() {
        return jugadoresNombres;
    }

    public ArrayList<SocketClienteThread> getHilosJugadores() {
        return hilosJugadores;
    }

    public int getJugadoresActuales(){
        return jugadores.size();
    }

    public boolean checkMatrix(){
        boolean check = true;
        int cont = 0;
        int numero;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 18; j++){
                if (matriz[i][j].contains("tab"))
                    continue;
                if (matriz[i][j].contains("como"))
                    continue;
                if (matriz[i][j].contains("negr")){
                    cont = 1;
                    numero = Integer.parseInt(matriz[i][j].substring(4));
                    if (matriz[i][j+1].substring(4).equals((numero+1) + "")){
                        for (int k = j + 1; k < 18; k++){
                            if ((matriz[i][k].contains("negr") && matriz[i][k].equals((numero+1) + "")) || matriz[i][k].contains("como")){
                                cont++;
                                numero++;
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Negras: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else if(matriz[i][j+1].substring(4).equals(numero + "") && !(matriz[i][j+1].contains("negr"))){
                        ArrayList<String> colores = new ArrayList<String>();
                        colores.add("azul");
                        colores.add("rojo");
                        colores.add("nara");
                        for (int k = j + 1; k < 18; k++){
                            if (matriz[i][k].contains("como") || (!(matriz[i][k].contains("tab")) && matriz[i][k].substring(4).equals((numero) + ""))){
                                for(String color : colores){
                                    if (matriz[i][k].contains(color)){
                                        cont++;
                                        colores.remove(color);
                                        break;
                                    }
                                }
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Negras: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else{
                        check = false;
                        return check;
                    }
                }if (matriz[i][j].contains("rojo")){
                    cont = 1;
                    numero = Integer.parseInt(matriz[i][j].substring(4));
                    if (matriz[i][j+1].substring(4).equals((numero+1) + "")){
                        for (int k = j + 1; k < 18; k++){
                            if ((matriz[i][k].contains("rojo") && matriz[i][k].equals((numero+1) + "")) || matriz[i][k].contains("como")){
                                cont++;
                                numero++;
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Rojas: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else if(matriz[i][j+1].substring(4).equals(numero + "") && !(matriz[i][j+1].contains("rojo"))){
                        ArrayList<String> colores = new ArrayList<String>();
                        colores.add("azul");
                        colores.add("negr");
                        colores.add("nara");
                        for (int k = j + 1; k < 18; k++){
                            if (matriz[i][k].contains("como") || (!(matriz[i][k].contains("tab")) && matriz[i][k].substring(4).equals((numero) + ""))){
                                for(String color : colores){
                                    if (matriz[i][k].contains(color)){
                                        cont++;
                                        colores.remove(color);
                                        break;
                                    }
                                }
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Rojas: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else{
                        check = false;
                        return check;
                    }
                }if (matriz[i][j].contains("azul")){
                    cont = 1;
                    numero = Integer.parseInt(matriz[i][j].substring(4));
                    if (matriz[i][j+1].substring(4).equals((numero+1) + "")){
                        for (int k = j + 1; k < 18; k++){
                            if ((matriz[i][k].contains("azul") && matriz[i][k].equals((numero+1) + "")) || matriz[i][k].contains("como")){
                                cont++;
                                numero++;
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Azules: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else if(matriz[i][j+1].substring(4).equals(numero + "") && !(matriz[i][j+1].contains("azul"))){
                        ArrayList<String> colores = new ArrayList<String>();
                        colores.add("rojo");
                        colores.add("negr");
                        colores.add("nara");
                        for (int k = j + 1; k < 18; k++){
                            if (matriz[i][k].contains("como") || (!(matriz[i][k].contains("tab")) && matriz[i][k].substring(4).equals((numero) + ""))){
                                for(String color : colores){
                                    if (matriz[i][k].contains(color)){
                                        cont++;
                                        colores.remove(color);
                                        break;
                                    }
                                }
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Rojas: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else{
                        check = false;
                        return check;
                    }
                }if (matriz[i][j].contains("nara")){
                    cont = 1;
                    numero = Integer.parseInt(matriz[i][j].substring(4));
                    if (matriz[i][j+1].substring(4).equals((numero+1) + "")){
                        for (int k = j + 1; k < 18; k++){
                            if ((matriz[i][k].contains("nara") && matriz[i][k].equals((numero+1) + "")) || matriz[i][k].contains("como")){
                                cont++;
                                numero++;
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Rojas: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else if(matriz[i][j+1].substring(4).equals(numero + "") && !(matriz[i][j+1].contains("nara"))){
                        ArrayList<String> colores = new ArrayList<String>();
                        colores.add("rojo");
                        colores.add("negr");
                        colores.add("nara");
                        for (int k = j + 1; k < 18; k++){
                            if (matriz[i][k].contains("como") || (!(matriz[i][k].contains("tab")) && matriz[i][k].substring(4).equals((numero) + ""))){
                                for(String color : colores){
                                    if (matriz[i][k].contains(color)){
                                        cont++;
                                        colores.remove(color);
                                        break;
                                    }
                                }
                            }else{
                                if (cont < 3)
                                    check = false;
                                System.out.println("Rojas: " + cont);
                            }
                        }
                        if (cont < 3){
                            check = false;
                            return check;
                        }
                        j += cont - 1;
                        continue;
                    }else{
                        check = false;
                        return check;
                    }
                }
            }
        }
        return check;
    }
}