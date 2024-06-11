package Programa.View.Pedido;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Programa.Model.Pedido;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaClientes;
import Programa.Model.ListaPedidos;
import Programa.Model.ListaVentas;
import Programa.View.Cliente.modificarCliente;

public class buscarPedido extends JFrame {
    private ListaPedidos listaPedidos;
    
    public buscarPedido(ListaAutopartes listaAutopartes, ListaPedidos listaPedidos, ListaClientes listaClientes, ListaVentas listaVentas) {
        this.listaPedidos = listaPedidos;
        
       //crea el frame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //crear componentes
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtIdPedido = new JTextField();

        //asignar valor a los componentes
        panel.add(new JLabel("ID Pedido:"));
        panel.add(txtIdPedido);
      
        JButton btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar);

        add(panel);
        setVisible(true);

        //llamar al metodo para crear cliente al hacer click en guardar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPedido = Integer.parseInt(txtIdPedido.getText());
                Pedido p = listaPedidos.pedidoConId(idPedido);
                if (p!=null) {
                    new modificarPedido( listaAutopartes,  listaPedidos,  listaClientes,  listaVentas,  p).setVisible(true);
                }else {
                	JOptionPane.showMessageDialog(null,
                            "Pedido con ID " + idPedido + " no encontrado.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });
    }
   }