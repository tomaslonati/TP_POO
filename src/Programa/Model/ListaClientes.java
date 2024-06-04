package Programa.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaClientes implements Serializable{
    private List<Cliente> clientes;

    // Constructor
    public ListaClientes() {
        this.clientes = new ArrayList<>();
    }

    // Métodos para manejar la lista de clientes
    
    public void agregarCliente(int idCliente, String nombre, String direccion, int telefono, String localidad, String provincia, String email) {
        if(existeCliente(idCliente)==false) { //validar que no existe el cliente
        	Cliente c = new Cliente(idCliente, nombre, direccion, telefono, localidad, provincia, email); //crear obj cliente
        	clientes.add(c); //agregar obj cliente a la lista
        }
    }
    
    public void modificarCliente(Cliente c,  String nombre, String direccion, int telefono, String localidad, String provincia, String email) {
    	c.setNombre(nombre);
    	c.setDireccion(direccion);
    	c.setTelefono(telefono);
    	c.setLocalidad(localidad);
    	c.setProvincia(provincia);
    	c.setEmail(email);
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    //Recibe ID como parámetro y devuelve el cliente correspondiente
    public Cliente clienteConId(int id) {
    	Cliente cli = null;
    	for (int i=0;i<clientes.size();i++) {
    		Cliente c = clientes.get(i);
    		if (c.getIdCliente()==id) {
    			cli = c;
    			break;
    		}
    	}
    	return cli;
    }
    
    public boolean existeCliente(int codigo) {
    	boolean existe=false;
    	for (int i=0;i<clientes.size();i++) {
    		Cliente c = clientes.get(i);
    		if (c.getIdCliente()==codigo) {
    			existe=true;
    			break;
    		}
    	}
    	return existe;
    }
}

