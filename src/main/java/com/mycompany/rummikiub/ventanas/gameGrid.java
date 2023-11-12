/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.rummikiub.ventanas;

/**
 *
 * @author barra
 */
public class gameGrid extends javax.swing.JPanel {
    private Ficha[][] fichas  = new Ficha[6][18];
    /**
     * Creates new form gameGrid
     */
    public gameGrid() {
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
        createGrid();
        setLayout(new java.awt.GridLayout(6, 18));
    }// </editor-fold>//GEN-END:initComponents
    
    public void createGrid(){
        String colors[] = {"azul", "naranja", "negro", "rojo"};
        for (int i = 0; i < 6; i++){
            int t = i;
            if (i == 0 || i > 3) t = 0;
            for (int j = 0; j < 18; j++){
                int p = j;
                if (j == 0 || j > 12) p = 1;
                Ficha ficha = new Ficha(p, colors[t], false, 0);
                fichas[i][j] = ficha;
                this.add(fichas[i][j]);
                fichas[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        System.out.println("Ficha clickeada " + ficha.getColor());
                    }
                });
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
