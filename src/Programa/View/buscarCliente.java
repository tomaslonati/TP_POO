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

public class buscarCliente extends JFrame {
    private ListaClientes listaClientes;
    
    public buscarCliente(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
        
       //crea el frame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //crear componentes
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtIdCliente = new JTextField();

        //asignar valor a los componentes
        panel.add(new JLabel("ID Cliente:"));
        panel.add(txtIdCliente);
      
        JButton btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar);

        add(panel);
        setVisible(true);

        //llamar al metodo para crear cliente al hacer click en guardar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                Cliente c = listaClientes.clienteConId(idCliente);
                if (c!=null) {
                    new modificarCliente(listaClientes,idCliente).setVisible(true);
                }else {
                	JOptionPane.showMessageDialog(null,
                            "Cliente con ID " + idCliente + " no encontrado.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });
    }
   }
