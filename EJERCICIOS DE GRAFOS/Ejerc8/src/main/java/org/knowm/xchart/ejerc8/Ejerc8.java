
package org.knowm.xchart.ejerc8;

import java.util.*;

public class Ejerc8 {
   
    static List<List<Integer>> adj; // lista de adyacencia

    // Inicializar red con n nodos
    static void init(int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    // Agregar conexión entre dos nodos
    static void addConnection(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // red bidireccional
        System.out.println("Conexión agregada: " + u + " ↔ " + v);
    }

    // Verificar si dos nodos pueden comunicarse (BFS)
    static boolean canCommunicate(int src, int dest) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);

        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == dest) return true;
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        return false;
    }

    // Camino más corto en número de saltos (BFS)
    static void shortestPath(int src, int dest) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);

        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == dest) break;
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    q.add(v);
                }
            }
        }

        // Reconstruir camino
        List<Integer> path = new ArrayList<>();
        for (int at = dest; at != -1; at = parent[at]) path.add(at);
        Collections.reverse(path);

        if (path.get(0) != src) {
            System.out.println("No existe camino entre " + src + " y " + dest);
            return;
        }

        System.out.print("Camino más corto de " + src + " a " + dest + ": ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" → ");
        }
        System.out.println(" (saltos = " + (path.size()-1) + ")");
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        int N = 6; // número de computadoras
        init(N);

        // Agregar conexiones iniciales
        addConnection(0, 1);
        addConnection(0, 2);
        addConnection(1, 3);
        addConnection(2, 3);
        addConnection(3, 4);
        addConnection(4, 5);

        // Verificar comunicación
        System.out.println("¿Puede 0 comunicarse con 5? " + canCommunicate(0, 5));

        // Camino más corto
        shortestPath(0, 5);
    }
}




