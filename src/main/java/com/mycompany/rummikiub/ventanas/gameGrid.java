/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.rummikiub.ventanas;

import com.mycompany.rummikiub.Cliente;

/**
 *
 * @author barra
 */
public class GameGrid extends javax.swing.JPanel {
    private Ficha[][] fichas  = new Ficha[6][18];
    private GameGUI gameGUI;
    /**
     * Creates new form gameGrid
     */
    public GameGrid(String matrizJuego[][], GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        initComponents();
        createGrid(matrizJuego);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new java.awt.GridLayout(6, 18));
    }// </editor-fold>//GEN-END:initComponents
    
    public Ficha[][] getFichas(){
        return fichas;
    }

    public void createGrid(String matrizJuego[][]){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 18; j++){
                if (matrizJuego[i][j] == "T")
                    fichas[i][j] = new Ficha(-1, "tablero", false, 0, i, j);
                else{
                    if (matrizJuego[i][j] == "como")
                        fichas[i][j] = new Ficha(0, "comodin", true, 0, i, j);
                    else
                        fichas[i][j] = new Ficha(Integer.parseInt(matrizJuego[i][j].substring(4)), matrizJuego[i][j].substring(0, 4), false, 0, i, j);
                }
                this.add(fichas[i][j]);
                fichas[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (gameGUI.getTurno()){
                            if (gameGUI.getCurrentFicha() != null && (Ficha)evt.getSource() != gameGUI.getCurrentFicha() && (((Ficha)evt.getSource()).getColor().equals("tablero") || gameGUI.getCurrentFichaPos())){
                                Cliente cliente = gameGUI.getClienteApp();
                                Ficha ficha = (Ficha) evt.getSource();
                                String comodin = "0";
                                if (gameGUI.getCurrentFicha().getComodin()) comodin = "1";
                                if (gameGUI.getCurrentFichaPos()){
                                    cliente.sendInt(9); // opcode
                                    cliente.sendUTF(gameGUI.getCurrentFicha().getI() + " " + gameGUI.getCurrentFicha().getJ()); // pos
                                    cliente.sendUTF(ficha.getColor() + "" + ficha.getNumero()); // ficha
                                    cliente.sendUTF(comodin); // isComodin
                                }
                                comodin = "0";
                                if (ficha.getComodin()) comodin = "1";
                                cliente.sendInt(9); // opcode
                                cliente.sendUTF(ficha.getI() + " " + ficha.getJ()); // pos
                                cliente.sendUTF(gameGUI.getCurrentFicha().getColor() + "" + gameGUI.getCurrentFicha().getNumero()); // ficha
                                cliente.sendUTF(comodin); // isComodin
                                // Ficha temp = new Ficha();
                                // temp.morph(ficha);
                                // ficha.morph(gameGUI.getCurrentFicha());
                                // gameGUI.getCurrentFicha().morph(temp);
                                if (!gameGUI.getCurrentFichaPos()) gameGUI.removeFicha();
                                gameGUI.switchHasMoved();
                                gameGUI.setCurrentFicha(null);
                                gameGUI.setCurrentFichaPos(false);
                            }else{
                                gameGUI.setCurrentFicha((Ficha) evt.getSource());
                                gameGUI.setCurrentFichaPos(true);
                            }
                        }
                    }
                });
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
