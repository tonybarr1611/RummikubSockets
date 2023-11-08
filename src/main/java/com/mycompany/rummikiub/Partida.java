package com.mycompany.rummikiub;

import java.net.Socket;
import java.util.ArrayList;

import com.mycompany.rummikiub.server.SocketClienteThread;

public class Partida {
    private String nombre;
    private int cantidadJugadores;
    private String host;
    private Socket hostSocket;
    private ArrayList<Socket> jugadores;
    private ArrayList<SocketClienteThread> hilosJugadores;
    
    private int[][] matriz;
    
    public Partida(){
        jugadores = new ArrayList<Socket>();
        hilosJugadores = new ArrayList<SocketClienteThread>();
    }

    public Partida(String username, String nombrePartida, int cantidadJugadores, Socket cliente, SocketClienteThread hilo){
        this.nombre = nombrePartida;
        this.cantidadJugadores = cantidadJugadores;
        this.host = username;
        this.hostSocket = cliente;
        jugadores = new ArrayList<Socket>();
        jugadores.add(cliente);
        hilosJugadores = new ArrayList<SocketClienteThread>();
        hilosJugadores.add(hilo);
    }
}
