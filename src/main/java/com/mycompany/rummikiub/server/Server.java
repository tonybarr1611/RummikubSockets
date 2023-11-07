/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rummikiub.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author barra
 */
public class Server {
    private ServerSocket serverSocket;
    private AcceptThread acceptThread;
    private ArrayList<Socket> clientes;
    private ArrayList<SocketClienteThread> hilosClientes;

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

}
