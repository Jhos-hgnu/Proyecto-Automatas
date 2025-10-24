package controlador;

import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloDocumento;
import util.ArchivoTexto;
import util.GraphvizGenerator;
import vista.VistaAcercaDe;
import vista.VistaPrincipal;

/**
 *
 * @author jhosu
 */
public class ControladorDocumento implements ActionListener {

    private ModeloDocumento modelo;
    private VistaPrincipal vista;
    private List<String> elementosCadenaActual;
    private int indiceElementoActual;
    private String cadenaSeleccionadaActual;
    private String estadoActual;
    private List<Point> caminoRecorrido;
    private ControladorGraphviz gravphvizControlador;

    public ControladorDocumento(ModeloDocumento modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.gravphvizControlador = new ControladorGraphviz(vista.lblImageAFD);
        configurarListeners();

    }

    //Control de botones
    private void configurarListeners() {
        vista.getBtnAbrir().addActionListener(e -> abrirArchivo());
        vista.getBtnGuardar().addActionListener(e -> guardarArchivo());
        vista.getBtnAcercaDe().addActionListener(e -> mostrarInformacionAcercaDe());
        vista.getBtnProbartxt().addActionListener(e -> mostrarDatosTablas());
        vista.getBtnGuardarComo().addActionListener(e -> guardarComoArchivo());
        vista.getBtnCerrar().addActionListener(e -> cerrarArchivo());
        vista.getBtnNuevo().addActionListener(e -> nuevoArchivo());
        vista.getBtnEjemplo1().addActionListener(e -> abrirArchivoEjem("src/ejemplosdata/AFD_ejemplo1.txt"));
        vista.getBtnEjemplo2().addActionListener(e -> abrirArchivoEjem("src/ejemplosdata/AFD_ejemplo2.txt"));
        vista.getBtnEjemplo3().addActionListener(e -> abrirArchivoEjem("src/ejemplosdata/AFD_ejemplo3.txt"));
        vista.getBtnProbarCadena().addActionListener(e -> probarCadena());
        vista.getBtnManualUser().addActionListener(e -> abrirPDFs("src/resources/ManualDeUsuarioSimuladorAutomatas.pdf"));
        vista.getBtnManualTec().addActionListener(e -> abrirPDFs("src/resources/ManualTecnicoSimuladorAutomatas.pdf"));
    }

