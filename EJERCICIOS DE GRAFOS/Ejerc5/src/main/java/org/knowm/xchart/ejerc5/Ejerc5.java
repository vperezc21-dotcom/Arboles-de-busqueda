
package org.knowm.xchart.ejerc5;

import java.util.*;
public class Ejerc5 {

    static class Edge {
        int to, weight;
        Edge(int t, int w) { to = t; weight = w; }
    }

    static void dijkstra(List<List<Edge>> adj, int src) {
        int V = adj.size();
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;

            for (Edge e : adj.get(u)) {
                if (dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    parent[e.to] = u;
                    pq.add(new int[]{e.to, dist[e.to]});
                }
            }
        }

        // Mostrar resultados
        for (int v = 0; v < V; v++) {
            if (dist[v] == Integer.MAX_VALUE) {
                System.out.println("No hay camino de " + src + " a " + v);
            } else {
                System.out.print("Distancia mínima de " + src + " a " + v + " = " + dist[v]);
                System.out.print(" | Camino: ");
                List<Integer> path = new ArrayList<>();
                for (int at = v; at != -1; at = parent[at]) path.add(at);
                Collections.reverse(path);
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1) System.out.print(" → ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int V = 5; // nodos: 0,1,2,3,4
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Grafo dirigido con pesos
        adj.get(0).add(new Edge(1, 10));
        adj.get(0).add(new Edge(4, 3));
        adj.get(1).add(new Edge(2, 2));
        adj.get(1).add(new Edge(4, 4));
        adj.get(2).add(new Edge(3, 9));
        adj.get(3).add(new Edge(2, 7));
        adj.get(4).add(new Edge(1, 1));
        adj.get(4).add(new Edge(2, 8));
        adj.get(4).add(new Edge(3, 2));

        // Ejecutar desde 0
        dijkstra(adj, 0);
    }
}

