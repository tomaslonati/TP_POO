package Programa.View.Autoparte;

import Programa.Model.Autoparte;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaPedidos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class modificarAutoparte extends JFrame {
    private ListaAutopartes listaAutopartes;
    
    public modificarAutoparte(ListaAutopartes listaAutopartes,  ListaPedidos listaPedidosInt,int codigo) {
        Autoparte autoparte = listaAutopartes.autoparteConCodigo(codigo);
        
        if (autoparte != null) {
            // Crear frame
            setTitle("Modificar Autoparte");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            // Crear componentes y autocompletar campos con los datos actuales de la autoparte
            JPanel panel = new JPanel(new GridLayout(13, 3));
            JTextField txtCodigo = new JTextField(autoparte.getCodigo());
            txtCodigo.setEditable(false); // Hacer el campo código no editable
            JTextField txtDenominacion = new JTextField(autoparte.getDenominacion());
            JTextField txtDescripcion = new JTextField(autoparte.getDescripcion());
            JTextField txtCategoria = new JTextField(autoparte.getCategoria());
            JTextField txtPrecioUnitario = new JTextField(String.valueOf(autoparte.getPrecioUnitario()));
            JTextField txtEnlace = new JTextField(autoparte.getEnlace());
            JTextField txtCantidadEnStock = new JTextField(String.valueOf(autoparte.getCantidadEnStock()));
            JTextField txtStockMinimo = new JTextField(String.valueOf(autoparte.getStockMinimo()));
            JTextField txtMarca = new JTextField(autoparte.getMarca());
            JTextField txtModelo = new JTextField(autoparte.getModelo());
            JTextField txtVehiculo = new JTextField(autoparte.getVehiculo());

            // Asignar valor a los componentes
            panel.add(new JLabel("Código:"));
            panel.add(txtCodigo);
            panel.add(new JLabel("Denominación:"));
            panel.add(txtDenominacion);
            panel.add(new JLabel("Descripción:"));
            panel.add(txtDescripcion);
            panel.add(new JLabel("Categoría:"));
            panel.add(txtCategoria);
            panel.add(new JLabel("Precio por unidad:"));
            panel.add(txtPrecioUnitario);
            panel.add(new JLabel("Enlace:"));
            panel.add(txtEnlace);
            panel.add(new JLabel("Stock actual:"));
            panel.add(txtCantidadEnStock);
            panel.add(new JLabel("Stock mínimo:"));
            panel.add(txtStockMinimo);
            panel.add(new JLabel("Marca:"));
            panel.add(txtMarca);
            panel.add(new JLabel("Modelo:"));
            panel.add(txtModelo);
            panel.add(new JLabel("Vehículo:"));
            panel.add(txtVehiculo);
            
            JButton btnGuardar = new JButton("Guardar cambios"); // Botón para guardar cambios
            panel.add(btnGuardar);
            
            JButton btnEliminarCambios = new JButton("Salir sin modificar");  // Botón para no guardar cambios
            panel.add(btnEliminarCambios);
            
            JButton btnEliminarAutoparte = new JButton("Eliminar autoparte");  // Botón para eliminar autoparte
            panel.add(btnEliminarAutoparte);
            
            add(panel);
            setVisible(true);

            // Comportamiento al clickear en guardar
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener los nuevos valores ingresados por el usuario
                    String denominacion = txtDenominacion.getText();
                    String descripcion = txtDescripcion.getText();
                    String categoria = txtCategoria.getText();
                    double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
                    String enlace = txtEnlace.getText();
                    int cantidadEnStock = Integer.parseInt(txtCantidadEnStock.getText());
                    int stockMinimo = Integer.parseInt(txtStockMinimo.getText());
                    String marca = txtMarca.getText();
                    String modelo = txtModelo.getText();
                    String vehiculo = txtVehiculo.getText();
                    
                    // Llamar al método de listaAutopartes para actualizar los datos
                    listaAutopartes.modificarAutoparte(autoparte, denominacion, descripcion, categoria, precioUnitario, enlace, cantidadEnStock, stockMinimo, marca, modelo, vehiculo);
                    
                    dispose();
                }
            });
            
            // Al clickear "Salir sin modificar", cerrar el frame sin guardar cambios
            btnEliminarCambios.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                    dispose();
                }
            });
            
            // Eliminar autoparte al hacer clic
            btnEliminarAutoparte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                    int response = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro que deseas eliminar la autoparte?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                    
                    if (response == JOptionPane.YES_OPTION) {
                        listaAutopartes.eliminarAutoparte(autoparte);
                        dispose();
                    }
                }
            });
        }
    }
}
