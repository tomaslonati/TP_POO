package Programa.View.Cliente;

import Programa.Model.Cliente;
import Programa.Model.ListaClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class agregarCliente extends JFrame {
    private ListaClientes listaClientes;
    
    public agregarCliente(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
        
        // Crear frame para agregar cliente
        setTitle("Agregar Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear componentes
        JPanel panel = new JPanel(new GridLayout(8, 2));
        JTextField txtIdCliente = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtLocalidad = new JTextField();
        JTextField txtProvincia = new JTextField();
        JTextField txtEmail = new JTextField();

        // Asignar valor a los componentes
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

        add(panel);
        setVisible(true);

        // Llamar al método para crear cliente al hacer clic en guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCliente = Integer.parseInt(txtIdCliente.getText());
                    String nombre = txtNombre.getText();
                    String direccion = txtDireccion.getText();
                    int telefono = Integer.parseInt(txtTelefono.getText());
                    String localidad = txtLocalidad.getText();
                    String provincia = txtProvincia.getText();
                    String email = txtEmail.getText();

                    listaClientes.agregarCliente(idCliente, nombre, direccion, telefono, localidad, provincia, email);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

