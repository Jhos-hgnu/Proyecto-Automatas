/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Image;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import util.GraphvizGenerator;

/**
 *
 * @author jhosu
 */

public class ControladorGraphviz {

    private String rutaGraphviz = "C:/Program Files/Graphviz/bin/dot.exe";
    private JLabel labelImagen;

    private Map<String, String> transicionesUsadas;
    
    
    public ControladorGraphviz(JLabel labelImagen) {
        this.labelImagen = labelImagen;
        this.transicionesUsadas = new HashMap<>();
    }
    
      public void setLabelImagen(JLabel labelImagen) {
        this.labelImagen = labelImagen;
    }
    
   
    public void actualizarVisualizacion(String codigoDot) {
        // 1. Guardar archivo .dot
        guardarArchivoDot(codigoDot, "automata.dot");

        // 2. Generar imagen
        generarImagen("automata.dot", "automata.png");

        // 3. Mostrar imagen en la interfaz
        mostrarImagenEnVista("automata.png");
    }

    public void actualizarVisualizacionDesdeMatriz(String estadoInicial, List<String> estadosAceptacion,
            List<String> estados, List<String> simbolos,
            String[][] matrizTransiciones, String estadoActual) {

        String codigoDot = GraphvizGenerator.generarCodigoDotDesdeMatriz(
                estadoInicial, estadosAceptacion, estados, simbolos,
                matrizTransiciones, estadoActual, transicionesUsadas);

        actualizarVisualizacion(codigoDot);
    }

    private void guardarArchivoDot(String contenido, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarImagen(String archivoDot, String archivoImagen) {
        try {
            String comando = String.format("\"%s\" -Tpng %s -o %s",
                    rutaGraphviz, archivoDot, archivoImagen);

            Process process = Runtime.getRuntime().exec(comando);
            process.waitFor(); // Esperar a que termine la generación

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al generar imagen. Verifica que Graphviz esté instalado en: " + rutaGraphviz);
        }
    }

    private void mostrarImagenEnVista(String archivoImagen) {
        if (labelImagen == null) {
            System.out.println("ADVERTENCIA: JLabel es null en GraphvizController");
            return;
        }
        
        try {
            ImageIcon icon = new ImageIcon(archivoImagen);
            Image image = icon.getImage().getScaledInstance(585, 300, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(image));
            System.out.println("Imagen actualizada en Graphviz");
        } catch (Exception e) {
            System.out.println("Error al cargar imagen: " + e.getMessage());
        }
    
    }

    public void registrarTransicionUsada(String key) {
        if (transicionesUsadas == null) {
            transicionesUsadas = new HashMap<>();
        }
        transicionesUsadas.put(key, "usada");
        System.out.println(" Transición registrada: " + key);

    }

    public void limpiarTransicionesUsadas() {
        if (transicionesUsadas != null) {
            transicionesUsadas.clear();
        }
    }

}
