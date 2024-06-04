package Programa.Model;

import java.io.Serializable;
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
}

