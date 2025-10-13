/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private List<String> cadenasList;
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

            String cadenas = ArchivoTexto.extraerValorPatronEnArchivo(contenido, "Cadenas a analizar:");
            this.cadenasList = procesarLista(cadenas);

            
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
        System.out.println("ProcesarTransicciones " + transicionesStr);
        List<String[]> transiciones = new ArrayList<>();
        if (transicionesStr != null) {
            String[] lineas = transicionesStr.split("\n");
            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length == simbolosList.size()) {
                    transiciones.add(partes);
                }
            }
        }
        return transiciones;
    }

    private String[][] crearMatrizTransiciones() {
        System.out.println("Incio del metodo crear Matriz");
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

        for (int fila = 0; fila < estadosList.size(); fila++) {
            String[] destinos = transicionesList.get(fila);
            for (int columna = 0; columna < destinos.length; columna++) {
                matriz[fila][columna] = destinos[columna].trim();
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

    public List<String> getCadenasList() {
        return cadenasList;
    }

    
    
    
}
