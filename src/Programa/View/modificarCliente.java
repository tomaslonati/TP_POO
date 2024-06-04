package Programa.View;

import Programa.Model.Cliente;
import Programa.Model.ListaClientes;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modificarCliente extends JFrame {
    private ListaClientes listaClientes;
    
    public modificarCliente(ListaClientes listaClientes, int id) {
    	Cliente c =listaClientes.clienteConId(id);
    	if (c!=null) {
        	//crea el frame
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            // Crear componentes y autocomletar campos con los datos actuales del cliente
            JPanel panel = new JPanel(new GridLayout(9, 3));
            JTextField txtIdCliente = new JTextField(String.valueOf(c.getIdCliente()));
            txtIdCliente.setEditable(false); // Hacer el campo ID no editable
            JTextField txtNombre = new JTextField(c.getNombre());
            JTextField txtDireccion = new JTextField(c.getDireccion());
            JTextField txtTelefono = new JTextField(String.valueOf(c.getTelefono()));
            JTextField txtLocalidad = new JTextField(c.getLocalidad());
            JTextField txtProvincia = new JTextField(c.getProvincia());
            JTextField txtEmail = new JTextField(c.getEmail());

            //asignar valor a los componentes
            panel.add(new JLabel("ID Cliente:"));
            panel.add(txtIdCliente);
            panel.add(new JLabel("Nombre:"));
            panel.add(txtNombre);
            panel.add(new JLabel("Dirección:"));
            panel.add(txtDireccion);
            panel.add(new JLabel("Teléfono:"));
            panel.add(txtTelefono);
            panel.add(new JLabel("Localidad:"));
            panel.add(txtLocalidad);
            panel.add(new JLabel("Provincia:"));
            panel.add(txtProvincia);
            panel.add(new JLabel("Email:"));
            panel.add(txtEmail);
            
            JButton btnGuardar = new JButton("Guardar cambios"); //boton para guardar cambios
            panel.add(btnGuardar);
            
            JButton btnEliminarCambios = new JButton("Salir sin guardar");  //boton para no guardar cambios
            panel.add(btnEliminarCambios);
            
            JButton btnEliminarCliente = new JButton("Eliminar cliente");  //Botón para eliminar cliente
            panel.add(btnEliminarCliente);
            
            
            //agregar el frame al panel
            add(panel);
            setVisible(true);

            //Comportamiento al clickear en guardar
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	//obtener los nuevos valores ingresados por el usuario
                    String nombre = txtNombre.getText();
                    String direccion = txtDireccion.getText();
                    int telefono = Integer.parseInt(txtTelefono.getText());
                    String localidad = txtLocalidad.getText();
                    String provincia = txtProvincia.getText();
                    String email = txtEmail.getText();
                    
                    //llamar al metodo de listaClientes para actualizar los datos
                    listaClientes.modificarCliente(c, nombre, direccion, telefono, localidad, provincia, email);
                    
                    dispose();
                }
            });
            
          //Al clickear no guardar, Cerrar el frame sin guardar cambios 
            btnEliminarCambios.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                	dispose();
                }
            });
            
          //Eliminar cliente al hacer click
            btnEliminarCliente.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) { 
                	int response = JOptionPane.showConfirmDialog(null,
                		    "¿Estás seguro que deseas eliminar el cliente?",
                		    "Confirmar eliminación",
                		    JOptionPane.YES_NO_OPTION,
                		    JOptionPane.WARNING_MESSAGE);
                    
                    if (response == JOptionPane.YES_OPTION) {
                        listaClientes.eliminarCliente(c);
                        dispose();
                    }
                }
            });


            
    	}
    }
}
