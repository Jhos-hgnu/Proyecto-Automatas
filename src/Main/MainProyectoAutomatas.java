/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import controlador.ControladorDocumento;
import javax.swing.SwingUtilities;
import modelo.ModeloDocumento;
import vista.VistaPrincipal;

/**
 *
 * @author jhosu
 */
public class MainProyectoAutomatas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            
            ModeloDocumento modelo = new ModeloDocumento();
            VistaPrincipal vista = new VistaPrincipal();
            
            new ControladorDocumento(modelo, vista);
            
            vista.setVisible(true);
            
        });
       
        
    }
    
}
