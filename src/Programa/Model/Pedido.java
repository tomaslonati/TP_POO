package Programa.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable{
    private List<ItemPedido> listaItems;
    private int idCliente;
    private Date fecha;
    private double montoTotal;
    private int idPedido;
    private String estado;

    // Constructor
    public Pedido(int idCliente, Date fecha, double montoTotal, int idPedido, String estado) {
        this.listaItems = new ArrayList<>();
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.idPedido = idPedido;
        this.estado = estado;
    }

    // Métodos para manejar la lista de ítems de pedido
    public void agregarItemPedido(ItemPedido item) {
    	listaItems.add(item);
    }

    public void eliminarItemPedido(ItemPedido item) {
    	listaItems.remove(item);
    }

    public List<ItemPedido> getListaItems() {
        return listaItems;
    }

    public void setItemPedido(List<ItemPedido> listaItems) {
        this.listaItems = listaItems;
    }

    // Getters y Setters para los otros atributos
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

