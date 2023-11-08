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
    
    public Partida(){
        jugadores = new ArrayList<Socket>();
        jugadoresNombres = new ArrayList<String>();
        hilosJugadores = new ArrayList<SocketClienteThread>();
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
