/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.rummikiub.ventanas;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.mycompany.rummikiub.Cliente;
import com.mycompany.rummikiub.Partida;

/**
 *
 * @author barra
 */
public class LobbyWindow extends javax.swing.JFrame {
    private Socket socket;
    private String username;
    private Cliente cliente;
    private String host;
    private String nombrePartida;
    private int cantidadJugadores;
    private ArrayList<String> jugadores;
    /**
     * Creates new form LobbyWindow
     */
    public LobbyWindow(Socket socket, String username, Cliente cliente, String host, String nombrePartida, int cantidadJugadores, ArrayList<String> jugadores) {
        this.socket = socket;
        this.username = username;
        this.cliente = cliente;
        this.host = host;
        this.nombrePartida = nombrePartida;
        this.cantidadJugadores = cantidadJugadores;
        this.jugadores = jugadores;
        initComponents();
        setComponentsStatus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoImage = new javax.swing.JLabel();
        lblSalaEspera = new javax.swing.JLabel();
        lblJugadores = new javax.swing.JLabel();
        lblJugador1 = new javax.swing.JLabel();
        btnJugador1 = new javax.swing.JButton();
        btnJugador2 = new javax.swing.JButton();
        lblJugador2 = new javax.swing.JLabel();
        lblJugador3 = new javax.swing.JLabel();
        btnJugador3 = new javax.swing.JButton();
        lblJugador4 = new javax.swing.JLabel();
        btnJugador4 = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        chatPanel1 = new com.mycompany.rummikiub.ventanas.ChatPanel(socket, this);
        bgImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sala de espera");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/logo.png"))); // NOI18N
        getContentPane().add(logoImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, -1, -1));

        lblSalaEspera.setBackground(new java.awt.Color(255, 255, 255));
        lblSalaEspera.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        lblSalaEspera.setForeground(new java.awt.Color(255, 255, 255));
        lblSalaEspera.setText("Sala de espera");
        getContentPane().add(lblSalaEspera, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lblJugadores.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lblJugadores.setForeground(new java.awt.Color(255, 255, 255));
        lblJugadores.setText("Jugadores");
        getContentPane().add(lblJugadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        lblJugador1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblJugador1.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador1.setText("Jugador 1");
        getContentPane().add(lblJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        btnJugador1.setBackground(new java.awt.Color(51, 51, 51));
        btnJugador1.setForeground(new java.awt.Color(255, 255, 255));
        btnJugador1.setText("Kick");
        getContentPane().add(btnJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));
        btnJugador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kick_player(0);
            }
        });

        btnJugador2.setBackground(new java.awt.Color(51, 51, 51));
        btnJugador2.setForeground(new java.awt.Color(255, 255, 255));
        btnJugador2.setText("Kick");
        getContentPane().add(btnJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));
        btnJugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kick_player(1);
            }
        });

        lblJugador2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblJugador2.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador2.setText("Jugador 2");
        getContentPane().add(lblJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        lblJugador3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblJugador3.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador3.setText("Jugador 3");
        getContentPane().add(lblJugador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        btnJugador3.setBackground(new java.awt.Color(51, 51, 51));
        btnJugador3.setForeground(new java.awt.Color(255, 255, 255));
        btnJugador3.setText("Kick");
        getContentPane().add(btnJugador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));
        btnJugador3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kick_player(2);
            }
        });

        lblJugador4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblJugador4.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador4.setText("Jugador 4");
        getContentPane().add(lblJugador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        btnJugador4.setBackground(new java.awt.Color(51, 51, 51));
        btnJugador4.setForeground(new java.awt.Color(255, 255, 255));
        btnJugador4.setText("Kick");
        getContentPane().add(btnJugador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));
        btnJugador4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kick_player(3);
            }
        });

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/Cerrar.png"))); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 120, 170));

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/Iniciar.png"))); // NOI18N
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 120, 170));
        getContentPane().add(chatPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, -1, -1));

        bgImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/background.jpg"))); // NOI18N
        getContentPane().add(bgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void kick_player(int index){
        cliente.getClienteSocketThread().sendInt(66);
        cliente.getClienteSocketThread().sendUTF(index + "");
    }

    public ChatPanel getChatPanel(){
        return chatPanel1;
    }

    public void toggleIniciar(){
        btnIniciar.setEnabled(true);
    }

    public String getUsername(){
        return username;
    }

    public int getCantidadJugadores(){
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores){
        this.cantidadJugadores = cantidadJugadores;
    }

    public ArrayList<String> getJugadores(){
        return jugadores;
    }

    public void setJugadores(ArrayList<String> jugadores){
        this.jugadores = jugadores;
        setComponentsStatus();
    }

    public void addJugador(String jugador){
        jugadores.add(jugador);
        setComponentsStatus();
    }

    private void setComponentsStatus(){
        JLabel[] jugadoresLabels = {lblJugador1, lblJugador2, lblJugador3, lblJugador4};
        JButton[] jugadoresButtons = {btnJugador1, btnJugador2, btnJugador3, btnJugador4};
        lblJugadores.setText("Jugadores (" + jugadores.size() + "/"  + cantidadJugadores + ")");
        for (int i = 0; i < 4; i++){
            jugadoresLabels[i].setVisible(false);
            jugadoresButtons[i].setVisible(false);
        }
        for (int i = 0; i < jugadores.size(); i++){
            jugadoresLabels[i].setText(jugadores.get(i));
            jugadoresLabels[i].setVisible(true);
            jugadoresButtons[i].setVisible(true);
        }
        if (host.equals(username)){
            btnIniciar.setEnabled(true);
            btnCerrar.setEnabled(true);
        }else{
            btnIniciar.setEnabled(false);
            btnCerrar.setEnabled(false);
        }        
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if (cantidadJugadores >= 2){
            cliente.getClienteSocketThread().sendInt(7);
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LobbyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LobbyWindow(null, "", null, "", "", 0, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgImage;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnJugador1;
    private javax.swing.JButton btnJugador2;
    private javax.swing.JButton btnJugador3;
    private javax.swing.JButton btnJugador4;
    private com.mycompany.rummikiub.ventanas.ChatPanel chatPanel1;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblJugador3;
    private javax.swing.JLabel lblJugador4;
    private javax.swing.JLabel lblJugadores;
    private javax.swing.JLabel lblSalaEspera;
    private javax.swing.JLabel logoImage;
    // End of variables declaration//GEN-END:variables
}