    public void abrirArchivo() {
        File archivo = ArchivoTexto.seleccionarArchivoAbrir();
        System.out.println("ABRIR");
        if (archivo != null) {
            try {
                modelo.cargarDatosDesdeArchivo(archivo);
                vista.mostrarContenido(modelo.getContenido());

            } catch (IOException e) {

                JOptionPane.showMessageDialog(vista, "Error al abrir archivo", "Error Abrir-Archivo", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void abrirArchivoEjem(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        try {
            System.out.println("ruta: " + rutaArchivo);
            modelo.cargarDatosDesdeArchivo(archivo);
            vista.mostrarContenido(modelo.getContenido());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al abrir archivo", "Error Abrir-Archivo", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void guardarArchivo() {
        try {
            String contenido = vista.obtenerContenido();
            System.out.println("GUARDAR");
            File archivoActual = modelo.getArchivoActual();
            if (archivoActual != null) {
                modelo.guardarDatosArchivo(archivoActual, contenido);
                JOptionPane.showMessageDialog(vista, "Archivo Guardado", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                guardarComoArchivo();
//                JOptionPane.showMessageDialog(vista, "No hay ningun archivo abierto", "Archivo no seleccionado", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarComoArchivo() {
        try {
            String contenido = vista.obtenerContenido();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como");
            int seleccion = fileChooser.showSaveDialog(vista);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();

                if (!archivoSeleccionado.getName().toLowerCase().endsWith(".txt")) {
                    archivoSeleccionado = new File(archivoSeleccionado.getAbsolutePath() + ".txt");
                }
                modelo.guardarDatosArchivo(archivoSeleccionado, contenido);

                JOptionPane.showMessageDialog(vista, "Archivo guardado como: " + archivoSeleccionado.getName(),
                        "Guardar como", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(vista, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void cerrarArchivo() {
        DefaultTableModel modeloVacio = new DefaultTableModel();
        vista.Areatxt.setText("");
        vista.txtEstadoIncial.setText("");
        vista.tblEstados.setModel(modeloVacio);
        vista.tblSimbolos.setModel(modeloVacio);
        vista.tblTransiciones.setModel(modeloVacio);
        vista.tblcadenas.setModel(modeloVacio);
        limpiarElementosAFD();
        System.out.println("limpiar");
    }

    public void nuevoArchivo() {
        if (vista.Areatxt != null) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de crear un nuevo archivo?, el trabajo no guardado se perderá", "Nuevo txt", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_NO_OPTION) {
                cerrarArchivo();
            }
        } else {
            //cerrarArchivo();
        }
    }

    public void mostrarDatosTablas() {

        if (vista.Areatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Debe de seleccionar algún archivo", "Documento no seleccionado", JOptionPane.ERROR_MESSAGE);
            //ArchivoTexto.archivoAbierto
        } else {
            limpiarElementosAFD();
            try {
                modelo.cargarDatosDesdeArchivo(modelo.getArchivoActual());

                vista.mostrarContenido(modelo.getContenido());

                limpiarElementos();
                vista.mostrarEstadoInicial(modelo.getEstadoInicial());
                vista.mostrarEstadosAceptacionTabla(modelo.getEstadosList());
                vista.mostrarSimboloEnTabla(modelo.getSimbolosList());
                vista.mostrarCadenasEnTabla(modelo.getCadenasList());

                vista.mostrarTransicionesEnTabla(
                        modelo.getEstadosList(),
                        modelo.getSimbolosList(),
                        modelo.getMatrizTransiciones()
                );

            } catch (IOException e) {
                JOptionPane.showMessageDialog(vista, "Error al leer el archivo", "Error de lectura", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    public void mostrarInformacionAcercaDe() {
        VistaAcercaDe vistaInfo = new VistaAcercaDe();
        vistaInfo.setVisible(true);

    }

    //Metodos para mostrar cadena y pintar tabla de transiciones
    public void probarCadena() {

        String cadenaSeleccionada = vista.obtenerCadenaSeleccionada();
        if (cadenaSeleccionada == null) {
            JOptionPane.showMessageDialog(vista, "Selecciona una cadena de la tabla",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // CORRECCIÓN: Si hay una cadena diferente seleccionada, reiniciar
        if (elementosCadenaActual != null && !vista.obtenerCadenaSeleccionada().equals(cadenaSeleccionadaActual)) {
            elementosCadenaActual = null;
            estadoActual = null;
            caminoRecorrido = null;
        }

        // Si es nueva cadena, inicializar
        if (elementosCadenaActual == null) {
            iniciarNuevaCadena(cadenaSeleccionada);
            cadenaSeleccionadaActual = cadenaSeleccionada;
        }

        // 3. Procesar siguiente elemento
        procesarSiguienteElemento();

    }

    private void iniciarNuevaCadena(String cadena) {

        elementosCadenaActual = Arrays.stream(cadena.split(",")).map(String::trim).collect(Collectors.toList());

        cadenaSeleccionadaActual = cadena;
        indiceElementoActual = 0;

        System.out.println("" + cadena);

        //Metodos pintar tabla
        caminoRecorrido = new ArrayList<>();
        estadoActual = modelo.getEstadoInicial();

        vista.limpiarColoresTabla();
        vista.setTextoEnCampo("");

        vista.mostrarEstadosTransicion(estadoActual, "-");

        System.out.println("Iniciando procesamiento de: " + cadena);
        System.out.println("Estado inicial: " + estadoActual);

    }

    private void actualizarGraphviz() {
        gravphvizControlador.actualizarVisualizacionDesdeMatriz(
                modelo.getEstadoInicial(),
                modelo.getEstadosAceptacionList(),
                modelo.getEstadosList(),
                modelo.getSimbolosList(),
                modelo.getMatrizTransiciones(),
                estadoActual
        );
    }

    //Metodo para procesar elemento por elemento, validar y pintar celdas posteriormente
    private void procesarSiguienteElemento() {
        if (elementosCadenaActual == null || indiceElementoActual >= elementosCadenaActual.size()) {
            // Finalizar cadena y validar
            if (elementosCadenaActual != null) {
                validarCadenaFinal();
            }
            elementosCadenaActual = null;
            estadoActual = null;
            caminoRecorrido = null;
            return;
        }

        String simbolo = elementosCadenaActual.get(indiceElementoActual);
        String estadoSiguiente = obtenerTransicion(estadoActual, simbolo);

        if (estadoSiguiente != null && !estadoSiguiente.equals("-")) {
            // Resaltar celda en la tabla
            int fila = modelo.getEstadosList().indexOf(estadoActual);
            int columna = modelo.getSimbolosList().indexOf(simbolo) + 1; // +1 por columna de estados

            // CORRECCIÓN: Verificar que los índices son válidos
            if (fila >= 0 && columna >= 1 && columna < vista.tblTransiciones.getColumnCount()) {
                // Resaltar la transición actual
                vista.resaltarCelda(fila, columna, estadoActual, simbolo);
            }

            // 2. REGISTRAR TRANSICIÓN ANTES de actualizar estadoActual
            String key = estadoActual + "|" + simbolo + "->" + estadoSiguiente;
            System.out.println("Transición " + key);
            gravphvizControlador.registrarTransicionUsada(key);

            // 3. ACTUALIZAR GRAPHVIZ ANTES de cambiar estadoActual
            actualizarGraphviz();

            // Mostrar en interfaz
            vista.setTextoEnCampo(simbolo);
            vista.mostrarEstadosTransicion(estadoActual, estadoSiguiente);

            System.out.println("Transición: " + estadoActual + " --" + simbolo + "--> " + estadoSiguiente);

            // Preparar siguiente iteración
            estadoActual = estadoSiguiente;
            indiceElementoActual++;

            // Si es el último elemento, preparar validación
            if (indiceElementoActual >= elementosCadenaActual.size()) {
                System.out.println("Último elemento procesado");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "No existe transición para " + estadoActual + " con " + simbolo,
                    "Error", JOptionPane.ERROR_MESSAGE);
            validarCadenaFinal();
        }
    }

    private String obtenerTransicion(String estadoActual, String simbolo) {
        int fila = modelo.getEstadosList().indexOf(estadoActual);
        int columna = modelo.getSimbolosList().indexOf(simbolo);

        if (fila >= 0 && columna >= 0) {
            return modelo.getMatrizTransiciones()[fila][columna];
        }
        return null;
    }

    private void validarCadenaFinal() {
        // Verificar si el estado final es de aceptación
        if (estadoActual == null) {
            return;
        }

        boolean esValida = modelo.getEstadosAceptacionList().contains(estadoActual);
        int indiceCadena = vista.obtenerIndiceCadenaSeleccionada();

        // CORRECCIÓN: Verificar que el índice es válido
        if (indiceCadena != -1) {
            vista.marcarCadenaValida(indiceCadena, esValida);
            vista.mostrarEstadosTransicion(estadoActual, esValida ? "ACEPTADO" : "RECHAZADO");

            String mensaje = esValida ? "Cadena VÁLIDA - Estado final: " + estadoActual
                    : "Cadena INVÁLIDA - Estado final: " + estadoActual;
            JOptionPane.showMessageDialog(vista, mensaje, "Resultado",
                    esValida ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

            System.out.println("Cadena " + (esValida ? "VÁLIDA" : "INVÁLIDA") + " - Estado final: " + estadoActual);
        }

    }

    //Método para limpiar elementos gráficos
    public void limpiarElementos() {
        vista.txtEstadoIncial.revalidate();
        vista.scrollPaneEstadosAceptacion.revalidate();
        vista.scrollPaneSimbolos.revalidate();
        vista.scrollPaneTransiciones.revalidate();
        vista.scrollPaneEstadosAceptacion.repaint();
        vista.scrollPaneSimbolos.repaint();
        vista.scrollPaneTransiciones.repaint();
    }

    public void limpiarElementosAFD() {
        vista.limpiarAFD();
    }

    private void abrirPDFs(String ruta) {

        File archivoPDF = new File(ruta);

        if (!archivoPDF.exists()) {
            JOptionPane.showMessageDialog(vista, "Archivo No encontrado", "MANUAL NO ENCONTRADO", JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (!Desktop.isDesktopSupported()) {
            JOptionPane.showMessageDialog(vista, "Desktop no compatible en este sistema", "PROBLEMA AL ABRIR LOS ARCHIVOS", JOptionPane.WARNING_MESSAGE);

            return;
        }

        try {
            Desktop.getDesktop().open(archivoPDF);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "No se pudo abrir el archivo", "MANUAL NO ENCONTRADO", JOptionPane.WARNING_MESSAGE);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
