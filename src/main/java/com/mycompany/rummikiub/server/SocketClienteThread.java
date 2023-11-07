package com.mycompany.rummikiub.server;

import java.io.*;
import java.net.Socket;

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

    public void chat_in(){
        try {
            String mensaje = entrada.readUTF();
            String autor = entrada.readUTF();
            System.out.println(autor + " : " + mensaje);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            try {
                // TODO: implementar protocolo de comunicacion
                int opCode = entrada.readInt();
                switch (opCode) {
                    case 0002:
                        chat_in();
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
}
