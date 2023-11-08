package com.mycompany.rummikiub;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.mycompany.rummikiub.ventanas.HomeWindow;
import com.mycompany.rummikiub.ventanas.LobbyWindow;

public class ClienteSocketThread extends Thread{
    Socket cliente;
    String username;
    Cliente clienteApp;
    DataInputStream entrada = null;
    boolean running = true;

    public ClienteSocketThread(Socket cliente, Cliente clienteApp) {
        this.cliente = cliente;
        this.clienteApp = clienteApp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void chat_in(){
        try {
            String mensaje = entrada.readUTF();
            String autor = entrada.readUTF();
            if (clienteApp.getCurrentWindow() instanceof LobbyWindow) {
                ((LobbyWindow) clienteApp.getCurrentWindow()).getChatPanel().chat_in(mensaje, autor);
            } else if (clienteApp.getCurrentWindow() instanceof HomeWindow) {
                ((HomeWindow) clienteApp.getCurrentWindow()).getChatPanel().chat_in(mensaje, autor);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void crear_Part(){
        try {
            String host = entrada.readUTF();
            String nombrePartida = entrada.readUTF();
            int cantidadJugadores = entrada.readInt();
            clienteApp.crearPartida(host, nombrePartida, cantidadJugadores);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            entrada = new DataInputStream(cliente.getInputStream());
        } catch (Exception e) {
            System.out.println("Error al crear los streams de entrada y salida : " + e.getMessage());
            running = false;
        }
        while (running) {
            try {
                int opCode = entrada.readInt();
                switch (opCode) {
                    case 0001:
                        chat_in();
                        break;
                    case 0004:
                        crear_Part();
                        break;
                    default:
                        System.out.println("Codigo de operacion no reconocido: " + opCode);
                        break;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        super.run();
    }
}
