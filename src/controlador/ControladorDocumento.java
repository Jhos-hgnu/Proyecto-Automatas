/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.ModeloDocumento;
import util.ArchivoTexto;
import vista.VistaPrincipal;

/**
 *
 * @author jhosu
 */
public class ControladorDocumento implements ActionListener {

    private ModeloDocumento modelo;
    private VistaPrincipal vista;

    public ControladorDocumento(ModeloDocumento modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        configurarListeners();

    }

    private void configurarListeners() {
        vista.getBtnAbrir().addActionListener(e -> abrirArchivo());
    }

    public void abrirArchivo() {
        File archivo = ArchivoTexto.seleccionarArchivoAbrir();

        if (archivo != null) {
            try {
                modelo.cargarDatosDesdeArchivo(archivo);

                vista.mostrarContenido(modelo.getContenido());
                vista.mostrarEstadoInicial(modelo.getEstadoInicial());

            } catch (IOException e) {

                JOptionPane.showMessageDialog(vista, "Error al abrir archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
