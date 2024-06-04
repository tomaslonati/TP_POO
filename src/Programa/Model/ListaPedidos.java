package Programa.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaPedidos implements Serializable{
    private List<Pedido> pedidos;

    // Constructor
    public ListaPedidos() {
        this.pedidos = new ArrayList<>();
    }

    // Métodos para manejar la lista de pedidos
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    } 

    public void eliminarPedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
