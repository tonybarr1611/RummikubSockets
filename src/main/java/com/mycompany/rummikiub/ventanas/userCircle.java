/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.rummikiub.ventanas;

/**
 *
 * @author barra
 */
public class userCircle extends javax.swing.JPanel {

    /**
     * Creates new form userCircle
     */
    public userCircle() {
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

        lblName = new javax.swing.JLabel();
        lblCantidadFichas = new javax.swing.JLabel();
        lblMarco = new javax.swing.JLabel();

        setLayout(null);

        lblName.setBackground(new java.awt.Color(255, 255, 255));
        lblName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(0, 0, 0));
        lblName.setText("name");
        add(lblName);
        lblName.setBounds(30, 40, 37, 16);

        lblCantidadFichas.setText("n fichas");
        add(lblCantidadFichas);
        lblCantidadFichas.setBounds(30, 100, 100, 30);

        lblMarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/rummikiub/assets/marco.png"))); // NOI18N
        add(lblMarco);
        lblMarco.setBounds(0, 0, 100, 100);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCantidadFichas;
    private javax.swing.JLabel lblMarco;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}
