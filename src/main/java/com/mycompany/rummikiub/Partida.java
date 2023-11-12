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
    
    private int[][] matriz;
    private ArrayList<String> mazo;
    
    public Partida(){
        jugadores = new ArrayList<Socket>();
        jugadoresNombres = new ArrayList<String>();
        hilosJugadores = new ArrayList<SocketClienteThread>();
        mazo = new ArrayList<String>();
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
}
