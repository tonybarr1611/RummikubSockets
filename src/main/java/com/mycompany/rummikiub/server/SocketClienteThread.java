package com.mycompany.rummikiub.server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

import com.mycompany.rummikiub.Partida;

public class SocketClienteThread extends Thread{
    Socket cliente;
    String username;
    Server server;
    boolean running = true;

    DataInputStream entrada;
    DataOutputStream salida;


    public SocketClienteThread(Socket cliente, Server server) {
        this.cliente = cliente;
        this.server = server;
    }

    public String getUsername(){
        return username;
    }

    public void sendUTF(String data){
        try {
            salida.writeUTF(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendInt(int data){
        try {
            salida.writeInt(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void chat_in(){
        try {
            String mensaje = entrada.readUTF();
            String autor = entrada.readUTF();
            server.broadcastInt(0001);
            server.broadcastUTF(mensaje);
            server.broadcastUTF(autor);
            System.out.println(autor + " : " + mensaje);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void crear_Part(){
        try {
            // Leer y guardar datos de la partida
            String nombrePartida = entrada.readUTF();
            int cantidadJugadores = entrada.readInt();
            server.crearPartida(username, nombrePartida, cantidadJugadores, cliente, this);

            // Enviar datos de la partida al host
            sendInt(0004);
            sendUTF(username);
            sendUTF(nombrePartida);
            sendInt(cantidadJugadores);
            sendUTF(username);
            sendUTF("9999");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void send_part(String username, String nombrePartida, int cantidadJugadores, ArrayList<String> jugadores){
        sendInt(0004);
        sendUTF(username);
        sendUTF(nombrePartida);
        sendInt(cantidadJugadores);
        for (String jugador : jugadores) {
            sendUTF(jugador);
        }
        sendUTF("9999");
        try {
            salida.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void enviar_partidas(){
        ArrayList<Partida> partidas = server.getPartidas();
        try {
            sendInt(0003);
            sendInt(partidas.size());
            for (Partida partida : partidas) {
                sendUTF(partida.getHost());
                sendUTF(partida.getNombre());
                sendInt(partida.getJugadoresActuales());
                sendInt(partida.getCantidadJugadores());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private void join_Part(){
        try {
            String indexPartida = entrada.readUTF();
            int index = Integer.parseInt(indexPartida);
            ArrayList<Partida> partidas = server.getPartidas();
            Partida partida = partidas.get(index);
            partida.addJugador(cliente, this);
            ArrayList<Socket> jugadores = partida.getJugadores();
            ArrayList<SocketClienteThread> hilosJugadores = partida.getHilosJugadores();
            for (SocketClienteThread hilo : hilosJugadores) {
                hilo.sendInt(0005);
                hilo.sendUTF(username);
            }
            send_part(partida.getHost(), partida.getNombre(), partida.getCantidadJugadores(), partida.getJugadoresNombres());
            if (jugadores.size() == partida.getCantidadJugadores()){
                server.iniciarPartida(partida.getNombre(), partida.getHost());
            }
        } catch (Exception e) {
        }
    }

    private void sendMove(){
        try {   
            String pos = entrada.readUTF();
            String ficha = entrada.readUTF();
            String isComodin = entrada.readUTF();
            ArrayList<Partida> partidas = server.getPartidas();
            for (Partida partida : partidas) {
                if (partida.getJugadores().contains(cliente)){
                    for (Socket jugador : partida.getJugadores()) {
                            SocketClienteThread hilo = partida.getHilosJugadores().get(partida.getJugadores().indexOf(jugador));
                            hilo.sendInt(9);
                            hilo.sendUTF(pos);
                            hilo.sendUTF(ficha);
                            hilo.sendUTF(isComodin);
                    }
                    partida.registrarMovimiento(pos, ficha, isComodin);
                }
            }
        } catch (Exception e) {
        }
    }

    public void terminarTurno(){
        ArrayList<Partida> partidas = server.getPartidas();
        for (Partida partida : partidas) {
            if (partida.getJugadores().contains(cliente)){
                partida.terminarTurno();
            }
        }
    }

    private void sumaCarta(){
        try {
            ArrayList<Partida> partidas = server.getPartidas();
            for (Partida partida : partidas) {
                if (partida.getJugadores().contains(cliente)){
                    partida.skipTurno();
                }
            }
        } catch (Exception e) {
        }
    }
    

    @Override
    public void run() {
        
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            
        } catch (IOException e) {
           System.out.println("Error al crear los streams de entrada y salida : " + e.getMessage());
           running = false;
        }
        try {
            username = entrada.readUTF();
            System.out.println("El cliente " + username + " se ha conectado");
        } catch (IOException e) {
            System.out.println("Error al leer el nombre del cliente : " + e.getMessage());
        }
        while(running){
            renovarStreams();
            try {
                // TODO: implementar protocolo de comunicacion
                int opCode = entrada.readInt();
                System.out.println(opCode);
                switch (opCode) {
                    case 0002:
                        chat_in();
                        break;
                    case 0003:
                        enviar_partidas();
                        break;
                    case 0004:
                        crear_Part();
                        break;
                    case 8:
                        join_Part();
                        break;
                    case 9:
                        sendMove();
                        break;
                    case 1:
                        System.out.println("recibido 1");
                        salida.writeInt(2);
                        break;
                    case 88:
                        terminarTurno();
                        break;
                    case 91:
                        sumaCarta();
                        break;
                    default:
                        System.out.println("Codigo de operacion no reconocido");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error al leer el mensaje del cliente : " + e.getMessage());
                running = false;
            }
        }
    }

    private void renovarStreams(){
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
           System.out.println("Error al crear los streams de entrada y salida : " + e.getMessage());
           running = false;
        }
    }

    public void flushOutput(){
        try {
            salida.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
