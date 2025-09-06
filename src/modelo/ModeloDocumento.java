/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.ArchivoTexto;
import vista.VistaPrincipal;

/**
 *
 * @author jhosu
 */
public class ModeloDocumento {

    VistaPrincipal vistaInicioLector;
    private String estadoInicial;
    private String contenido;
    private String estadosAceptacion;
    private File archivoActual;

    private List<String> estadosAceptacionList;
    private List<String> simbolosList;
    private List<String> estadosList;
    private List<String[]> transicionesList;
    private String[][] matrizTransiciones;

    public void cargarDatosDesdeArchivo(File archivo) throws IOException {
        this.archivoActual = archivo;
        this.contenido = ArchivoTexto.abrirArchivotxt(archivo);
        this.estadoInicial = ArchivoTexto.extraerValorPatronEnArchivo(contenido, "Estado inicial:");
        this.extrarDatosEspecificos();

    }

    public void guardarDatosArchivo(File archivo, String nuevoContenido) throws IOException {

        ArchivoTexto.guardarArchivo(archivo, nuevoContenido);
        this.contenido = nuevoContenido;
        this.archivoActual = archivo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public File getArchivoActual() {
        return archivoActual;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    private void extrarDatosEspecificos() {

        if (contenido != null) {
            String estadosStr = ArchivoTexto.extraerValorPatronEnArchivo(contenido, "Estados:");
            this.estadosList = procesarLista(estadosStr);
            System.out.println("Estados en modelo: " + estadosStr);
            
            
            String simboloStr = ArchivoTexto.extraerValorPatronEnArchivo(contenido, "Simbolos:");
            this.simbolosList = procesarLista(simboloStr);

            String transicionesStr = ArchivoTexto.extraerBloqueTransiciones(contenido);
            this.transicionesList = procesarTransiciones(transicionesStr);
            
            this.matrizTransiciones = crearMatrizTransiciones();
        }
    }

    private List<String> procesarLista(String texto) {
        List<String> items = new ArrayList<>();

        if (texto != null && !texto.isEmpty()) {
            String[] array = texto.split(",");
            for (String item : array) {
                String itemLimpio = item.trim();
                if (!itemLimpio.isEmpty()) {
                    items.add(itemLimpio);
                }

            }
        }
        return items;
    }
    
    private List<String[]> procesarTransiciones(String transicionesStr) {
        List<String[]> transiciones = new ArrayList<>();
        if (transicionesStr != null) {
            String[] lineas = transicionesStr.split("\n");
            for (String linea : lineas) {
                if (linea.contains(",")) {
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        transiciones.add(new String[]{partes[0].trim(), partes[1].trim()});
                    }
                }
            }
        }
        return transiciones;
    }
    
     private String[][] crearMatrizTransiciones() {
        if (estadosList.isEmpty() || simbolosList.isEmpty()) {
            return new String[0][0];
        }
        
        String[][] matriz = new String[estadosList.size()][simbolosList.size()];
        
        // Inicializar matriz con "-" o vacío
        for (int i = 0; i < estadosList.size(); i++) {
            for (int j = 0; j < simbolosList.size(); j++) {
                matriz[i][j] = "-"; // Valor por defecto
            }
        }
        
        // Llenar matriz con transiciones
        for (String[] transicion : transicionesList) {
            String estadoOrigen = transicion[0];
            String[] destinoSimbolo = transicion[1].split("\\|"); // Suponiendo formato "Q1|0"
            
            if (destinoSimbolo.length == 2) {
                String estadoDestino = destinoSimbolo[0].trim();
                String simbolo = destinoSimbolo[1].trim();
                
                int fila = estadosList.indexOf(estadoOrigen);
                int columna = simbolosList.indexOf(simbolo);
                
                if (fila >= 0 && columna >= 0) {
                    matriz[fila][columna] = estadoDestino;
                }
            }
        }
        
        return matriz;
    }

    public List<String> getEstadosAceptacionList() {
        return estadosAceptacionList;
    }

    public List<String> getSimbolosList() {
        return simbolosList;
    }

    public List<String> getEstadosList() {
        return estadosList;
    }

    public String[][] getMatrizTransiciones() {
        return matrizTransiciones;
    }

    public ModeloDocumento() {
    }

    public ModeloDocumento(VistaPrincipal vistaInicioLector) {
        this.vistaInicioLector = vistaInicioLector;
    }

    public VistaPrincipal getVistaInicioLector() {
        return vistaInicioLector;
    }

    public void setVistaInicioLector(VistaPrincipal vistaInicioLector) {
        this.vistaInicioLector = vistaInicioLector;
    }

}
