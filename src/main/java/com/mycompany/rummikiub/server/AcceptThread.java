package com.mycompany.rummikiub.server;

import java.net.*;
import java.util.ArrayList;

public class AcceptThread extends Thread{
    private Server server;
    private ServerSocket serverSocket;
    private ArrayList<Socket> clientes;
    private ArrayList<SocketClienteThread> hilosClientes;
    private boolean running = true;
    
    public AcceptThread(Server server, ServerSocket serverSocket, ArrayList<Socket> clientes, ArrayList<SocketClienteThread> hilosClientes){
        this.server = server;
        this.serverSocket = serverSocket;
        this.clientes = clientes;
        this.hilosClientes = hilosClientes;
    }

    @Override
    public void run() {
        while(running){
            System.out.println("AcceptThread started");
            try {
                Socket cliente = serverSocket.accept();
                clientes.add(cliente);
                SocketClienteThread hiloCliente = new SocketClienteThread(cliente, server);
                hilosClientes.add(hiloCliente);
                hiloCliente.start();
            } catch (Exception e) {
                System.out.println("Error al aceptar cliente");
            }
        }
    }
}
