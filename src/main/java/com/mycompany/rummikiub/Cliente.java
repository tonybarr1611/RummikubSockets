package com.mycompany.rummikiub;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.mycompany.rummikiub.ventanas.HomeWindow;
import com.mycompany.rummikiub.ventanas.LobbyWindow;

public class Cliente {
    Socket socket;
    ClienteSocketThread clienteSocketThread;
    JFrame currentWindow;
    BufferedImage img;


    public Cliente() {
        try {
            socket = new Socket("localhost", 777);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        currentWindow = new HomeWindow(socket, this);
        clienteSocketThread = new ClienteSocketThread(socket, this);
        clienteSocketThread.start();
        currentWindow.setVisible(true);
        // LobbyWindow lw = new LobbyWindow(socket);
        // lw.setVisible(true);
        img = new BufferedImage(206, 206, BufferedImage.TYPE_INT_RGB);
        img.getGraphics().drawImage(new javax.swing.ImageIcon(currentWindow.getClass().getResource("/com/mycompany/rummikiub/assets/icon.png")).getImage(), 0, 0, null);
        currentWindow.setIconImage(img);
        // detach windows so they can be closed independently
        currentWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public JFrame getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(JFrame currentWindow) {
        this.currentWindow = currentWindow;
    }

    public void crearPartida(String username, String nombrePartida, int cantidadJugadores, ArrayList<String> jugadores){
        LobbyWindow lw = new LobbyWindow(socket, username, this, username, nombrePartida, cantidadJugadores, jugadores);
        lw.setVisible(true);
        lw.setIconImage(img);
        if (jugadores.size() == 1) lw.toggleIniciar();
        lw.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        currentWindow.dispose();
        currentWindow = lw;
    }

    public ArrayList<String[]> getPartidas(){
        return clienteSocketThread.getPartidas();
    }

    public ClienteSocketThread getClienteSocketThread(){
        return clienteSocketThread;
    }

    public void sendUTF(String data){
        clienteSocketThread.sendUTF(data);
    }

    public void sendInt(int data){
        clienteSocketThread.sendInt(data);
    }
}
