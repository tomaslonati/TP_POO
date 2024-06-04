package Programa.Model;

import java.io.Serializable;

public class Venta implements Serializable{
    private String formaDePago;
    private double precioFinal;
    private int idPedido;
    private int cuotas;

    // Constructor
    public Venta(String formaDePago, double precioFinal, int idPedido, int cuotas) {
        this.formaDePago = formaDePago;
        this.precioFinal = precioFinal;
        this.idPedido = idPedido;
        this.cuotas = cuotas;
    }

    // Getters
    public String getFormaDePago() {
        return formaDePago;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getCuotas() {
        return cuotas;
    }

    // Setters
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }
}
