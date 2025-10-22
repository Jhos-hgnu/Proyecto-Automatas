/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author jhosu
 */

public class GraphvizGenerator {

//    public static String generarCodigoDot(String estadoInicial, List<String> estadosAceptacion, List<String[]> transiciones, String estadoActual, Map<String, String> transicionesUsadas) {
//
//        StringBuilder dot = new StringBuilder();
//
//        //Encabezados del archivo DOT
//        dot.append("digraph automata_finito_determinista {\n");
//        dot.append("\trankdir=LR;\n");
//        dot.append("\tsize=\"8,5\"\n\n");
//
//        //Configuración de estados de aceptación
//        if (!estadosAceptacion.isEmpty()) {
//            dot.append("\tnode [shape = doublecircle]; ");
//
//            for (String estado : estadosAceptacion) {
//                dot.append(estado).append(" ");
//            }
//            dot.append(";\n");
//        }
//
//        dot.append("\tnode [shape = circle];\n\n");
//
//        //Aplicar colores a los estados del autómata
//        dot.append("\t// Estados con colores\n");
//        List<String> todoEstados = obtenerTodosEstados(transiciones);
//        for (String estado : todoEstados) {
//            String color = obtenerColorEstado(estado, estadoInicial, estadoActual, estadosAceptacion);
//
//            dot.append("\t").append(estado).append(" [").append(color).append("];\n");
//        }
//
//        dot.append("\n");
//
//        // Generar transiciones
//        dot.append("\t// Transiciones\n");
//        for (String[] transicion : transiciones) {
//            if (transicion.length == 2) {
//                String origen = transicion[0];
//                String destino = transicion[1];
//                String simbolo = obtenerSimboloTransicion(transicion);
//                String colorTransicion = obtenerColorTransicion(origen, destino, simbolo, transicionesUsadas);
//
//                dot.append("\t").append(origen).append(" -> ").append(destino)
//                        .append(" [ label = \"").append(obtenerSimboloTransicion(transicion)).append("\" ]")
//                        .append(colorTransicion).append(";\n");
//            }
//        }
//
//        dot.append("}");
//        return dot.toString();
//
//    }

