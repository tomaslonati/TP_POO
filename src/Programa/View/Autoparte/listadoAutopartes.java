package Programa.View.Autoparte;

import Programa.Model.Autoparte;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class listadoAutopartes extends JFrame {
    private ListaAutopartes listaAutopartes;

    public listadoAutopartes(ListaAutopartes listaAutopartes, ListaPedidos listaPedidos) {
        this.listaAutopartes = listaAutopartes;
        
        // Crear frame para mostrar la lista de autopartes
        setTitle("Lista de Autopartes");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        List<Autoparte> autopartes = listaAutopartes.obtenerAutopartes();
        
        // Crear panel para el título y subtítulo
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Lista de Autopartes");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Haga doble click en la autoparte para modificar");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitulo.add(titulo);
        panelTitulo.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre título y subtítulo
        panelTitulo.add(subtitulo);
        
        add(panelTitulo, BorderLayout.NORTH);
        
        // Crear columnas para la tabla
        String[] columnas = {"Código", "Denominación", "Descripción", "Categoría", "Precio por unidad", "Enlace", "Stock actual", "Stock mínimo", "Marca", "Modelo", "Vehículo"};
     
        // Crear modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Agregar filas al modelo de tabla
        for (Autoparte autoparte : autopartes) {
            Object[] fila = {
                autoparte.getCodigo(),
                autoparte.getDenominacion(),
                autoparte.getDescripcion(),
                autoparte.getCategoria(),
                autoparte.getPrecioUnitario(),
                autoparte.getEnlace(),
                autoparte.getCantidadEnStock(),
                autoparte.getStockMinimo(),
                autoparte.getMarca(),
                autoparte.getModelo(),
                autoparte.getVehiculo()
            };
            tableModel.addRow(fila);
        }
		
        // Crear tabla
        JTable tablaAutopartes = new JTable(tableModel);
        
        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(tablaAutopartes);
        add(scrollPane, BorderLayout.CENTER);
        
        // Agregar MouseListener para capturar eventos de doble clic
        tablaAutopartes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tablaAutopartes.getSelectedRow();
                    if (row != -1) {
                    	int codigo = (int) tablaAutopartes.getValueAt(row, 0);
                        new modificarAutoparte(listaAutopartes, listaPedidos, codigo).setVisible(true);
                        dispose();
                    }
                }
            }
        });

        setVisible(true);
    }
}
