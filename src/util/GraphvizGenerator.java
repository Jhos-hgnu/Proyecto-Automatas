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
//        //Metodos nuevos forzar orden
        dot.append("\tordering=out;\n");
        dot.append("\tnodesep=0.8;\n\n");

        // Configurar estados de aceptación (doble círculo)
        if (!estadosAceptacion.isEmpty()) {
            dot.append("\tnode [shape = doublecircle]; ");
            for (String estado : estadosAceptacion) {
                dot.append(estado).append(" ");
            }
            dot.append(";\n");
        }

        dot.append("\tnode [shape = circle];\n\n");

        //Metodos forzar orden
        dot.append("\t// Flecha de inicio visible\n");
        dot.append("\tinic [shape=point, width=0.15, color=black];\n");
        dot.append("\tinic -> ").append(estadoInicial).append(" [color=black, arrowhead=normal];\n\n");

        // Ranks jerárquicos para forzar orden específico
        List<String> estadosOrdenados = ordenarEstados(estados, estadoInicial);

        for (int i = 0; i < estadosOrdenados.size(); i++) {
            dot.append("\t{ rank = same; ");
            dot.append(estadosOrdenados.get(i)).append("; }\n");
        }

        // Aplicar colores a los estados
        dot.append("\t// Estados con colores\n");
        for (String estado : estados) {
            String color = obtenerColorEstado(estado, estadoInicial, estadoActual, estadosAceptacion);
            dot.append("\t").append(estado).append(" [").append(color).append("];\n");

            //FORZAR ORDEN
            //Posicionamiento adicional para el estado inicial
//            if (estado.equals(estadoInicial)) {
//                dot.append(" xlabel=\"Inicio\"");
//            }
//            dot.append("];\n");
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
                return " [color=\"#ffa500\" penwidth=3.0]"; // Azul grueso
            }
            if (transicionesUsadas.containsKey(key2)) {
                System.out.println("ENCONTRADA key2 - Pintando AZUL");
                return " [color=\"#ffa500\" penwidth=3.0]"; // Azul grueso
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


    
    private static List<String> ordenarEstados(List<String> estados, String estadoInicial) {
        List<String> ordenados = new ArrayList<>();
        
        // Estado inicial siempre primero
        if (estados.contains(estadoInicial)) {
            ordenados.add(estadoInicial);
            System.out.println("Estado inicial agregado: " + estadoInicial);
        }
        
        // Agregar los demás estados en orden (Q0, Q1, Q2...)
        List<String> otrosEstados = estados.stream()
                .filter(e -> !e.equals(estadoInicial))
                .sorted()
                .collect(Collectors.toList());
        
        ordenados.addAll(otrosEstados);
        
        System.out.println("Estados ordenados: " + ordenados);
        return ordenados;
    }
    
   
}
