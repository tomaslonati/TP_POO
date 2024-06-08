package Programa.View.Pedido;

import Programa.Model.ListaPedidos;
import Programa.Model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class listadoPedidos extends JFrame {
    private ListaPedidos listaPedidos;
    private DefaultTableModel tableModel;

    public listadoPedidos(ListaPedidos listaPedidos) {
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
    }

    public static void main(String[] args) {
        // Este es solo un ejemplo de cómo inicializar la lista de pedidos
        ListaPedidos listaPedidos = new ListaPedidos();

        // Crear instancia de la ventana para ver el listado de pedidos
        new listadoPedidos(listaPedidos);
    }
}
