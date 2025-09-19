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

    public void mostrarCadenasEnTabla() {

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

    public JMenuItem getBtnAcercaDe() {
        return ItemAcercaDe;
    }

    public JButton getBtnProbartxt() {
        return btnProbartxt;
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
        jLabel6 = new javax.swing.JLabel();
        scrollCadenas = new javax.swing.JScrollPane();
        contenedorTablaCadenasAnalizar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnProbartxt = new javax.swing.JButton();
        MenuBarra = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        ItemNuevo = new javax.swing.JMenuItem();
        ItemAbrir = new javax.swing.JMenuItem();
        ItemCerrar = new javax.swing.JMenuItem();
        ItemGuardar = new javax.swing.JMenuItem();
        ItemGuardarComo = new javax.swing.JMenuItem();
        MenuEjemplos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        MenuInformacion = new javax.swing.JMenu();
        ItemAcercaDe = new javax.swing.JMenuItem();
        ItemManualTec = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 720));

        ContenedorPrincipal.setBackground(new java.awt.Color(236, 239, 241));

        ContenedorScrollP.setBackground(new java.awt.Color(204, 204, 204));
        ContenedorScrollP.setForeground(new java.awt.Color(204, 204, 204));

        Areatxt.setBackground(new java.awt.Color(255, 255, 255));
        Areatxt.setColumns(20);
        Areatxt.setRows(5);
        ContenedorScrollP.setViewportView(Areatxt);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Estado Inicial");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Área de Texto");

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Estados");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Símbolos");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Transiciones");

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Cadenas a Analizar");

        javax.swing.GroupLayout contenedorTablaCadenasAnalizarLayout = new javax.swing.GroupLayout(contenedorTablaCadenasAnalizar);
        contenedorTablaCadenasAnalizar.setLayout(contenedorTablaCadenasAnalizarLayout);
        contenedorTablaCadenasAnalizarLayout.setHorizontalGroup(
            contenedorTablaCadenasAnalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        contenedorTablaCadenasAnalizarLayout.setVerticalGroup(
            contenedorTablaCadenasAnalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );

        scrollCadenas.setViewportView(contenedorTablaCadenasAnalizar);

        jButton1.setText("Probar Cadena");

        btnProbartxt.setText("Probar txt");

        javax.swing.GroupLayout ContenedorPrincipalLayout = new javax.swing.GroupLayout(ContenedorPrincipal);
        ContenedorPrincipal.setLayout(ContenedorPrincipalLayout);
        ContenedorPrincipalLayout.setHorizontalGroup(
            ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedorPrincipalLayout.createSequentialGroup()
                        .addComponent(ContenedorScrollP, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                        .addGap(38, 38, 38)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstadoIncial, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(143, 143, 143)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(scrollCadenas, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(101, 101, 101))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(btnProbartxt)
                            .addComponent(scrollPaneTransiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollPaneEstadosAceptacion, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(scrollPaneSimbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel4)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        ContenedorPrincipalLayout.setVerticalGroup(
            ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedorPrincipalLayout.createSequentialGroup()
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEstadoIncial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ContenedorScrollP, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProbartxt)
                        .addGap(35, 35, 35)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneEstadosAceptacion, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPaneSimbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollCadenas, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(scrollPaneTransiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MenuBarra.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MenuBarra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuFile.setText("File");

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

        jMenuItem1.setText("Ejemplo1");
        MenuEjemplos.add(jMenuItem1);

        jMenuItem2.setText("Ejemplo2");
        MenuEjemplos.add(jMenuItem2);

        jMenuItem3.setText("Ejemplo3");
        MenuEjemplos.add(jMenuItem3);

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
            .addComponent(ContenedorPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    public javax.swing.JMenuItem ItemGuardar;
    public javax.swing.JMenuItem ItemGuardarComo;
    private javax.swing.JMenuItem ItemManualTec;
    public javax.swing.JMenuItem ItemNuevo;
    private javax.swing.JMenuBar MenuBarra;
    private javax.swing.JMenu MenuEjemplos;
    public javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuInformacion;
    private javax.swing.JButton btnProbartxt;
    private javax.swing.JPanel contenedorTablaCadenasAnalizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane scrollCadenas;
    public javax.swing.JScrollPane scrollPaneEstadosAceptacion;
    public javax.swing.JScrollPane scrollPaneSimbolos;
    public javax.swing.JScrollPane scrollPaneTransiciones;
    public javax.swing.JTable tblEstados;
    public javax.swing.JTable tblSimbolos;
    public javax.swing.JTable tblTransiciones;
    public javax.swing.JTextField txtEstadoIncial;
    // End of variables declaration//GEN-END:variables

}
