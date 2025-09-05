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
