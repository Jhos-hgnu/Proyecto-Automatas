/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import vista.VistaPrincipal;

/**
 *
 * @author jhosu
 */
public class ModeloVentanaPrincipal {

    VistaPrincipal vistaInicioLector;
   
    
    public ModeloVentanaPrincipal() {
    }

    public ModeloVentanaPrincipal(VistaPrincipal vistaInicioLector) {
        this.vistaInicioLector = vistaInicioLector;
    }

    public VistaPrincipal getVistaInicioLector() {
        return vistaInicioLector;
    }

    public void setVistaInicioLector(VistaPrincipal vistaInicioLector) {
        this.vistaInicioLector = vistaInicioLector;
    }
    
    
    
}
