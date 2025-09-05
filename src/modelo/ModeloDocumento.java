/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.IOException;
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
    
    
    public void cargarDatosDesdeArchivo(File archivo) throws IOException {
        this.archivoActual = archivo;
        this.contenido = ArchivoTexto.abrirArchivotxt(archivo);
        this.estadoInicial = ArchivoTexto.extraerValorPatronEnArchivo(contenido, "Estado inicial:");
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
