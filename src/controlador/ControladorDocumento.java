/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.ModeloDocumento;
import util.ArchivoTexto;
import vista.VistaAcercaDe;
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

    //Control de botones
    private void configurarListeners() {
        vista.getBtnAbrir().addActionListener(e -> abrirArchivo());
        vista.getBtnGuardar().addActionListener(e -> guardarArchivo());
        vista.getBtnAcercaDe().addActionListener(e -> mostrarInformacionAcercaDe());
        vista.getBtnProbartxt().addActionListener(e -> mostrarDatosTablas());
    }

    public void abrirArchivo() {
        File archivo = ArchivoTexto.seleccionarArchivoAbrir();
        System.out.println("ABRIR");
        if (archivo != null) {
            try {
                modelo.cargarDatosDesdeArchivo(archivo);

                vista.mostrarContenido(modelo.getContenido());
//                vista.mostrarEstadoInicial(modelo.getEstadoInicial());
//                vista.mostrarEstadosAceptacionTabla(modelo.getEstadosList());
//                vista.mostrarSimboloEnTabla(modelo.getSimbolosList());
//                
//                vista.mostrarTransicionesEnTabla(
//                    modelo.getEstadosList(), 
//                    modelo.getSimbolosList(), 
//                    modelo.getMatrizTransiciones()
//                );

            } catch (IOException e) {

                JOptionPane.showMessageDialog(vista, "Error al abrir archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void guardarArchivo() {
        try {
            String contenido = vista.obtenerContenido();
            System.out.println("GUARDAR");
            File archivoActual = modelo.getArchivoActual();
            if (archivoActual != null) {
                modelo.guardarDatosArchivo(archivoActual, contenido);
                JOptionPane.showMessageDialog(vista, "Archivo Guardado", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                  guardarComoArchivo();
//                JOptionPane.showMessageDialog(vista, "No hay ningun archivo abierto", "Archivo no seleccionado", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarComoArchivo() {
        try {
            String contenido = vista.obtenerContenido();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como");
            int seleccion = fileChooser.showSaveDialog(vista);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();

                if (!archivoSeleccionado.getName().toLowerCase().endsWith(".txt")) {
                    archivoSeleccionado = new File(archivoSeleccionado.getAbsolutePath() + ".txt");
                }
                modelo.guardarDatosArchivo(archivoSeleccionado, contenido);

                JOptionPane.showMessageDialog(vista, "Archivo guardado como: " + archivoSeleccionado.getName(),
                        "Guardar como", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(vista, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void mostrarDatosTablas() {

        if (vista.Areatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Debe de seleccionar algún archivo", "Documento no seleccionado", JOptionPane.ERROR_MESSAGE);
            //ArchivoTexto.archivoAbierto
        } else {

            try {
                modelo.cargarDatosDesdeArchivo(modelo.getArchivoActual());

                vista.mostrarContenido(modelo.getContenido());

                limpiarElementos();
                vista.mostrarEstadoInicial(modelo.getEstadoInicial());
                vista.mostrarEstadosAceptacionTabla(modelo.getEstadosList());
                vista.mostrarSimboloEnTabla(modelo.getSimbolosList());

                vista.mostrarTransicionesEnTabla(
                        modelo.getEstadosList(),
                        modelo.getSimbolosList(),
                        modelo.getMatrizTransiciones()
                );

            } catch (IOException e) {
                JOptionPane.showMessageDialog(vista, "Error al leer el archivo", "Error de lectura", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    public void mostrarInformacionAcercaDe() {
        VistaAcercaDe vistaInfo = new VistaAcercaDe();
        vistaInfo.setVisible(true);

    }

    //Método para limpiar elementos gráficos
    public void limpiarElementos() {
        vista.txtEstadoIncial.revalidate();
        vista.scrollPaneEstadosAceptacion.revalidate();
        vista.scrollPaneSimbolos.revalidate();
        vista.scrollPaneTransiciones.revalidate();
        vista.scrollPaneEstadosAceptacion.repaint();
        vista.scrollPaneSimbolos.repaint();
        vista.scrollPaneTransiciones.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
