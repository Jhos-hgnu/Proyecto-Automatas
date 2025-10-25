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
    
    private String rutaGraphviz;
    private JLabel labelImagen;
    private Map<String, String> transicionesUsadas;
    private String directorioTemporal;
    
    public ControladorGraphviz(JLabel labelImagen) {
        this.labelImagen = labelImagen;
        this.transicionesUsadas = new HashMap<>();
        this.directorioTemporal = System.getProperty("java.io.tmpdir");
        this.rutaGraphviz = detectarGraphviz();
        
        System.out.println("ControladorGraphviz inicializado");
        System.out.println("Directorio temporal: " + directorioTemporal);
        System.out.println("Ruta Graphviz: " + rutaGraphviz);
    }
    
    //Detectar Graphviz automáticamente
    private String detectarGraphviz() {
        String[] posiblesRutas = {
            "dot", // En PATH (Linux/Mac)
            "C:/Program Files/Graphviz/bin/dot.exe",
            "C:/Program Files (x86)/Graphviz/bin/dot.exe", 
            "/usr/bin/dot",
            "/usr/local/bin/dot"
        };
        
        for (String ruta : posiblesRutas) {
            if (verificarGraphviz(ruta)) {
                System.out.println("Graphviz encontrado en: " + ruta);
                return ruta;
            }
        }
        
        System.err.println("Graphviz no encontrado en rutas comunes");
        return null;
    }
    
    //Verificar si Graphviz funciona
    private boolean verificarGraphviz(String ruta) {
        try {
            ProcessBuilder pb = new ProcessBuilder(ruta, "-V");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            
            // Leer output para debug
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   Graphviz test: " + line);
            }
            
            int exitCode = process.waitFor();
            return exitCode == 0;
            
        } catch (Exception e) {
            System.out.println("   Graphviz no funciona en: " + ruta + " - " + e.getMessage());
            return false;
        }
    }
    
    public void setLabelImagen(JLabel labelImagen) {
        this.labelImagen = labelImagen;
    }
    
    //Usar rutas temporales absolutas
    public void actualizarVisualizacion(String codigoDot) {
        if (rutaGraphviz == null) {
            mostrarErrorGraphvizNoEncontrado();
            return;
        }
        
        try {
            //Usar nombres únicos para evitar conflictos
            String timestamp = String.valueOf(System.currentTimeMillis());
            String archivoDot = directorioTemporal + File.separator + "automata_" + timestamp + ".dot";
            String archivoImagen = directorioTemporal + File.separator + "automata_" + timestamp + ".png";
            
            System.out.println("Guardando DOT en: " + archivoDot);
            System.out.println("Generando imagen en: " + archivoImagen);
            
            guardarArchivoDot(codigoDot, archivoDot);
            generarImagen(archivoDot, archivoImagen);
            mostrarImagenEnVista(archivoImagen);
            
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensajeError("Error al generar visualización: " + e.getMessage());
        }
    }

    public void actualizarVisualizacionDesdeMatriz(String estadoInicial, List<String> estadosAceptacion,
            List<String> estados, List<String> simbolos,
            String[][] matrizTransiciones, String estadoActual) {

        if (rutaGraphviz == null) {
            mostrarErrorGraphvizNoEncontrado();
            return;
        }

        String codigoDot = GraphvizGenerator.generarCodigoDotDesdeMatriz(
                estadoInicial, estadosAceptacion, estados, simbolos,
                matrizTransiciones, estadoActual, transicionesUsadas);

        actualizarVisualizacion(codigoDot);
    }

    //Guardar con rutas absolutas
    private void guardarArchivoDot(String contenido, String rutaCompleta) throws IOException {
        File archivo = new File(rutaCompleta);
        // Crear directorios si no existen
        archivo.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Archivo DOT guardado: " + rutaCompleta);
            System.out.println("Tamaño: " + archivo.length() + " bytes");
        }
    }

    //Mejor manejo de procesos
    private void generarImagen(String archivoDot, String archivoImagen) {
        try {
            //Verificar que el archivo DOT existe y tiene contenido
            File dotFile = new File(archivoDot);
            if (!dotFile.exists() || dotFile.length() == 0) {
                System.err.println("Archivo DOT no existe o está vacío: " + archivoDot);
                return;
            }
            
            //Usar ProcessBuilder en lugar de Runtime.exec (más seguro)
            ProcessBuilder pb = new ProcessBuilder(rutaGraphviz, "-Tpng", archivoDot, "-o", archivoImagen);
            pb.redirectErrorStream(true);
            
            System.out.println("Ejecutando: " + String.join(" ", pb.command()));
            
            Process process = pb.start();
            
            //Leer output de Graphviz para debug
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                File imagenFile = new File(archivoImagen);
                if (imagenFile.exists() && imagenFile.length() > 0) {
                    System.out.println("Imagen generada exitosamente: " + archivoImagen);
                    System.out.println("   Tamaño: " + imagenFile.length() + " bytes");
                } else {
                    System.err.println("Imagen generada pero no existe o está vacía");
                }
            } else {
                System.err.println("Graphviz falló con código: " + exitCode);
                System.err.println("   Output: " + output.toString());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensajeError("Error al generar imagen: " + e.getMessage());
        }
    }

    //Mejor manejo de errores
    private void mostrarImagenEnVista(String archivoImagen) {
        if (labelImagen == null) {
            System.err.println("JLabel es null");
            return;
        }
        
        try {
            File imagenFile = new File(archivoImagen);
            if (!imagenFile.exists()) {
                System.err.println("Archivo de imagen no existe: " + archivoImagen);
                mostrarMensajeError("No se pudo generar la imagen del autómata");
                return;
            }
            
            if (imagenFile.length() == 0) {
                System.err.println("Archivo de imagen está vacío: " + archivoImagen);
                mostrarMensajeError("La imagen generada está vacía");
                return;
            }
            
            ImageIcon icon = new ImageIcon(archivoImagen);
            Image image = icon.getImage().getScaledInstance(585, 300, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(image));
            labelImagen.setText(""); // Limpiar texto
            System.out.println("Imagen mostrada en vista correctamente");
            
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + e.getMessage());
            mostrarMensajeError("Error al cargar imagen: " + e.getMessage());
        }
    }

    //Mostrar error cuando Graphviz no está disponible
    private void mostrarErrorGraphvizNoEncontrado() {
        String mensaje = "<html><div style='text-align: center; color: red;'>"
                + "Graphviz no encontrado<br>"
                + "<small>Instala Graphviz para ver el autómata</small>"
                + "</div></html>";
        
        mostrarMensajeError(mensaje);
        
       
        System.err.println("Graphviz no disponible - mostrando mensaje al usuario");
    }
    
    // Método para mostrar mensajes de error
    private void mostrarMensajeError(String mensaje) {
        if (labelImagen != null) {
            labelImagen.setIcon(null);
            labelImagen.setText(mensaje);
        }
    }

    public void registrarTransicionUsada(String key) {
        if (transicionesUsadas == null) {
            transicionesUsadas = new HashMap<>();
        }
        
        transicionesUsadas.clear();
        transicionesUsadas.put(key, "usada");
        System.out.println("Transición registrada: " + key);
    }

    public void limpiarTransicionesUsadas() {
        if (transicionesUsadas != null) {
            transicionesUsadas.clear();
            System.out.println("Transiciones limpiadas");
        }
    }
    
    // Método para verificar si Graphviz está disponible
    public boolean estaGraphvizDisponible() {
        return rutaGraphviz != null;
    }
    
    // Método para obtener la ruta detectada (para debug)
    public String getRutaGraphviz() {
        return rutaGraphviz;
    }

   
}
