/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.rummikiub;
import com.mycompany.rummikiub.ventanas.LobbyWindow;
import com.mycompany.rummikiub.server.Server;
import com.mycompany.rummikiub.ventanas.HomeWindow;
import java.awt.image.*;

/**
 *
 * @author barra
 */
public class Rummikiub {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Server s = new Server();
        HomeWindow hw = new HomeWindow();
        hw.setVisible(true);
        LobbyWindow lw = new LobbyWindow();
        lw.setVisible(true);
        BufferedImage img = new BufferedImage(206, 206, BufferedImage.TYPE_INT_RGB);
        img.getGraphics().drawImage(new javax.swing.ImageIcon(hw.getClass().getResource("/com/mycompany/rummikiub/assets/icon.png")).getImage(), 0, 0, null);
        hw.setIconImage(img);
        lw.setIconImage(img);
        // detach windows so they can be closed independently
        hw.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        lw.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}