/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.rummikiub.ventanas;

import java.util.ArrayList;

/**
 *
 * @author barra
 */
public class MazoJugador extends javax.swing.JPanel {
    ArrayList<Ficha> fichas = new ArrayList<Ficha>();
    /**
     * Creates new form MazoJugador
     */
    public MazoJugador() {
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

        setLayout(new java.awt.GridLayout(2, 10));
    }// </editor-fold>//GEN-END:initComponents

    public void addFicha(String codigoFicha){
        Ficha ficha;
        if (codigoFicha.equals("como")) ficha = new Ficha(0, "comodin", true, 0, -1, -1);
        else ficha = new Ficha(Integer.parseInt(codigoFicha.substring(4)), codigoFicha.substring(0, 4), false, 0, -1, -1);
        fichas.add(ficha);
        this.add(ficha);
        this.revalidate();
    }

    public ArrayList<Ficha> getFichas(){
        return fichas;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}