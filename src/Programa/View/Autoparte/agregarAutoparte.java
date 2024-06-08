package Programa.View.Autoparte;

import Programa.Model.Autoparte;
import Programa.Model.ListaAutopartes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class agregarAutoparte extends JFrame {
    private ListaAutopartes listaAutopartes;
    
    public agregarAutoparte(ListaAutopartes listaAutopartes) {
        this.listaAutopartes = listaAutopartes;
        
        // Crear frame para agregar autoparte
        setTitle("Agregar Autoparte");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear componentes
        JPanel panel = new JPanel(new GridLayout(12, 2)); // 12 filas y 2 columnas para el diseño de la cuadrícula
        JTextField txtCodigo = new JTextField();
        JTextField txtDenominacion = new JTextField();
        JTextField txtDescripcion = new JTextField();
        JTextField txtCategoria = new JTextField();
        JTextField txtPrecioUnitario = new JTextField();
        JTextField txtEnlace = new JTextField();
        JTextField txtCantidadEnStock = new JTextField();
        JTextField txtStockMinimo = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtVehiculo = new JTextField();

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

        JButton btnGuardar = new JButton("Guardar");
        panel.add(btnGuardar);

        add(panel);
        setVisible(true);

        // Llamar al método para crear autoparte al hacer clic en guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    String denominacion = txtDenominacion.getText();
                    String descripcion = txtDescripcion.getText();
                    String categoria = txtCategoria.getText();
                    String marca = txtMarca.getText();
                    String vehiculo = txtVehiculo.getText();
                    String modelo = txtModelo.getText();
                    double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
                    String enlace = txtEnlace.getText();
                    int cantidadEnStock = Integer.parseInt(txtCantidadEnStock.getText());
                    int stockMinimo = Integer.parseInt(txtStockMinimo.getText());

                    listaAutopartes.agregarAutoparte(codigo, denominacion, descripcion, categoria, marca, vehiculo, modelo, precioUnitario, enlace, cantidadEnStock, stockMinimo);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

