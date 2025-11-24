

package org.knowm.xchart.ejer1;
import java.util.*;
public class EJER1 {

    // Clase para representar una arista
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int V = 6; // número de vértices
    static List<Edge> edges = new ArrayList<>();

    // Grafo del taller
    static {
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(2, 4, 6));
        edges.add(new Edge(3, 5, 4));
        edges.add(new Edge(4, 5, 3));
    }

    // ---------- PRIM ----------
    static int primMST(int start) {
        boolean[] visited = new boolean[V];
        int[] minEdge = new int[V];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[start] = 0;
        int totalCost = 0;

        for (int i = 0; i < V; i++) {
            int u = -1;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && (u == -1 || minEdge[v] < minEdge[u])) {
                    u = v;
                }
            }

            visited[u] = true;
            totalCost += minEdge[u];

            // actualizar aristas adyacentes
            for (Edge e : edges) {
                if (e.src == u && !visited[e.dest] && e.weight < minEdge[e.dest]) {
                    minEdge[e.dest] = e.weight;
                }
                if (e.dest == u && !visited[e.src] && e.weight < minEdge[e.src]) {
                    minEdge[e.src] = e.weight;
                }
            }
        }
        return totalCost;
    }

    // ---------- KRUSKAL ----------
    static int kruskalMST() {
        Collections.sort(edges);
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        int cost = 0;
        int count = 0;

        for (Edge e : edges) {
            int setU = find(parent, e.src);
            int setV = find(parent, e.dest);
            if (setU != setV) {
                cost += e.weight;
                union(parent, setU, setV);
                count++;
                if (count == V - 1) break;
            }
        }
        return cost;
    }

    static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    static void union(int[] parent, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);
        parent[rootU] = rootV;
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        int costPrim = primMST(0); // iniciando en vértice 0
        int costKruskal = kruskalMST();

        System.out.println("Costo MST con Prim: " + costPrim);
        System.out.println("Costo MST con Kruskal: " + costKruskal);

        if (costPrim == costKruskal) {
            System.out.println("✅ Ambos algoritmos coinciden. Costo total = " + costPrim);
        } else {
            System.out.println("❌ Los resultados no coinciden.");
        }
    }
}

