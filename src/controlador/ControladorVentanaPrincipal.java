/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ModeloVentanaPrincipal;
import util.ArchivoTexto;

/**
 *
 * @author jhosu
 */
public class ControladorVentanaPrincipal implements ActionListener {

    ModeloVentanaPrincipal modelo;

    public ControladorVentanaPrincipal(ModeloVentanaPrincipal modelo) {
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == modelo.getVistaInicioLector().ItemGuardarComo) {
            ArchivoTexto.guardarArchivo(modelo.getVistaInicioLector().Areatxt.getText());
            
        } else if (e.getSource() == modelo.getVistaInicioLector().ItemAbrir) {
            String textoLeido = ArchivoTexto.abrirArchivotxt();
            
            if (textoLeido != null) {
                modelo.getVistaInicioLector().Areatxt.setText(textoLeido);
            }

        } else if (e.getSource() == modelo.getVistaInicioLector().ItemGuardar) {
            ArchivoTexto.sobreEscribirArchivo(modelo.getVistaInicioLector().Areatxt.getText());
            
        } else if (e.getSource() == modelo.getVistaInicioLector().ItemNuevo) {
            ArchivoTexto.nuevoArchivo(modelo.getVistaInicioLector().Areatxt.getText());
            modelo.getVistaInicioLector().Areatxt.setText("");
        } else if(e.getSource() == modelo.getVistaInicioLector().ItemCerrar){
            modelo.getVistaInicioLector().Areatxt.setText("");
            ArchivoTexto.recetearReferenciaArchivo();
        }

    }

    private void mostrarMensaje(String mensaje, String nombre) {
        JOptionPane.showMessageDialog(null, mensaje, nombre, JOptionPane.INFORMATION_MESSAGE);
    }

}
