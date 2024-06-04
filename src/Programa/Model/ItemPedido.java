package Programa.Model;

import java.io.Serializable;

public class ItemPedido implements Serializable{
    private int idAutoparte;
    private int cantidad;

    // Constructor
    public ItemPedido(int idAutoparte, int cantidad) {
        this.idAutoparte = idAutoparte;
        this.cantidad = cantidad;
    }

    // Getters
    public int getIdAutoparte() {
        return idAutoparte;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setters
    public void setIdAutoparte(int idAutoparte) {
        this.idAutoparte = idAutoparte;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

