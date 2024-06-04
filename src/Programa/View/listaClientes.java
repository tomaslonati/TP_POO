package Programa.View;

import Programa.Model.Cliente;
import Programa.Model.ListaClientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Programa.View.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class listaClientes extends JFrame {
    private ListaClientes listaClientes;
    
    public listaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
        
        // Crear frame para agregar cliente
        setTitle("Lista Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        List<Cliente> clientes = listaClientes.obtenerClientes();
        
        // Crear columnas para la tabla
        String[] columnas = {"ID", "Nombre", "Dirección", "Teléfono", "Localidad", "Provincia", "Email"};
     
        
        // Crear modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
        };
        // Agregar filas al modelo de tabla
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getLocalidad(),
                cliente.getProvincia(),
                cliente.getEmail()
            };
            tableModel.addRow(fila);
        }
        
        // Crear tabla
        JTable tablaClientes = new JTable(tableModel);
        
        
        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        
        // Crear un nuevo JFrame para mostrar la tabla
        setSize(600, 400);
        add(scrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        // Agregar MouseListener para capturar eventos de doble clic
        tablaClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tablaClientes.getSelectedRow();
                    if (row != -1) {
                        int idCliente = (int) tablaClientes.getValueAt(row, 0);
                        new modificarCliente(listaClientes,idCliente).setVisible(true);
                          dispose();
                        }
                    }
                }
            
        });
    }}
