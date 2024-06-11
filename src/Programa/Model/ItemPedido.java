package Programa.Model;

import java.io.Serializable;

public class ItemPedido implements Serializable{
    private int idAutoparte;
    private int cantidad;
    private double montoTotal;
    private String nombre;
    Autoparte a;

    // Constructor
    public ItemPedido(Autoparte A, int cantidad) {
        this.idAutoparte = A.getCodigo();
        this.nombre=A.getDenominacion();
        this.cantidad = cantidad;
        this.a=A;
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
        double nuevoMonto = a.getPrecioUnitario()*cantidad;
        this.setMontoTotal(nuevoMonto);
    }
    
    public void setMontoTotal(double mt) {
    	this.montoTotal=mt;
    }
}

