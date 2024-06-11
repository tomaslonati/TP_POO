package Programa.View.Pedido;


import Programa.Model.*;
import Programa.View.Venta.nuevaVenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class modificarPedido extends JFrame {
    private ListaAutopartes listaAutopartes;
    private DefaultTableModel tableModel;
    private ListaPedidos listaPedidos;
    private ListaClientes listaClientes;

    private JLabel labelMonto; // Declarar el JLabel fuera del constructor

    public modificarPedido(ListaAutopartes listaAutopartes, ListaPedidos listaPedidos, ListaClientes listaClientes, ListaVentas listaVentas, Pedido pedidoExistente) {
        this.listaAutopartes = listaAutopartes;
        this.listaPedidos = listaPedidos;
        this.listaClientes = listaClientes;

        // Crear frame para modificar pedido
        setTitle("Modificar Pedido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel para el título y subtítulo
        JPanel panelTitulo = new JPanel(new GridLayout(1, 2));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel panelBotones = new JPanel(new GridLayout(1,3));

        // Usar el pedido existente en lugar de inicializar uno nuevo
        Pedido p = pedidoExistente;

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

        // Seleccionar el cliente actual del pedido
        comboBoxClientes.setSelectedItem(p.getIdCliente());

        panelCliente.add(labelCliente);
        panelCliente.add(comboBoxClientes);

        panelTitulo.add(panelCliente);

        // Añadir el panel de título y cliente al frame
        add(panelTitulo, BorderLayout.NORTH);

        // Usar la lista de items del pedido existente
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
        
        // Crear menú (click derecho en una fila de la tabla)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar Item");
        JMenuItem modificarCantidad = new JMenuItem("Modificar Cantidad");
        popupMenu.add(eliminarItem);
        popupMenu.add(modificarCantidad);

        // Agregar menú al hacer click derecho en una fila de la tabla
        tablaItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = tablaItems.rowAtPoint(e.getPoint());
                    int column = tablaItems.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        tablaItems.setRowSelectionInterval(row, row);
                        popupMenu.show(tablaItems, e.getX(), e.getY());
                    }
                }
            }
        });

        // Agregar acciones para los elementos del menú contextual
        eliminarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaItems.getSelectedRow();
                if (selectedRow != -1) {
                    int idItem = Integer.parseInt(tablaItems.getValueAt(selectedRow, 1).toString());
                    ItemPedido item = listaItems.itemConId(idItem);
                    p.eliminarItemPedido(item);
                    actualizarMontoTotal(p);
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        modificarCantidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaItems.getSelectedRow();
                if (selectedRow != -1) {
                    String nuevaCantidadStr = JOptionPane.showInputDialog(modificarPedido.this, "Ingrese la nueva cantidad:");
                    try {
                        int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                        int idItem = Integer.parseInt(tablaItems.getValueAt(selectedRow, 1).toString());
                        if (nuevaCantidad > 0 && listaAutopartes.pedirAutoparte(idItem, nuevaCantidad) != -1) {
                            tableModel.setValueAt(nuevaCantidad, selectedRow, 2);
                            ItemPedido item = listaItems.itemConId(idItem);
                            p.modificarCantItemPedido(item, nuevaCantidad);
                            tableModel.setValueAt(String.valueOf(item.getMontoTotal()), selectedRow, 3);
                            actualizarMontoTotal(p);
                        } else {
                            JOptionPane.showMessageDialog(modificarPedido.this, "No hay stock suficiente");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(modificarPedido.this, "Por favor, ingrese un número válido.");
                    }
                }
            }
        });

        // Crear botón para agregar item
        JButton addButton = new JButton("Agregar autoparte");
        
        // Crear botón para guardar cambios
        JButton saveButton = new JButton("Guardar cambios");

        // Crear botón para cancelar cambios
        JButton cancelButton = new JButton("Cancelar");
        
        // Agregar botones al panel correspondiente
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
                    JOptionPane.showMessageDialog(modificarPedido.this,
                            "Por favor, seleccione un cliente.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Evento para guardar cambios en el pedido
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPedidos.actualizarPedido(p, listaAutopartes);
                int response = JOptionPane.showConfirmDialog(null,
                        "¿Desea continuar con la venta?",
                        "Realizar venta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                    new nuevaVenta(p).setVisible(true);
                    dispose();
                } else if (response == JOptionPane.NO_OPTION) {
                    dispose();
                }
            }
        });

        // Evento para cancelar cambios
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas cancelar la modificación del pedido?",
                        "Cancelar modificación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
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
                        double monto = listaAutopartes.pedirAutoparte(selectedAutoparte.getCodigo(), cantidad);
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

