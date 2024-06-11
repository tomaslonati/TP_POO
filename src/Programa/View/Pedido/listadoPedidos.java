package Programa.View.Pedido;

import Programa.Model.ListaAutopartes;
import Programa.Model.ListaClientes;
import Programa.Model.ListaPedidos;
import Programa.Model.ListaVentas;
import Programa.Model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class listadoPedidos extends JFrame {
    private ListaPedidos listaPedidos;
    private DefaultTableModel tableModel;

    public listadoPedidos(ListaAutopartes listaAutopartes, ListaPedidos listaPedidos, ListaClientes listaClientes, ListaVentas listaVentas) {
        this.listaPedidos = listaPedidos;

        // Configurar la ventana
        setTitle("Listado de Pedidos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel para la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());

        // Crear columnas para la tabla
        String[] columnas = {"ID Pedido", "ID Cliente", "Fecha", "Monto Total","Estado"};

        // Crear modelo de tabla
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Agregar filas al modelo de tabla
        for (Pedido pedido : listaPedidos.obtenerPedidos()) {
            Object[] fila = {
                    pedido.getIdPedido(),
                    pedido.getIdCliente(),
                    pedido.getFecha(),
                    pedido.getMontoTotal(),
                    pedido.getEstado()
            };
            tableModel.addRow(fila);
        }

        // Crear tabla
        JTable tablaPedidos = new JTable(tableModel);

        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        // Agregar el JScrollPane al panel de la tabla
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        // Añadir el panel de la tabla al frame
        add(panelTabla);

        setVisible(true);
        
        tablaPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaPedidos.getSelectedRow();
                    if (selectedRow != -1) {
                        int idPedido = Integer.parseInt(tablaPedidos.getValueAt(selectedRow, 0).toString());
                        Pedido pedido = listaPedidos.pedidoConId(idPedido);
                        if (pedido != null) {
                        	dispose();
                        	new modificarPedido(listaAutopartes, listaPedidos, listaClientes, listaVentas, pedido).setVisible(true);
                        }
                    }
                }
            }
        });
    }

}
