/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.ModeloDocumento;

/**
 *
 * @author jhosu
 */
public class ArchivoTexto {

    static File archivoAbierto;

    public static String abrirArchivotxt(File archivo) throws IOException {

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

        }

        return contenido.toString();

    }

    public static void guardarArchivo(File archivo, String contenido) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido);

        }

    }

//    public static void sobreEscribirArchivo(String contenido) {
//
//        if (archivoAbierto == null) {
//            guardarArchivo(contenido);
//        } else {
//            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAbierto))) {
//                bw.write(contenido);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static File seleccionarArchivoAbrir() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");

        fileChooser.setFileFilter(filtro);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    public static String extraerValorPatronEnArchivo(String contenido, String patron) {

        String[] lineas = contenido.split("\n");
        for (String linea : lineas) {
            if (linea.trim().startsWith(patron)) {
                return linea.substring(patron.length()).trim();
            }
        }
        return "";

    }

    public static void mostrarError(String mensaje, String nombre) {
        JOptionPane.showMessageDialog(null, mensaje, nombre, JOptionPane.ERROR_MESSAGE);

    }

    public static void recetearReferenciaArchivo() {
        archivoAbierto = null;
    }

}
