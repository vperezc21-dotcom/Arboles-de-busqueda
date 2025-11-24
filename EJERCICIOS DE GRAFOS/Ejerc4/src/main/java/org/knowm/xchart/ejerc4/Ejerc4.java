

package org.knowm.xchart.ejerc4;
import java.util.*;

public class Ejerc4 {

    // Encuentra el camino más corto en número de aristas usando BFS
    static void shortestPath(List<List<Integer>> adj, int src, int dest) {
        int V = adj.size();
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[src] = true;
        queue.add(src);

        // BFS normal
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == dest) break; // si llegamos al destino, paramos
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }

        // Reconstruir camino desde el destino
        List<Integer> path = new ArrayList<>();
        for (int at = dest; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        // Mostrar salida
        System.out.print("Camino más corto → ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" → ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Grafo del enunciado
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(5);

        int origen = 0, destino = 5;
        shortestPath(adj, origen, destino);
    }
}

