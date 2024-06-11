package Programa.Model;

import java.io.Serializable;
import Programa.Model.ListaPedidos;

import java.util.ArrayList;
import java.util.List;

public class ListaAutopartes implements Serializable{
    private List<Autoparte> autopartes;


    // Constructor
    public ListaAutopartes() {
        this.autopartes = new ArrayList<>();
    }

    // Métodos para manejar la lista de autopartes
    public void agregarAutoparte(int codigo, String denominacion, String descripcion, String categoria, String marca, String vehiculo, String modelo, double precioUnitario, String enlace, int cantidadEnStock, int stockMinimo) {
        if(existeAutoparte(codigo)==false) { //verificar que el codigo no exista, para crear obj autoparte y agregarlo a la lista
        	Autoparte A = new Autoparte(codigo,denominacion,descripcion,categoria,marca,vehiculo,modelo,precioUnitario,enlace,cantidadEnStock,stockMinimo);
        	autopartes.add(A);
        }else {
        	System.out.println("El código de la autoparte ya existe "); //si ya existe, se informa error
        }
    }
    
    public void modificarAutoparte(Autoparte a,String denominacion,String descripcion,String categoria,double precioUnitario,String enlace,int cantidadEnStock,int stockMinimo,String marca, String modelo, String vehiculo) {
    	a.setCantidadEnStock(cantidadEnStock);
    	a.setDenominacion(denominacion);
    	a.setDescripcion(descripcion);
    	a.setCantidadEnStock(cantidadEnStock);
    	a.setCategoria(categoria);
    	a.setStockMinimo(stockMinimo);
    	a.setEnlace(enlace);
    	a.setPrecioUnitario(precioUnitario);
    	a.setMarca(marca);
    	a.setModelo(modelo);
    	a.setVehiculo(vehiculo);
    	a.setCategoria(categoria);
    }

    public void eliminarAutoparte(Autoparte autoparte) {
        autopartes.remove(autoparte);
    }

    public List<Autoparte> obtenerAutopartes() {
        return autopartes;
    }

    public void setAutopartes(List<Autoparte> autopartes) {
        this.autopartes = autopartes;
    }
    
    //verificar si existe una autoparte en la lista, dado su codigo
    public boolean existeAutoparte(int codigo) {
    	boolean existe = false;
    	for (int i =0;i<autopartes.size();i++) { //recorrer lista de autopartes
    		Autoparte A = autopartes.get(i);
    		if(A.getCodigo()==codigo) { 
    			existe=true;
    			break;
    		}
    	}
    	return existe;
    }
    
    //Recibe codigo como parámetro y devuelve autoparte correspondiente
    public Autoparte autoparteConCodigo(int id) {
    	Autoparte au = null;
    	for (int i=0;i<autopartes.size();i++) {
    		Autoparte a = autopartes.get(i);
    		if (a.getCodigo()==id) {
    			au = a;
    			break;
    		}
    	}
    	return au;
    }
    
    public void descontarStock (int codigo,int cantidad) {
    	Autoparte a = autoparteConCodigo(codigo);
    	if (a!=null) {
			a.setCantidadEnStock(a.getCantidadEnStock()-cantidad); //actualizar stock
    	}
    }
    
    public void devolverStock (int codigo,int cantidad) {
    	Autoparte a = autoparteConCodigo(codigo);
    	if (a!=null) {
			a.setCantidadEnStock(a.getCantidadEnStock()+cantidad); //actualizar stock
    	}
    }
    
    public double pedirAutoparte(int codigo,int cantidad) {
    	Autoparte a = autoparteConCodigo(codigo);
    	if(a!=null) { //verificar que exista la autoparte
    		if(a.getCantidadEnStock()>=cantidad) { //verificar stock
    			double monto = a.getPrecioUnitario()*cantidad; //obtener monto total
    			return monto; //devolver monto total
    		}else {
        		return -1; //si no hay stock suficiente, se devuelve -1
        		}
    	}else {
    		return -1; //si no existe la autoparte, se devuelve -1
    		}
    }
}

