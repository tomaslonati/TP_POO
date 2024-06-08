package Programa.View.Venta;

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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nuevaVenta extends JFrame {
    private ListaAutopartes listaAutopartes;
    private DefaultTableModel tableModel;
    private ListaPedidos listaPedidos;
    private ListaClientes listaClientes;

    private JLabel labelMonto; // Declarar el JLabel fuera del constructor

    public nuevaVenta(Pedido p) {
    }

}