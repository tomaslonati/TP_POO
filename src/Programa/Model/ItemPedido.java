package Programa.Model;

import java.io.Serializable;

public class ItemPedido implements Serializable{
    private int idAutoparte;
    private int cantidad;
    private double montoTotal;
    private String nombre;

    // Constructor
    public ItemPedido(Autoparte A, int cantidad) {
        this.idAutoparte = A.getCodigo();
        this.nombre=A.getDenominacion();
        this.cantidad = cantidad;
        this.montoTotal=A.getPrecioUnitario()*cantidad;
    }


    // Getters
    public int getIdAutoparte() {
        return idAutoparte;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getMontoTotal() {
    	return montoTotal;
    }

    // Setters
    public void setIdAutoparte(int idAutoparte) {
        this.idAutoparte = idAutoparte;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

