

package org.knowm.xchart.ejerc6;

import java.util.*;
public class Ejerc6 {

    static final int INF = 9999; // valor muy grande para representar "infinito"

    // Ejecuta Floyd-Warshall
    static void floydWarshall(int[][] dist, int[][] next) {
        int V = dist.length;
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k]; // para reconstruir el camino
                    }
                }
            }
        }
    }

    // Reconstruye el camino desde u hasta v usando la matriz next
    static List<Integer> getPath(int u, int v, int[][] next) {
        if (next[u][v] == -1) return new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(u);
        while (u != v) {
            u = next[u][v];
            path.add(u);
        }
        return path;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] graph = {
            {0, 10, INF, INF, 3},
            {INF, 0, 2, INF, 4},
            {INF, INF, 0, 9, INF},
            {INF, INF, 7, 0, INF},
            {INF, 1, 8, 2, 0}
        };

        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        // Inicialización
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Ejecutar Floyd-Warshall
        floydWarshall(dist, next);

        // Mostrar matriz de distancias
        System.out.println("Matriz de distancias mínimas:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) System.out.print("INF\t");
                else System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }

        // Camino mínimo de 0 a 3
        List<Integer> path = getPath(0, 3, next);
        System.out.println("\nCamino mínimo de 0 → 3: " + path);
        System.out.println("Distancia mínima = " + dist[0][3]);
    }
}



