package com.mycompany.rummikiub;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


import com.mycompany.rummikiub.ventanas.HomeWindow;
import com.mycompany.rummikiub.ventanas.LobbyWindow;

public class ClienteSocketThread extends Thread{
    Socket cliente;
    String username;
    Cliente clienteApp;
    DataInputStream entrada = null;
    DataOutputStream salida = null;
    boolean running = true;
    private boolean pause = false;

    public ClienteSocketThread(Socket cliente, Cliente clienteApp) {
        this.cliente = cliente;
        this.clienteApp = clienteApp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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
            ArrayList<String> jugadores = new ArrayList<String>();
            for (int i = 0; i < cantidadJugadores + 1; i++) {
                String jugador = entrada.readUTF();
                if (jugador.equals("9999"))
                    break;
                jugadores.add(jugador);
            }
            if (jugadores.size() == 1)
                clienteApp.crearPartida(host, nombrePartida, cantidadJugadores, jugadores);
            else
                clienteApp.crearPartida(username, nombrePartida, cantidadJugadores, jugadores);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getPartidas(){
        ArrayList<String[]> partidas = new ArrayList<String[]>();
        try {
            pause = true;
            salida.writeInt(0003);
            int cantidadPartidas = entrada.readInt();
            for (int i = 0; i < cantidadPartidas; i++) {
                String[] partida = new String[4];
                partida[0] = entrada.readUTF(); // host
                partida[1] = entrada.readUTF(); // nombre
                partida[2] = entrada.readInt() + ""; // cantidad actual
                partida[3] = entrada.readInt() + ""; // cantidad total
                partidas.add(partida);
            }
            pause = false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return partidas;
    }

    public void sendUTF(String data){
        try {
            salida.writeUTF(data);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void sendInt(int data){
        try {
            salida.writeInt(data);
        } catch (Exception e) {
        }
    }

    private void addPlayerLobby(){
        try {
            String jugador = entrada.readUTF();
            if (clienteApp.getCurrentWindow() instanceof LobbyWindow) {
                ((LobbyWindow) clienteApp.getCurrentWindow()).addJugador(jugador);
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void run() {
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error al crear los streams de entrada y salida : " + e.getMessage());
            running = false;
        }
        while (running) {
            if (!pause){
                try {
                    int opCode = entrada.readInt();
                    switch (opCode) {
                        case 0001:
                            chat_in();
                            break;
                        case 0003:
                            break;
                        case 0004:
                            crear_Part();
                            break;
                        case 0005:
                            addPlayerLobby();
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
        }
        super.run();
    }
}
