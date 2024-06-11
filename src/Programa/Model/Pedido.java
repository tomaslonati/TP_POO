package Programa.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable{
    ListaItems listaItems;
    private int idCliente;
    private String fecha;
    private double montoTotal;
    private int idPedido;
    private String estado;

    // Constructor
    public Pedido( String fecha,  int idPedido) {
        this.listaItems = new ListaItems();
        this.fecha = fecha;
        this.idPedido = idPedido;
        this.estado = "c";
        this.montoTotal=0;
    }

    // Métodos para manejar la lista de ítems de pedido
    public void agregarItemPedido(ItemPedido item) {
    	listaItems.addItem(item);
    	montoTotal+=item.getMontoTotal();
    }

    public void eliminarItemPedido(ItemPedido item) {
    	montoTotal-=item.getMontoTotal();
    	listaItems.sacarItem(item);
    }
    
    public void modificarCantItemPedido(ItemPedido item, int cant) {
    	montoTotal-=item.getMontoTotal(); //restar cantidad anterior del monto total
    	listaItems.modificarCantidad(item, cant);
    	montoTotal+=item.getMontoTotal(); //actualizar monto total con la nueva cantidad
    }
    
 
    public void actualizaStockAutopartes(ListaAutopartes listaAutopartes) {
        for (ItemPedido item : listaItems.obtenerItems()) {
            listaAutopartes.descontarStock(item.getIdAutoparte(), item.getCantidad());
        }
    }

    public void revertirStockAutopartes(ListaAutopartes listaAutopartes) {
        for (ItemPedido item : listaItems.obtenerItems()) {
            listaAutopartes.devolverStock(item.getIdAutoparte(), item.getCantidad());
        }
    }


    public ListaItems getListaItems() {
        return listaItems;
    }


    // Getters y Setters para los otros atributos
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setCliente(Cliente c) {
    	this.idCliente=c.getIdCliente();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
    
    //Busca autoparte en el pedido, devuelve el codigo propio si existe
    public String autoparteEnPedido(int codigo) {
    	String c = null;
    	boolean e = listaItems.autoparteEnPedido(codigo);
    	if(e==true) {
    		c =  String.valueOf(this.idPedido);
    		}
    	return c;
    }
}

