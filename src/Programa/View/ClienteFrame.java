package Programa.View;

import Programa.Model.Cliente;
import Programa.Model.ListaClientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ClienteFrame extends JFrame {
    private ListaClientes listaClientes;
    
    public ClienteFrame(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
        
        //crear frame para el ABM de clientes
        setTitle("Administrar Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //crear componentes
        JPanel panel = new JPanel();
        JButton viewCliente = new JButton("Ver lista de clientes");
        JButton addCliente = new JButton("Agregar cliente");
        JButton modifieCliente = new JButton("Modificar cliente con ID");

        //Agregar componentes al panel
        panel.add(viewCliente);
        panel.add(addCliente);
        panel.add(modifieCliente);
        
        // Agregar panel a la ventana
        add(panel, BorderLayout.CENTER);
        
        // Vincular manejadores de eventos a los componentes
        	
        	//al hacer click en viewCliente, llamar al metodo mostrarClientes (para mostrar tabla)
        viewCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarClientes();
            }
        });
        
	    	//al hacer click en addCliente
	    addCliente.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	mostrarFormularioAgregarCliente();
	        }
	    });
	    
    	//al hacer click en modifieCliente
    modifieCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	buscarYModificarCliente();
        }
    });
       
     
   }
    
    //METODOS
    
    //mostrar tabla de clientes
    private void mostrarClientes() {
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
        JFrame frameTabla = new JFrame("Lista de Clientes");
        frameTabla.setSize(600, 400);
        frameTabla.add(scrollPane, BorderLayout.CENTER);
        frameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameTabla.setVisible(true);
        
        // Agregar MouseListener para capturar eventos de doble clic
        tablaClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tablaClientes.getSelectedRow();
                    if (row != -1) {
                        int idCliente = (int) tablaClientes.getValueAt(row, 0);
                          modificarCliente(idCliente);
                          frameTabla.dispose();
                        }
                    }
                }
            
        });
    }
    
    //metodo para crear ventana para agregar nuevo cliente
    private void mostrarFormularioAgregarCliente() {
    	//crea el frame
        JFrame frameAgregar = new JFrame("Agregar Cliente");
        frameAgregar.setSize(400, 300);
        frameAgregar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //crear componentes
        JPanel panel = new JPanel(new GridLayout(8, 2));
        JTextField txtIdCliente = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtLocalidad = new JTextField();
        JTextField txtProvincia = new JTextField();
        JTextField txtEmail = new JTextField();

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

        JButton btnGuardar = new JButton("Guardar");
        panel.add(btnGuardar);

        frameAgregar.add(panel);
        frameAgregar.setVisible(true);

        //llamar al metodo para crear cliente al hacer click en guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                int telefono = Integer.parseInt(txtTelefono.getText());
                String localidad = txtLocalidad.getText();
                String provincia = txtProvincia.getText();
                String email = txtEmail.getText();

                listaClientes.agregarCliente(idCliente, nombre, direccion, telefono, localidad, provincia, email);
                frameAgregar.dispose();
            }
        });
    }
    
    private void buscarYModificarCliente() {
    	//crea el frame
        JFrame frameAgregar = new JFrame("Ingresar cliente");
        frameAgregar.setSize(400, 300);
        frameAgregar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //crear componentes
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtIdCliente = new JTextField();

        //asignar valor a los componentes
        panel.add(new JLabel("ID Cliente:"));
        panel.add(txtIdCliente);
      
        JButton btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar);

        frameAgregar.add(panel);
        frameAgregar.setVisible(true);

        //llamar al metodo para crear cliente al hacer click en guardar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                Cliente c = listaClientes.clienteConId(idCliente);
                if (c!=null) {
                	modificarCliente(c.getIdCliente());
                }else {
                	JOptionPane.showMessageDialog(frameAgregar,
                            "Cliente con ID " + idCliente + " no encontrado.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                frameAgregar.dispose();
            }
        });
    }

    //modificar datos de un cliente dado su id
    private void modificarCliente(int id) {
    	Cliente c =listaClientes.clienteConId(id);
    	if (c!=null) {
        	//crea el frame
            JFrame frameModificar = new JFrame("Modificar Cliente");
            frameModificar.setSize(400, 300);
            frameModificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
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
            frameModificar.add(panel);
            frameModificar.setVisible(true);

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
                    
                    frameModificar.dispose();
                }
            });
            
          //Al clickear no guardar, Cerrar el frame sin guardar cambios 
            btnEliminarCambios.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                	frameModificar.dispose();
                }
            });
            
            //Eliminar cliente al hacer click
            btnEliminarCliente.addActionListener(new ActionListener(){
            	@Override
                public void actionPerformed(ActionEvent e) { 
            		 int response = JOptionPane.showConfirmDialog(frameModificar,
                             "¿Estás seguro que deseas eliminar el cliente?",
                             "Confirmar eliminación",
                             JOptionPane.YES_NO_OPTION,
                             JOptionPane.WARNING_MESSAGE);
                     
                     if (response == JOptionPane.YES_OPTION) {
                         listaClientes.eliminarCliente(c);
                         frameModificar.dispose();
                     }
                 
                }
            });
            
    	}
    }
}
