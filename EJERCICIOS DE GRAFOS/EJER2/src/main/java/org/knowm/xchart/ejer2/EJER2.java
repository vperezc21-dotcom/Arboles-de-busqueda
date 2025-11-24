

package org.knowm.xchart.ejer2;

import java.util.Arrays;


public class EJER2 {
  
    static final int INF = Integer.MAX_VALUE;

    static int minKey(int[] key, boolean[] used) {
        int min = INF, minIndex = -1;
        for (int v=0; v<key.length; v++)
            if (!used[v] && key[v] < min) { min = key[v]; minIndex=v; }
        return minIndex;
    }

    static void primMST(int[][] graph, int start) {
        int V = graph.length;
        int[] parent = new int[V], key = new int[V];
        boolean[] used = new boolean[V];
        Arrays.fill(key, INF); key[start]=0; parent[start]=-1;

        for (int i=0;i<V-1;i++) {
            int u=minKey(key,used); used[u]=true;
            for (int v=0; v<V; v++) {
                if (graph[u][v]!=INF && !used[v] && graph[u][v]<key[v]) {
                    parent[v]=u; key[v]=graph[u][v];
                }
            }
        }

        int total=0;
        System.out.println("\nInicio en vértice " + start);
        for (int i=0;i<V;i++) if (parent[i]!=-1) {
            System.out.println(parent[i]+" - "+i+" : "+graph[i][parent[i]]);
            total+=graph[i][parent[i]];
        }
        System.out.println("Costo total MST = "+total);
    }

    public static void main(String[] args) {
        int INF=Integer.MAX_VALUE;
        int[][] graph={
            {INF,6,1,5,INF,INF},
            {6,INF,2,INF,5,INF},
            {1,2,INF,2,6,INF},
            {5,INF,2,INF,INF,4},
            {INF,5,6,INF,INF,3},
            {INF,INF,INF,4,3,INF}
        };
        primMST(graph,0);
        primMST(graph,2);
    }
}



    