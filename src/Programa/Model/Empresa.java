package Programa.Model;

import Programa.datos.*;
import java.util.*;
import java.io.*;

public class Empresa implements Serializable
{
	private String nombre;
	private ListaAutopartes autopartes;
	private ListaClientes clientes;
	private ListaPedidos pedidos;
	private ListaVentas ventas;
	
	//private ArrayList empleados;
	
	public Empresa(String n) 
	{
		nombre = n;
		//empleados = new ArrayList();
		autopartes = new ListaAutopartes();
		clientes = new ListaClientes();
		pedidos = new ListaPedidos();
		ventas = new ListaVentas();
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	
	public ListaAutopartes getAutopartes() {
		return autopartes;
	}

	public ListaClientes getClientes() {
		return clientes;
	}

	public ListaPedidos getPedidos() {
		return pedidos;
	}

	public ListaVentas getVentas() {
		return ventas;
	}

	public static Empresa recuperarse(){
		Empresa emp=(Empresa)Datos.recuperar();
		if(emp == null)
			emp = new Empresa("LA EMPRESA S.A.");
		return emp;
	}
	
	public boolean guardarse(){
		return Datos.guardar(this);
	}
}
