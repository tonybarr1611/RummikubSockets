/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.rummikiub.ventanas;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author barra
 */
public class HomeWindow extends javax.swing.JFrame {
    private Socket socket;
    private String username;

    /**
     * Creates new form HomeWindow
     */
    public HomeWindow(Socket socket){
        this.socket = socket;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        logoImage = new javax.swing.JLabel();
        txfUsername = new javax.swing.JTextField();
        btnUsername = new javax.swing.JButton();
        btnUnirsePartida = new javax.swing.JButton();
        btnPartidaGuardada = new javax.swing.JButton();
        btnCrearPartida = new javax.swing.JButton();
        chatPanel1 = new com.mycompany.rummikiub.ventanas.ChatPanel(socket, this);
        bgImage = new javax.swing.JLabel();

        btnUnirsePartida.setEnabled(false);
        btnPartidaGuardada.setEnabled(false);
        btnCrearPartida.setEnabled(false);
        chatPanel1.disableChat();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rummikub");
        setIconImages(null);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setBackground(new java.awt.Color(255, 255, 255));
        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Nombre de usuario:");
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        logoImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/logo.png"))); // NOI18N
        getContentPane().add(logoImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        txfUsername.setBackground(new java.awt.Color(255, 255, 255, 0));
        txfUsername.setOpaque(false);
        txfUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txfUsername.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txfUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 200, -1));

        btnUsername.setBackground(new java.awt.Color(51, 51, 51));
        btnUsername.setForeground(new java.awt.Color(255, 255, 255));
        btnUsername.setText("Conectar");
        btnUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        btnUnirsePartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/UnirsePartida.png"))); // NOI18N
        getContentPane().add(btnUnirsePartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 200, 260));

        btnPartidaGuardada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/CargarPartida.png"))); // NOI18N
        getContentPane().add(btnPartidaGuardada, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 200, 260));

        btnCrearPartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/CrearPartida.png"))); // NOI18N
        btnCrearPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 200, 260));
        getContentPane().add(chatPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, -1, -1));

        bgImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/background.jpg"))); // NOI18N
        getContentPane().add(bgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ChatPanel getChatPanel(){
        return chatPanel1;
    }

    public String getUsername(){
        return username;
    }

    private void btnUsernameActionPerformed(java.awt.event.ActionEvent evt){
        username = txfUsername.getText();
        if(username.length() > 0){
            btnUnirsePartida.setEnabled(true);
            btnPartidaGuardada.setEnabled(true);
            btnCrearPartida.setEnabled(true);
            chatPanel1.enableChat();
            btnUsername.setEnabled(false);
            txfUsername.setEnabled(false);
            try {
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeUTF(username);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void btnCrearPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPartidaActionPerformed
        try {
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            salida.writeInt(0002);
            salida.writeUTF("Hola");
            salida.writeUTF("Tony");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCrearPartidaActionPerformed

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
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeWindow(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgImage;
    private javax.swing.JButton btnCrearPartida;
    private javax.swing.JButton btnPartidaGuardada;
    private javax.swing.JButton btnUnirsePartida;
    private javax.swing.JButton btnUsername;
    private com.mycompany.rummikiub.ventanas.ChatPanel chatPanel1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel logoImage;
    private javax.swing.JTextField txfUsername;
    // End of variables declaration//GEN-END:variables
}
