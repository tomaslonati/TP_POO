package Programa;

import Programa.Model.*;
import Programa.Model.ListaClientes;
import Programa.Model.ListaPedidos;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaVentas;
import Programa.View.*;
import Programa.View.Cliente.agregarCliente;
import Programa.View.Cliente.buscarCliente;
import Programa.View.Cliente.listaClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;

    private Empresa E;

    // Constructor
    public mainFrame() {
        E = E.recuperarse();
        ListaClientes listaClientes = E.getClientes();
        ListaVentas listaVentas = E.getVentas();
        ListaAutopartes listaAutopartes = E.getAutopartes();
        ListaPedidos listaPedidos = E.getPedidos();
        initialize();

        // Configuración de la ventana 
        setTitle("Sistema de Gestión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear menús
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuPedidos = new JMenu("Pedidos");
        JMenu menuAutopartes = new JMenu("Autopartes");
        JMenu menuVentas = new JMenu("Ventas");

        // Crear ítems de menú
        
        //items menú clientes
        JMenuItem verClientes = new JMenuItem("Ver lista de clientes");
        JMenuItem addClientes = new JMenuItem("Agregar Cliente");
        JMenuItem searchCliente = new JMenuItem("Buscar cliente con ID");
        
        JMenuItem itemPedidos = new JMenuItem("Administrar PEDIDOS");
        JMenuItem itemAutopartes = new JMenuItem("Administrar AUTOPARTES");
        JMenuItem itemVentas = new JMenuItem("Agregar Venta");

        //Agregar ítems a los menús
        menuClientes.add(verClientes);
        menuClientes.add(addClientes);
        menuClientes.add(searchCliente);
        
        menuPedidos.add(itemPedidos);
        menuAutopartes.add(itemAutopartes);
        menuVentas.add(itemVentas);

        // Agregar menús a la barra de menú
        menuBar.add(menuClientes);
        menuBar.add(menuPedidos);
        menuBar.add(menuAutopartes);
        menuBar.add(menuVentas);

        // Configurar la barra de menú en el frame
        setJMenuBar(menuBar);

        // Vincular manejadores de eventos
        
        // evento agregar cliente
        addClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new agregarCliente(listaClientes).setVisible(true);
            }
        });
        
        // evento ver lista de clientes
        verClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new listaClientes(listaClientes).setVisible(true);
            }
        });
        
        // evento buscar cliente por su ID
        searchCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new buscarCliente(listaClientes).setVisible(true);
            }
        });
  

        itemPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PedidoFrame(listaPedidos).setVisible(true);
            }
        });

        itemAutopartes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AutoparteFrame(listaAutopartes).setVisible(true);
            }
        });

        itemVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentaFrame(listaVentas).setVisible(true);
            }
        });
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

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (E.guardarse())
                    JOptionPane.showMessageDialog(null, "GUARDADO OK");
                else
                    JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR");
            }
        });
    }
}