    private static List<String> obtenerTodosEstados(List<String[]> transiciones) {
        Set<String> estados = new HashSet<>();

        if (transiciones != null) {
            for (String[] transicion : transiciones) {
                if (transicion.length >= 2) {
                    estados.add(transicion[0]); // Estado origen
                    estados.add(transicion[1]); // Estado destino
                }
            }
        }

        // Ordenar los estados para consistencia (Q0, Q1, Q2...)
        return estados.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    //MÉTODO 2: Extraer el símbolo de la transición
    private static String obtenerSimboloTransicion(String[] transicion) {
        // Depende del formato de tus transiciones:
        // Formato 1: ["Q0", "Q1"] → símbolo implícito (necesitarías mapeo)
        // Formato 2: ["Q0", "1|Q1"] → símbolo explícito

        if (transicion.length < 2) {
            return "ε"; // Transición épsilon por defecto
        }

        String destinoCompleto = transicion[1];

        // Si el formato es "símbolo|estadoDestino"
        if (destinoCompleto.contains("|")) {
            String[] partes = destinoCompleto.split("\\|");
            if (partes.length >= 2) {
                return partes[0].trim(); // Retorna el símbolo
            }
        }

        // Si no hay símbolo explícito, usar uno por defecto basado en la transición
        // Esto es un fallback - idealmente tus transiciones deberían tener símbolos
        return generarSimboloPorDefecto(transicion[0], transicion[1]);
    }

    private static String generarSimboloPorDefecto(String origen, String destino) {
        // Usar hash simple para generar símbolo consistente
        int hash = (origen + destino).hashCode();
        return String.valueOf(Math.abs(hash) % 2); // 0 o 1 para autómatas binarios
    }

    private static String obtenerColorEstado(String estado, String estadoInicial, String estadoActual,
            List<String> estadosAceptacion) {
        if (estado.equals(estadoActual)) {
            return "color=deepskyblue style=filled"; // Estado ACTUAL
        } else if (estado.equals(estadoInicial)) {
            //return "color=orangered style=filled"; // Estado INICIAL 
        } else if (estadosAceptacion.contains(estado)) {
            System.out.println("ESTADO ACEPTACION PINTAR");
            return "color=lightgreen style=filled"; // Estado de ACEPTACIÓN
        }
        return "color=black"; // Estado normal - negro
    }

    private static String obtenerColorTransicion(String origen, String destino, String simbolo,
            Map<String, String> transicionesUsadas) {
        if (transicionesUsadas != null && !transicionesUsadas.isEmpty()) {
            // Buscar si esta transición fue usada recientemente
            String key1 = origen + "|" + simbolo + "->" + destino;
            String key2 = origen + "->" + destino;
            
            if (transicionesUsadas.containsKey(key1)) {
            System.out.println("ENCONTRADA key1 - Pintando AZUL");
            return " [color=\"#0000ff\" penwidth=3.0]"; // Azul grueso
        }
        if (transicionesUsadas.containsKey(key2)) {
            System.out.println("ENCONTRADA key2 - Pintando AZUL"); 
            return " [color=\"#0000ff\" penwidth=3.0]"; // Azul grueso
        }
        System.out.println("NO encontrada");
    } else {
        System.out.println("transicionesUsadas es null o vacío");
    }

//            if (transicionesUsadas.containsKey(key) || transicionesUsadas.containsKey(keySimple)) {
//                return " [color=\"#0000ff\" penwidth=2.0]"; // Transición USADA - rojo
//            }

        return " [color=\"#000000\"]"; // Transición normal - negro

    }

    // En util/GraphvizGenerator.java
    public static String generarCodigoDotDesdeMatriz(String estadoInicial, List<String> estadosAceptacion,
            List<String> estados, List<String> simbolos,
            String[][] matrizTransiciones, String estadoActual,
            Map<String, String> transicionesUsadas) {

        StringBuilder dot = new StringBuilder();

        // Encabezado del archivo DOT
        dot.append("digraph automata_finito_determinista {\n");
        dot.append("\trankdir=LR;\n");
        dot.append("\tsize=\"8,5\"\n\n");

        // Configurar estados de aceptación (doble círculo)
        if (!estadosAceptacion.isEmpty()) {
            dot.append("\tnode [shape = doublecircle]; ");
            for (String estado : estadosAceptacion) {
                dot.append(estado).append(" ");
            }
            dot.append(";\n");
        }

        dot.append("\tnode [shape = circle];\n\n");

        // Aplicar colores a los estados
        dot.append("\t// Estados con colores\n");
        for (String estado : estados) {
            String color = obtenerColorEstado(estado, estadoInicial, estadoActual, estadosAceptacion);
            dot.append("\t").append(estado).append(" [").append(color).append("];\n");
        }

        dot.append("\n");

        // Generar transiciones desde la MATRIZ
        dot.append("\t// Transiciones desde matriz\n");
        for (int i = 0; i < estados.size(); i++) {
            for (int j = 0; j < simbolos.size(); j++) {
                String destino = matrizTransiciones[i][j];

                // Solo crear transición si hay un destino válido
                if (destino != null && !destino.equals("-") && !destino.isEmpty()) {
                    String origen = estados.get(i);
                    String simbolo = simbolos.get(j);
                    String colorTransicion = obtenerColorTransicion(origen, destino, simbolo, transicionesUsadas);

                    dot.append("\t").append(origen).append(" -> ").append(destino)
                            .append(" [ label = \"").append(simbolo).append("\" ]")
                            .append(colorTransicion).append(";\n");
                }
            }
        }

        dot.append("}");
        return dot.toString();
    }
}
