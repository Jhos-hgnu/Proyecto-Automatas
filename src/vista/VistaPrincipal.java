/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControladorDocumento;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.ModeloDocumento;

/**
 *
 * @author jhosu
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    private JTable tablaEstadosAceptacion;
    private JTable tablaSimbolos;
    private DefaultTableModel modeloTablaEstados;
    private DefaultTableModel modeloTablaSimbolos;
    private DefaultTableModel modeloTablaTransiciones;
    private DefaultTableModel modeloTablaCadenas;

    public VistaPrincipal() {
        initComponents();
        setTitle("Autómatas y Lenguajes Formales");
        setLocationRelativeTo(null);

        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/IconAutomatasLenguajes.png"));
        setIconImage(icono);

        //Tabla Estados de Aceptacion  
        modeloTablaEstados = new DefaultTableModel();
        modeloTablaEstados.addColumn("No");
        modeloTablaEstados.addColumn("Estados");

        //Tabla de Símbolos
        modeloTablaSimbolos = new DefaultTableModel();
        modeloTablaSimbolos.addColumn("No");
        modeloTablaSimbolos.addColumn("Símbolo");

        //Tabla de transiciones
        modeloTablaTransiciones = new DefaultTableModel();

        //Tabla de Cadenas
        modeloTablaCadenas = new DefaultTableModel();
        modeloTablaCadenas.addColumn("No");
        modeloTablaCadenas.addColumn("Cadenas a Analizar");
//        tablaEstadosAceptacion = new JTable(modeloTablaEstados);
//
//        tablaEstadosAceptacion.setPreferredScrollableViewportSize(new Dimension(10, 155));
//        scrollPaneEstadosAceptacion.setPreferredSize(new Dimension(250, 175));

        if (modeloTablaEstados.getRowCount() == 0) {
            scrollPaneTransiciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPaneTransiciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
    }

    //Limpiar y llenar la tabla
    public void mostrarEstadosAceptacionTabla(List<String> estados) {
        System.out.println(estados);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        System.out.println("Mostrar datos Tab Acept");
        modeloTablaEstados.setRowCount(0);
        if (estados != null) {
            for (int i = 0; i < estados.size(); i++) {
                modeloTablaEstados.addRow(new Object[]{i + 1, estados.get(i)});
            }

            tblEstados.setModel(modeloTablaEstados);
            tblEstados.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tblEstados.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            
        }
    }

    public void mostrarSimboloEnTabla(List<String> simbolos) {
        modeloTablaSimbolos.setRowCount(0);
        System.out.println("Simbolos del modelo " + simbolos);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        if (simbolos != null) {
            for (int i = 0; i < simbolos.size(); i++) {
                modeloTablaSimbolos.addRow(new Object[]{i + 1, simbolos.get(i)});
            }
            
            tblSimbolos.setModel(modeloTablaSimbolos);
            tblSimbolos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tblSimbolos.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

            
        }
    }

    public void mostrarCadenasEnTabla(List<String> cadenas) {
        modeloTablaCadenas.setRowCount(0);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        System.out.println("Cadenas " + cadenas);
        
        
        for(int i = 0; i < cadenas.size(); i++){
            
            modeloTablaCadenas.addRow(new Object[] {i + 1, cadenas.get(i)});
        }
        
        tblcadenas.setModel(modeloTablaCadenas);

    }

    public void mostrarTransicionesEnTabla(List<String> estados, List<String> simbolos, String[][] matrizTransiciones) {
        modeloTablaTransiciones.setRowCount(0);
        modeloTablaTransiciones.setColumnCount(0);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        if (estados != null && simbolos != null && matrizTransiciones != null) {

            //Encabezados de columnas
            modeloTablaTransiciones.addColumn("");

            for (int i = 0; i < simbolos.size(); i++) {
                modeloTablaTransiciones.addColumn(simbolos.get(i));
            }

            //Llenar filas
            for (int i = 0; i < estados.size(); i++) {
                Object[] fila = new Object[simbolos.size() + 1];
                fila[0] = estados.get(i);

                for (int j = 0; j < simbolos.size(); j++) {
                    fila[j + 1] = matrizTransiciones[i][j];
                    System.out.println("Fila: " + fila);
                }

                modeloTablaTransiciones.addRow(fila);
            }

            tblTransiciones.setModel(modeloTablaTransiciones);
            tblTransiciones.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tblTransiciones.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            tblTransiciones.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            scrollPaneTransiciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPaneTransiciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        }

    }

    //Metodos para el controlador
    public void mostrarContenido(String contenido) {
        Areatxt.setText(contenido);
    }

    public void mostrarEstadoInicial(String estado) {
        txtEstadoIncial.setText(estado);
    }

    public String obtenerContenido() {
        return Areatxt.getText();
    }

    
    //Menu File
    public JMenuItem getBtnNuevo(){
        return  ItemNuevo;
    }
    
    public JMenuItem getBtnAbrir() {
        return ItemAbrir;
    }

    public JMenuItem getBtnCerrar() {
        return ItemCerrar;
    }

    public JMenuItem getBtnGuardar() {
        return ItemGuardar;
    }

    public JMenuItem getBtnGuardarComo() {
        return ItemGuardarComo;
    }

    

    public JButton getBtnProbartxt() {
        return btnProbartxt;
    }
    
    //Menu Información
    public JMenuItem getBtnAcercaDe() {
        return ItemAcercaDe;
    }
    
    
    //Menu Ejemplos
    public JMenuItem getBtnEjemplo1(){
        return ItemEjemplo1;
    }
    
    public JMenuItem getBtnEjemplo2(){
        return ItemEjemplo2;
    }
    
    public JMenuItem getBtnEjemplo3(){
        return ItemEjemplo3;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContenedorPrincipal = new javax.swing.JPanel();
        ContenedorScrollP = new javax.swing.JScrollPane();
        Areatxt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEstadoIncial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        scrollPaneTransiciones = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransiciones = new javax.swing.JTable();
        scrollPaneSimbolos = new javax.swing.JScrollPane();
        tblSimbolos = new javax.swing.JTable();
        scrollPaneEstadosAceptacion = new javax.swing.JScrollPane();
        tblEstados = new javax.swing.JTable();
        btnProbartxt = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        scrollCadenas = new javax.swing.JScrollPane();
        tblcadenas = new javax.swing.JTable();
        MenuBarra = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuFile = new javax.swing.JMenu();
        ItemNuevo = new javax.swing.JMenuItem();
        ItemAbrir = new javax.swing.JMenuItem();
        ItemCerrar = new javax.swing.JMenuItem();
        ItemGuardar = new javax.swing.JMenuItem();
        ItemGuardarComo = new javax.swing.JMenuItem();
        MenuEjemplos = new javax.swing.JMenu();
        ItemEjemplo1 = new javax.swing.JMenuItem();
        ItemEjemplo2 = new javax.swing.JMenuItem();
        ItemEjemplo3 = new javax.swing.JMenuItem();
        MenuInformacion = new javax.swing.JMenu();
        ItemAcercaDe = new javax.swing.JMenuItem();
        ItemManualTec = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 720));

        ContenedorPrincipal.setBackground(new java.awt.Color(236, 239, 241));
        ContenedorPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ContenedorScrollP.setBackground(new java.awt.Color(204, 204, 204));
        ContenedorScrollP.setForeground(new java.awt.Color(204, 204, 204));

        Areatxt.setBackground(new java.awt.Color(255, 255, 255));
        Areatxt.setColumns(20);
        Areatxt.setRows(5);
        ContenedorScrollP.setViewportView(Areatxt);

        ContenedorPrincipal.add(ContenedorScrollP, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 47, 430, 302));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Estado Inicial");
        ContenedorPrincipal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 404, -1, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Área de Texto");
        ContenedorPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 10, 194, -1));

        txtEstadoIncial.setEditable(false);
        txtEstadoIncial.setBackground(new java.awt.Color(204, 204, 204));
        txtEstadoIncial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEstadoIncial.setForeground(new java.awt.Color(0, 0, 0));
        txtEstadoIncial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstadoIncial.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtEstadoIncial.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtEstadoIncial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoIncialActionPerformed(evt);
            }
        });
        ContenedorPrincipal.add(txtEstadoIncial, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 445, 95, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Estados");
        ContenedorPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 406, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Símbolos");
        jLabel4.setToolTipText("");
        ContenedorPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 564, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Transiciones");
        ContenedorPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 177, -1));

        tblTransiciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTransiciones);

        scrollPaneTransiciones.setViewportView(jScrollPane1);

        ContenedorPrincipal.add(scrollPaneTransiciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 674, 125));

        tblSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollPaneSimbolos.setViewportView(tblSimbolos);

        ContenedorPrincipal.add(scrollPaneSimbolos, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 595, 275, 114));

        tblEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollPaneEstadosAceptacion.setViewportView(tblEstados);

        ContenedorPrincipal.add(scrollPaneEstadosAceptacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 437, 275, 115));

        btnProbartxt.setText("Probar txt");
        ContenedorPrincipal.add(btnProbartxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 355, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Cadenas a Analizar");

        jButton1.setText("Probar Cadena");

        tblcadenas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollCadenas.setViewportView(tblcadenas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(scrollCadenas, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollCadenas, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ContenedorPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 715));

        MenuBarra.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MenuBarra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("Simulador de Autómatas     ");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBarra.add(jMenu1);

        MenuFile.setText("File");
        MenuFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemNuevo.setText("Nuevo txt");
        ItemNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemNuevo);

        ItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemAbrir.setText("Abrir txt");
        ItemAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemAbrir);

        ItemCerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemCerrar.setText("Cerrar txt");
        ItemCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemCerrar);

        ItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemGuardar.setText("Guardar txt");
        ItemGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemGuardar);

        ItemGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemGuardarComo.setText("Guardar Como");
        ItemGuardarComo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemGuardarComo);

        MenuBarra.add(MenuFile);

        MenuEjemplos.setText("Ejemplos");
        MenuEjemplos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ItemEjemplo1.setText("Ejemplo1");
        MenuEjemplos.add(ItemEjemplo1);

        ItemEjemplo2.setText("Ejemplo2");
        MenuEjemplos.add(ItemEjemplo2);

        ItemEjemplo3.setText("Ejemplo3");
        MenuEjemplos.add(ItemEjemplo3);

        MenuBarra.add(MenuEjemplos);

        MenuInformacion.setText("Información");

        ItemAcercaDe.setText("Acerca de:");
        MenuInformacion.add(ItemAcercaDe);

        ItemManualTec.setText("Manual Técnico");
        MenuInformacion.add(ItemManualTec);

        jMenuItem6.setText("Manual Usuario");
        MenuInformacion.add(jMenuItem6);

        MenuBarra.add(MenuInformacion);

        setJMenuBar(MenuBarra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedorPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1520, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedorPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEstadoIncialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoIncialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoIncialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea Areatxt;
    private javax.swing.JPanel ContenedorPrincipal;
    private javax.swing.JScrollPane ContenedorScrollP;
    public javax.swing.JMenuItem ItemAbrir;
    private javax.swing.JMenuItem ItemAcercaDe;
    public javax.swing.JMenuItem ItemCerrar;
    private javax.swing.JMenuItem ItemEjemplo1;
    private javax.swing.JMenuItem ItemEjemplo2;
    private javax.swing.JMenuItem ItemEjemplo3;
    public javax.swing.JMenuItem ItemGuardar;
    public javax.swing.JMenuItem ItemGuardarComo;
    private javax.swing.JMenuItem ItemManualTec;
    public javax.swing.JMenuItem ItemNuevo;
    private javax.swing.JMenuBar MenuBarra;
    private javax.swing.JMenu MenuEjemplos;
    public javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuInformacion;
    private javax.swing.JButton btnProbartxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane scrollCadenas;
    public javax.swing.JScrollPane scrollPaneEstadosAceptacion;
    public javax.swing.JScrollPane scrollPaneSimbolos;
    public javax.swing.JScrollPane scrollPaneTransiciones;
    public javax.swing.JTable tblEstados;
    public javax.swing.JTable tblSimbolos;
    public javax.swing.JTable tblTransiciones;
    private javax.swing.JTable tblcadenas;
    public javax.swing.JTextField txtEstadoIncial;
    // End of variables declaration//GEN-END:variables

}
