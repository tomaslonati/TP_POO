package Programa.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaItems implements Serializable {
    private List<ItemPedido> items;

    // Constructor
    public ListaItems() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(ItemPedido p) {
    	items.add(p);
    }
    
    public void sacarItem(ItemPedido i) {
    	items.remove(i);
    }
    //Busca autoparte en el pedido, devuelve el codigo propio si existe
    public boolean autoparteEnPedido(int codigo) {
    	boolean e = false;
    	for(int i=0;i<items.size();i++) {
    		ItemPedido item = items.get(i);
    		if(codigo==item.getIdAutoparte()) {
    			e=true;;
    		}
    	}
    	return e;
    }
    public List<ItemPedido> obtenerItems() {
        return items;
    }
    
}
