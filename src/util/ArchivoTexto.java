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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jhosu
 */
public class ArchivoTexto {

    static File archivoAbierto;

    public static String abrirArchivotxt() {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filtro);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            archivoAbierto = archivo;
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

                StringBuilder contenido = new StringBuilder();
                String linea;

                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                return contenido.toString();

            } catch (IOException e) {
                e.printStackTrace();
                mostrarError("No se puede abrir el archivo, el tipo de archivo no es compatible", "Erro al abrir el archivo");
            }

        }

        return null;

    }

    public static void guardarArchivo(String contenido) {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filtro);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new File(archivo.getAbsolutePath() + ".txt");

            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(contenido);
                archivoAbierto = archivo;
                bw.close();
            } catch (IOException e) {
                mostrarError("No se pudo guardar el archivo", "Error al guardar el archivo");
            }
        }

    }

    public static void sobreEscribirArchivo(String contenido) {

        if (archivoAbierto == null) {
            guardarArchivo(contenido);
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAbierto))) {
                bw.write(contenido);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void nuevoArchivo(String contenido) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de crear un nuevo archivo?", "Nuevo Archivo", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirmacion == JOptionPane.CANCEL_OPTION) {
            return;
        }

        if (confirmacion == JOptionPane.YES_OPTION) {
            sobreEscribirArchivo(contenido);
        }

        archivoAbierto = null;

    }

    public static void mostrarError(String mensaje, String nombre) {
        JOptionPane.showMessageDialog(null, mensaje, nombre, JOptionPane.ERROR_MESSAGE);

    }
    
    public static void recetearReferenciaArchivo(){
        archivoAbierto = null;
    }

}
