
package org.knowm.xchart.ejerc7;


public class Ejerc7 {

    static void warshall(int[][] graph) {
        int V = graph.length;
        int[][] reach = new int[V][V];

        // Copiar la matriz inicial
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                reach[i][j] = graph[i][j];

        // Algoritmo de Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (reach[i][k] == 1 && reach[k][j] == 1)
                        reach[i][j] = 1;
                }
            }
        }

        // Mostrar matriz de alcanzabilidad
        System.out.println("Matriz de alcanzabilidad:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(reach[i][j] + " ");
            }
            System.out.println();
        }

        // Verificar si es fuertemente conexo
        boolean stronglyConnected = true;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && reach[i][j] == 0) {
                    stronglyConnected = false;
                }
            }
        }
        System.out.println("\n¿Es fuertemente conexo? " + stronglyConnected);
    }

    public static void main(String[] args) {
        // Grafo dirigido: 0→1, 1→2, 2→0, 2→3
        int[][] graph = {
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 0}
        };

        warshall(graph);
    }
}

