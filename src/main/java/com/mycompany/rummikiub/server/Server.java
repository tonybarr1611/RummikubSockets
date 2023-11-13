/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rummikiub.server;

import java.io.DataOutput;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import com.mycompany.rummikiub.Partida;

/**
 *
 * @author barra
 */
public class Server {
    private ServerSocket serverSocket;
    private AcceptThread acceptThread;
    private ArrayList<Socket> clientes;
    private ArrayList<SocketClienteThread> hilosClientes;

    private ArrayList<Partida> partidas;

    public Server(){
        try {
            serverSocket = new ServerSocket(777);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el puerto 777");
        }
        clientes = new ArrayList<Socket>();
        hilosClientes = new ArrayList<SocketClienteThread>();
        acceptThread = new AcceptThread(this, serverSocket, clientes, hilosClientes);
        acceptThread.start();
        partidas = new ArrayList<Partida>();
    }

    public void broadcastUTF(String data){
        for (SocketClienteThread hilo : hilosClientes) {
            hilo.sendUTF(data);
        }
    }

    public void broadcastInt(int data){
        for (SocketClienteThread hilo : hilosClientes) {
            hilo.sendInt(data);
        }
    }

    public void crearPartida(String username, String nombrePartida, int cantidadJugadores, Socket cliente, SocketClienteThread hilo){
        Partida partida = new Partida(username, nombrePartida, cantidadJugadores, cliente, hilo);
        partidas.add(partida);
        System.out.println("Partida creada: " + nombrePartida + " de " + username + " para " + cantidadJugadores + " jugadores");
    }

    public void iniciarPartida(String nombrePartida, String host){
        for (Partida partida : partidas) {
            if (partida.getNombre().equals(nombrePartida) && partida.getHost().equals(host)){
                for (SocketClienteThread hilo : partida.getHilosJugadores()) {
                    hilo.sendInt(0007);
                    partida.repartirCartas(hilo);
                }
                partida.sendTurnos();
            }
        }

    }

    public ArrayList<Partida> getPartidas(){
        return partidas;
    }

}
