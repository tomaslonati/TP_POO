package Programa.View.Pedido;

import Programa.Model.Autoparte;
import Programa.Model.ItemPedido;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaClientes;
import Programa.Model.Autoparte;
import Programa.Model.Cliente;
import Programa.Model.ItemPedido;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaClientes;
import Programa.Model.ListaItems;
import Programa.Model.ListaPedidos;
import Programa.Model.ListaVentas;
import Programa.Model.Pedido;
import Programa.View.Cliente.agregarCliente;
import Programa.View.Venta.nuevaVenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class agregarPedido extends JFrame {
    private ListaAutopartes listaAutopartes;
    private DefaultTableModel tableModel;
    private ListaPedidos listaPedidos;
    private ListaClientes listaClientes;

    private JLabel labelMonto; // Declarar el JLabel fuera del constructor

    public agregarPedido(ListaAutopartes listaAutopartes, ListaPedidos listaPedidos, ListaClientes listaClientes, ListaVentas listaVentas) {
        this.listaAutopartes = listaAutopartes;
        this.listaPedidos = listaPedidos;
        this.listaClientes = listaClientes;

        // Crear frame para agregar cliente
        setTitle("Agregar autoparte al pedido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel para el título y subtítulo
        JPanel panelTitulo = new JPanel(new GridLayout(1, 2));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel panelBotones = new JPanel(new GridLayout(1,3));

        // inicializar pedido
        Pedido p = listaPedidos.inicializarPedido();

        // Crear panel para el título
        JPanel panelTituloPedido = new JPanel(new GridLayout(2, 1));
        panelTituloPedido.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JLabel titulo = new JLabel("Pedido #" + p.getIdPedido());
        titulo.setFont(new Font("Arial", Font.PLAIN, 14));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        labelMonto = new JLabel("Monto total: " + p.getMontoTotal());
        labelMonto.setFont(new Font("Arial", Font.PLAIN, 12));
        labelMonto.setHorizontalAlignment(SwingConstants.CENTER);

        panelTituloPedido.add(titulo);
        panelTituloPedido.add(labelMonto);

        panelTitulo.add(panelTituloPedido);

        // Crear panel para seleccionar cliente
        JPanel panelCliente = new JPanel(new GridLayout(2, 1));

        JLabel labelCliente = new JLabel("Seleccione el Cliente:");
        JComboBox<Cliente> comboBoxClientes = new JComboBox<>();
        for (Cliente cliente : listaClientes.obtenerClientes()) {
            comboBoxClientes.addItem(cliente);
        }

        panelCliente.add(labelCliente);
        panelCliente.add(comboBoxClientes);

        panelTitulo.add(panelCliente);

        // Añadir el panel de título y cliente al frame
        add(panelTitulo, BorderLayout.NORTH);

        // Crear pedido
        ListaItems listaItems = p.getListaItems();

        // Crear columnas para la tabla
        String[] columnas = {"Nombre", "ID", "Cantidad", "Monto"};

        // Crear modelo de tabla
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Agregar filas al modelo de tabla
        for (ItemPedido item : listaItems.obtenerItems()) {
            Object[] fila = {
                    item.getNombre(),
                    item.getIdAutoparte(),
                    item.getCantidad(),
                    item.getMontoTotal()
            };
            tableModel.addRow(fila);
        }

        // Crear tabla
        JTable tablaItems = new JTable(tableModel);

        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(tablaItems);

        // Crear botón para agregar item
        JButton addButton = new JButton("Agregar autoparte");
        
        //crear boton para guardar cambios
        JButton saveButton = new JButton("Guardar cambios");

        //crear boton para cancelar cambios
        JButton cancelButton = new JButton("Cancelar");
        
        //agregar botones al panel correspondiente
        panelBotones.add(addButton);
        panelBotones.add(saveButton);
        panelBotones.add(cancelButton);

        // Añadir la tabla y el botón al frame
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);

        // Evento para agregar autoparte al pedido
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente selectedCliente = (Cliente) comboBoxClientes.getSelectedItem();
                if (selectedCliente != null) {
                    p.setCliente(selectedCliente);
                    mostrarDialogoAgregarAutoparte(p);
                } else {
                    JOptionPane.showMessageDialog(agregarPedido.this,
                            "Por favor, seleccione un cliente.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Evento para guardar pedido en lista de pedidos
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPedidos.agregarPedido(p);
                int response = JOptionPane.showConfirmDialog(null,
            		    "¿Desea continuar con la venta?",
            		    "Realizar venta",
            		    JOptionPane.YES_NO_OPTION,
            		    JOptionPane.WARNING_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                    new nuevaVenta(p).setVisible(true); 
                    dispose();
                }
                else if(response == JOptionPane.NO_OPTION) {
                    dispose();
                }
            }
        });
    }
    
    

    private void mostrarDialogoAgregarAutoparte(Pedido p) {
        JDialog dialogo = new JDialog(this, "Seleccionar Autoparte", true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new BorderLayout());

        // Crear combo box con autopartes
        JComboBox<Autoparte> comboBoxAutopartes = new JComboBox<>();
        for (Autoparte autoparte : listaAutopartes.obtenerAutopartes()) {
            comboBoxAutopartes.addItem(autoparte);
        }

        // Crear campo de texto para la cantidad
        JTextField txtCantidad = new JTextField();

        // Crear panel para los controles
        JPanel panelControles = new JPanel(new GridLayout(3, 2));
        panelControles.add(new JLabel("Autoparte:"));
        panelControles.add(comboBoxAutopartes);
        panelControles.add(new JLabel("Cantidad:"));
        panelControles.add(txtCantidad);

        // Crear botones de agregar y cancelar
        JButton btnAgregar = new JButton("Agregar");
        JButton btnCancelar = new JButton("Cancelar");

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al diálogo
        dialogo.add(panelControles, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });

        // Acción del botón agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Autoparte selectedAutoparte = (Autoparte) comboBoxAutopartes.getSelectedItem();
                    int cantidad = Integer.parseInt(txtCantidad.getText());

                    if (selectedAutoparte != null) {
                        double monto = listaAutopartes.pedirAutoparte(selectedAutoparte.getCodigo(), cantidad); // se verifica si hay dispo
                        if (monto != -1) {
                            ItemPedido item = new ItemPedido(selectedAutoparte, cantidad);
                            Object[] fila = {
                                    selectedAutoparte.getDenominacion(),
                                    selectedAutoparte.getCodigo(),
                                    cantidad,
                                    monto
                            };
                            tableModel.addRow(fila);
                            p.agregarItemPedido(item);
                            actualizarMontoTotal(p);
                            dialogo.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialogo,
                                    "No hay suficiente cantidad de la autoparte",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialogo,
                                "Por favor, seleccione una autoparte.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialogo,
                            "Por favor, ingrese una cantidad válida.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialogo.setVisible(true);
    }

    private void actualizarMontoTotal(Pedido p) {
        labelMonto.setText("Monto total: " + p.getMontoTotal());
    }

}