package Programa.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaVentas implements Serializable{
    private List<Venta> ventas;

    // Constructor
    public ListaVentas() {
        this.ventas = new ArrayList<>();
    }

    // Métodos para manejar la lista de ventas
    public void agregarVenta(Venta venta) {
        ventas.add(venta);
    }

    public void eliminarVenta(Venta venta) {
        ventas.remove(venta);
    }

    public List<Venta> obtenerVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}

