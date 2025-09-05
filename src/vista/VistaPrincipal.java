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

    public VistaPrincipal() {
        initComponents();
        setTitle("Automátas y Lenguajes Formales");
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

//        tablaEstadosAceptacion = new JTable(modeloTablaEstados);
//
//        tablaEstadosAceptacion.setPreferredScrollableViewportSize(new Dimension(10, 155));
//        scrollPaneEstadosAceptacion.setPreferredSize(new Dimension(250, 175));

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
            JTable tablaEstados = new JTable(modeloTablaEstados);
            JScrollPane tableScrollEstadoAcept = new JScrollPane(tablaEstados);

            TableColumnModel columnModel = tablaEstados.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(15);

            tablaEstados.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tablaEstados.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

            contenedorTablaEstadosAceptacion.add(tableScrollEstadoAcept);
            contenedorTablaEstadosAceptacion.revalidate();
            contenedorTablaEstadosAceptacion.repaint();
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
            
            JTable tablaSimbolos = new JTable(modeloTablaSimbolos);
            JScrollPane tableScrolSimbolos = new JScrollPane(tablaSimbolos);
            tablaSimbolos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tablaSimbolos.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            
            contenedorTablaSimbolos.add(tableScrolSimbolos);
            contenedorTablaSimbolos.revalidate();
            contenedorTablaSimbolos.repaint();
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

    public JMenuItem getBtnAbrir() {
        return ItemAbrir;
    }

    public JMenuItem getBtnGuardar() {
        return ItemGuardar;
    }

    public JMenuItem getBtnGuardarComo() {
        return ItemGuardarComo;
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
        scrollPaneSimbolos = new javax.swing.JScrollPane();
        contenedorTablaSimbolos = new javax.swing.JPanel();
        scrollPaneEstadosAceptacion = new javax.swing.JScrollPane();
        contenedorTablaEstadosAceptacion = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        MenuBarra = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        ItemNuevo = new javax.swing.JMenuItem();
        ItemAbrir = new javax.swing.JMenuItem();
        ItemCerrar = new javax.swing.JMenuItem();
        ItemGuardar = new javax.swing.JMenuItem();
        ItemGuardarComo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 720));

        ContenedorPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        ContenedorScrollP.setBackground(new java.awt.Color(204, 204, 204));
        ContenedorScrollP.setForeground(new java.awt.Color(204, 204, 204));

        Areatxt.setBackground(new java.awt.Color(204, 204, 204));
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

        txtEstadoIncial.setBackground(new java.awt.Color(204, 204, 204));
        txtEstadoIncial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEstadoIncial.setForeground(new java.awt.Color(0, 0, 0));
        txtEstadoIncial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstadoIncial.setEnabled(false);
        txtEstadoIncial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoIncialActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Estados de Aceptación");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Símbolos");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Transiciones");

        contenedorTablaSimbolos.setLayout(new javax.swing.BoxLayout(contenedorTablaSimbolos, javax.swing.BoxLayout.LINE_AXIS));
        scrollPaneSimbolos.setViewportView(contenedorTablaSimbolos);

        contenedorTablaEstadosAceptacion.setLayout(new javax.swing.BoxLayout(contenedorTablaEstadosAceptacion, javax.swing.BoxLayout.LINE_AXIS));
        scrollPaneEstadosAceptacion.setViewportView(contenedorTablaEstadosAceptacion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ContenedorPrincipalLayout = new javax.swing.GroupLayout(ContenedorPrincipal);
        ContenedorPrincipal.setLayout(ContenedorPrincipalLayout);
        ContenedorPrincipalLayout.setHorizontalGroup(
            ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(ContenedorScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(txtEstadoIncial, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(672, 672, 672))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPaneSimbolos, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                        .addComponent(scrollPaneTransiciones))
                                    .addComponent(scrollPaneEstadosAceptacion, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(265, 265, 265)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jLabel4))
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel5)))
                        .addContainerGap(224, Short.MAX_VALUE))))
        );
        ContenedorPrincipalLayout.setVerticalGroup(
            ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedorPrincipalLayout.createSequentialGroup()
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstadoIncial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPaneEstadosAceptacion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneSimbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPaneTransiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ContenedorScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );

        MenuBarra.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MenuBarra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuFile.setText("File");

        ItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemNuevo.setText("Nuevo");
        ItemNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemNuevo);

        ItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemAbrir.setText("Abrir");
        ItemAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemAbrir);

        ItemCerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemCerrar.setText("Cerrar");
        ItemCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemCerrar);

        ItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemGuardar.setText("Guardar");
        ItemGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemGuardar);

        ItemGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemGuardarComo.setText("Guardar Como");
        ItemGuardarComo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuFile.add(ItemGuardarComo);

        MenuBarra.add(MenuFile);

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
    public javax.swing.JMenuItem ItemCerrar;
    public javax.swing.JMenuItem ItemGuardar;
    public javax.swing.JMenuItem ItemGuardarComo;
    public javax.swing.JMenuItem ItemNuevo;
    private javax.swing.JMenuBar MenuBarra;
    public javax.swing.JMenu MenuFile;
    private javax.swing.JPanel contenedorTablaEstadosAceptacion;
    private javax.swing.JPanel contenedorTablaSimbolos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPaneEstadosAceptacion;
    private javax.swing.JScrollPane scrollPaneSimbolos;
    private javax.swing.JScrollPane scrollPaneTransiciones;
    public javax.swing.JTextField txtEstadoIncial;
    // End of variables declaration//GEN-END:variables

    public void setControlador(ControladorDocumento controlador) {
        ItemAbrir.addActionListener(controlador);
        ItemGuardarComo.addActionListener(controlador);
        ItemNuevo.addActionListener(controlador);
        ItemGuardar.addActionListener(controlador);
        ItemCerrar.addActionListener(controlador);
    }

}
