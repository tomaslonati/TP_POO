package Programa.Model;

import java.io.Serializable;

public class Autoparte implements Serializable{
    private int codigo;
    private String denominacion;
    private String descripcion;
    private String categoria;
    private String marca;
    private String vehiculo;
    private String modelo;
    private double precioUnitario;
    private String enlace;
    private int cantidadEnStock;
    private int stockMinimo;

    // Constructor
    public Autoparte(int codigo, String denominacion, String descripcion, String categoria, String marca, String vehiculo, String modelo, double precioUnitario, String enlace, int cantidadEnStock, int stockMinimo) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.marca = marca;
        this.vehiculo = vehiculo;
        this.modelo = modelo;
        this.precioUnitario = precioUnitario;
        this.enlace = enlace;
        this.cantidadEnStock = cantidadEnStock;
        this.stockMinimo = stockMinimo;
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getMarca() {
        return marca;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public String getEnlace() {
        return enlace;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    // Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
