package Programa;

import Programa.Model.*;
import Programa.Model.ListaClientes;
import Programa.Model.ListaPedidos;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaVentas;
import Programa.View.*;
import Programa.View.Autoparte.agregarAutoparte;
import Programa.View.Autoparte.buscarAutoparte;
import Programa.View.Autoparte.listadoAutopartes;
import Programa.View.Cliente.agregarCliente;
import Programa.View.Cliente.buscarCliente;
import Programa.View.Cliente.listaClientes;
import Programa.View.Pedido.abmItems;
import Programa.View.Pedido.agregarPedido;
import Programa.View.Pedido.buscarPedido;
import Programa.View.Pedido.listadoPedidos;

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
        
        // Crear panel para el título y subtítulo
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel titulo = new JLabel("Bienvenido al sistema de gestión de autopartes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);    

        panelTitulo.add(titulo);
        panelTitulo.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre título y subtítulo
        
        add(panelTitulo, BorderLayout.NORTH);

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
        
        //items menú autopartes
        JMenuItem verAutoparte = new JMenuItem("Ver lista de autopartes");
        JMenuItem addAutoparte = new JMenuItem("Agregar autoparte");
        JMenuItem searchAutoparte = new JMenuItem("Buscar autoparte con código");
        
        //items menú pedidos
        JMenuItem verPedidos = new JMenuItem("Ver lista de pedidos");
        JMenuItem agregarPedido = new JMenuItem("Agregar pedido");
        JMenuItem searchPedido = new JMenuItem("Buscar pedido con código");
        
        
        
        //Agregar ítems a los menús
        
        //agregar items menú clientes
        menuClientes.add(verClientes);
        menuClientes.add(addClientes);
        menuClientes.add(searchCliente);
        
        //agregar items menú autopartes
        menuAutopartes.add(verAutoparte);
        menuAutopartes.add(addAutoparte);
        menuAutopartes.add(searchAutoparte);
        
        //agregar items menu pedidos
        menuPedidos.add(agregarPedido);
        menuPedidos.add(searchPedido);
        menuPedidos.add(verPedidos);

        
        // Agregar menús a la barra de menú
        menuBar.add(menuClientes);
        menuBar.add(menuPedidos);
        menuBar.add(menuAutopartes);
        menuBar.add(menuVentas);

        // Configurar la barra de menú en el frame
        setJMenuBar(menuBar);

        
        // Vincular manejadores de eventos de CLIENTES
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
        
        // Vincular manejadores de eventos de AUTOPARTES
        // evento agregar autoparte
        addAutoparte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new agregarAutoparte(listaAutopartes).setVisible(true);
            }
        });
        
        // evento ver lista de autopartes
        verAutoparte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new listadoAutopartes(listaAutopartes,listaPedidos).setVisible(true);
            }
        });
        
        // evento buscar cliente por su ID
        searchAutoparte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new buscarAutoparte(listaAutopartes,listaPedidos).setVisible(true);
            }
        });
        
        // Vincular manejadores de eventos de PEDIDOS
        // evento agregar Pedido
        agregarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new agregarPedido(listaAutopartes, listaPedidos,listaClientes,listaVentas).setVisible(true);
            }
        });
        
        // evento ver Pedido
        verPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new listadoPedidos(listaAutopartes, listaPedidos,listaClientes,listaVentas).setVisible(true);
            }
        });
        
        // evento buscar Pedido
        searchPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new buscarPedido(listaAutopartes, listaPedidos,listaClientes,listaVentas).setVisible(true);
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
