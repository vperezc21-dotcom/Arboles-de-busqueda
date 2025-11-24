

package org.knowm.xchart.ejerc3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;







class Edge implements Comparable<Edge> {
    int src, dest, weight;
    Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
    public int compareTo(Edge other) { return this.weight - other.weight; }
}

class UnionFind {
    int[] parent, rank;
    UnionFind(int n) { parent = new int[n]; rank = new int[n]; for(int i=0;i<n;i++) parent[i]=i; }
    int find(int x){ return parent[x]==x?x:(parent[x]=find(parent[x])); }
    boolean union(int a,int b){
        int ra=find(a), rb=find(b);
        if(ra==rb) return false;
        if(rank[ra]<rank[rb]) parent[ra]=rb;
        else if(rank[rb]<rank[ra]) parent[rb]=ra;
        else { parent[rb]=ra; rank[ra]++; }
        return true;
    }
}

public class Ejerc3{
    static final int INF = Integer.MAX_VALUE;

    // ---------- PRIM (para comparar) ----------
    static int primMST(int[][] graph, int start) {
        int V = graph.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] used = new boolean[V];
        Arrays.fill(key, INF);
        key[start] = 0;
        parent[start] = -1;

        for (int count=0; count<V-1; count++) {
            int u=-1, min=INF;
            for(int v=0; v<V; v++) if(!used[v] && key[v]<min){ min=key[v]; u=v; }
            used[u]=true;
            for(int v=0; v<V; v++) {
                if(graph[u][v]!=INF && !used[v] && graph[u][v]<key[v]) {
                    parent[v]=u; key[v]=graph[u][v];
                }
            }
        }
        int total=0;
        for(int i=0;i<V;i++) if(parent[i]!=-1) total+=graph[i][parent[i]];
        return total;
    }

    // ---------- KRUSKAL ----------
    static int kruskalMST(int V, List<Edge> edges) {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(V);
        int total=0, descartadas=0;

        System.out.println("Aristas seleccionadas (Kruskal):");
        for(Edge e: edges){
            if(uf.union(e.src, e.dest)){
                System.out.println(e.src+" - "+e.dest+" : "+e.weight);
                total+=e.weight;
            } else {
                descartadas++;
            }
        }
        System.out.println("Número de aristas descartadas = " + descartadas);
        System.out.println("Costo total MST (Kruskal) = " + total);
        return total;
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        int V=6;
        int[][] graph={
            {INF,6,1,5,INF,INF},
            {6,INF,2,INF,5,INF},
            {1,2,INF,2,6,INF},
            {5,INF,2,INF,INF,4},
            {INF,5,6,INF,INF,3},
            {INF,INF,INF,4,3,INF}
        };

        List<Edge> edges=Arrays.asList(
            new Edge(0,1,6), new Edge(0,2,1), new Edge(0,3,5),
            new Edge(1,2,2), new Edge(1,4,5), new Edge(2,3,2),
            new Edge(2,4,6), new Edge(3,5,4), new Edge(4,5,3)
        );

        int costoKruskal = kruskalMST(V, edges);
        int costoPrim = primMST(graph, 0);

        System.out.println("\nComparación:");
        System.out.println("Costo con Prim = " + costoPrim);
        System.out.println("Costo con Kruskal = " + costoKruskal);
        System.out.println((costoPrim==costoKruskal) ? "✅ Ambos coinciden" : "❌ Diferentes");
    }
}

