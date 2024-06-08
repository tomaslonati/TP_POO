package Programa.View.Autoparte;

import Programa.Model.Autoparte;
import Programa.Model.ListaAutopartes;
import Programa.Model.ListaPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Programa.View.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class buscarAutoparte extends JFrame {
    private ListaAutopartes listaAutopartes;
    
    public buscarAutoparte(ListaAutopartes listaAutopartes, ListaPedidos listaPedidos) {
        this.listaAutopartes = listaAutopartes;
        
        // Crear frame
        setTitle("Buscar Autoparte");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear componentes
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtCodigoAutoparte = new JTextField();

        // Asignar valor a los componentes
        panel.add(new JLabel("Código Autoparte:"));
        panel.add(txtCodigoAutoparte);
      
        JButton btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar);

        add(panel);
        setVisible(true);

        // Llamar al método para buscar autoparte al hacer clic en buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigoAutoparte = Integer.parseInt(txtCodigoAutoparte.getText());
                    Autoparte autoparte = listaAutopartes.autoparteConCodigo(codigoAutoparte);
                    if (autoparte != null) {
                        new modificarAutoparte(listaAutopartes, listaPedidos, codigoAutoparte).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Autoparte con código " + codigoAutoparte + " no encontrada.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, ingrese un código de autoparte válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

