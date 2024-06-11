package Programa.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListaPedidos implements Serializable{
    private List<Pedido> pedidos;

    // Constructor
    public ListaPedidos() {
        this.pedidos = new ArrayList<>();
    }

    // Métodos para manejar la lista de pedidos
    public void agregarPedido(Pedido pedido, ListaAutopartes listaAutopartes) {
        pedidos.add(pedido);
        pedido.actualizaStockAutopartes(listaAutopartes);
    } 
    
    public void actualizarPedido(Pedido pedido, ListaAutopartes listaAutopartes) {
        // Buscar el índice del pedido en la lista de pedidos
        int index = -1;
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getIdPedido() == pedido.getIdPedido()) {
                index = i;
                break;
            }
        }

        // Si se encuentra el pedido, actualizarlo
        if (index != -1) {
            // Recuperar el pedido original para revertir el stock de autopartes
            Pedido pedidoOriginal = pedidos.get(index);

            // Revertir el stock de autopartes del pedido original
            pedidoOriginal.revertirStockAutopartes(listaAutopartes);

            // Actualizar el pedido en la lista
            pedidos.set(index, pedido);

            // Actualizar el stock de autopartes del pedido actualizado
            pedido.actualizaStockAutopartes(listaAutopartes);
        }
    }

    
    public String autoparteEnPedido(int codigo) {
    	String pedidosConAutoparte = null;
    	for (int i=0;i<pedidos.size();i++) {
    		Pedido p = pedidos.get(i);
    		if (p.autoparteEnPedido(codigo)!=null) {
    			pedidosConAutoparte = "Autoparte está en el pedido: "+p.autoparteEnPedido(codigo);
    		}
    	}
    	return pedidosConAutoparte;
    }

    public void eliminarPedido(Pedido pedido) {
        pedidos.remove(pedido);
    }
    
    public Pedido pedidoConId(int id) {
    	Pedido pedido = null;
    	for (int i=0;i<pedidos.size();i++) {
    		Pedido p = pedidos.get(i);
    		if (p.getIdPedido()==id) {
    			pedido=p;
    			break;
    		}
    	}
    	return pedido;
    }
    

    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public Pedido inicializarPedido() {
        String fechaActual = LocalDate.now().toString(); // Obtener fecha actual
        int ID = this.generarID();
    	Pedido p=new Pedido(fechaActual, ID);
    	return p;
    }
    public int generarID () {
    	return pedidos.size()+1;
    }
    }
