package com.mycompany.rummikiub.ventanas;

import javax.swing.JButton;

public class Ficha extends JButton{
    private int numero;
    private String color;
    private boolean comodin;
    private String icon_path;

    public Ficha(int numero, String color, boolean comodin, int tipo){
        // Crea un boton ficha que recibe numero y color, si es comodin o no y el tipo de ficha (tablero (0) o mazo(1)). Si el numero es -1, es una ficha vacia, entonces se pone el icono del tablero
        super();
        this.numero = numero;
        this.color = color;
        this.comodin = comodin;
        if (tipo == 0)
            super.setSize(1*35, (int)2*35);
        else
            super.setSize(1*20, (int)1.5*20);
        this.icon_path = "/com/mycompany/rummikiub/assets/fichas/" + color + "/" + numero + ".png";
        if (numero == -1)
            this.icon_path = "/com/mycompany/rummikiub/assets/fichas/tablero.png";
        System.out.println(icon_path);
        super.setIcon(new javax.swing.ImageIcon(getClass().getResource(icon_path)));
    }

    public int getNumero(){
        return numero;
    }

    public String getColor(){
        return color;
    }

    public boolean getComodin(){
        return comodin;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setComodin(boolean comodin){
        this.comodin = comodin;
    }
}
