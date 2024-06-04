package Programa;

import Programa.Model.ListaClientes;
import Programa.Model.ListaPedidos;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaVentas;
import Programa.View.ClienteFrame;
import Programa.View.PedidoFrame;
import Programa.View.AutoparteFrame;
import Programa.View.VentaFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame extends JFrame {
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;
    private ListaAutopartes listaAutopartes;
    private ListaVentas listaVentas;

    // Constructor
    public mainFrame() {
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos();
        listaAutopartes = new ListaAutopartes();
        listaVentas = new ListaVentas();

        // Configuración de la ventana
        setTitle("Sistema de Gestión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear componentes
        JPanel panel = new JPanel();
        JButton abmClientes = new JButton("Administrar CLIENTES");
        JButton abmPedidos = new JButton("Administrar PEDIDOS");
        JButton abmAutopartes = new JButton("Administrar AUTOPARTES");
        JButton registrarVenta = new JButton("Agregar Venta");

        // Agregar componentes al panel
        panel.add(abmClientes);
        panel.add(abmPedidos);
        panel.add(abmAutopartes);
        panel.add(registrarVenta);

        // Agregar panel a la ventana
        add(panel, BorderLayout.CENTER);

        // Vincular manejadores de eventos
        	
         	//al hacer click en el boton, abrir la ventana del ABM
        abmClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteFrame(listaClientes).setVisible(true);
            }
        });
        /*
        abmPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PedidoFrame(listaPedidos).setVisible(true);
            }
        });

        abmAutopartes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AutoparteFrame(listaAutopartes).setVisible(true);
            }
        });

        registrarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentaFrame(listaVentas).setVisible(true);
            }
        });*/
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }
}

