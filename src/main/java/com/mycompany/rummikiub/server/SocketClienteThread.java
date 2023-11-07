package com.mycompany.rummikiub.server;

import java.io.*;
import java.net.Socket;

public class SocketClienteThread extends Thread{
    Socket cliente;
    Server server;
    boolean running = true;

    DataInputStream entrada;
    DataOutputStream salida;


    public SocketClienteThread(Socket cliente, Server server) {
        this.cliente = cliente;
        this.server = server;
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
            System.out.println(entrada.readUTF());
        } catch (IOException e) {
            System.out.println("Error al leer el nombre del cliente : " + e.getMessage());
        }
        while(running){
            try {
                // TODO: implementar protocolo de comunicacion
                int mensaje = entrada.readInt();
                System.out.println(mensaje);
                salida.writeUTF("Hola cliente");
            } catch (IOException e) {
                System.out.println("Error al leer el mensaje del cliente : " + e.getMessage());
                running = false;
            }
        }
    }
}
