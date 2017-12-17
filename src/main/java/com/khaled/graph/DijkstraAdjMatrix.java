package com.khaled.graph;

import java.lang.annotation.Inherited;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 */
public class DijkstraAdjMatrix {

    class Node {
        int id;
        int dist;

        public Node(int id, int wt) {
            this.id = id;
            this.dist = wt;
        }

        @Override
        public boolean equals(Object n) {
            return this.id == ((Node)n).id;
        }
    }

    public void dijkstra(int[][] graph, int src) {

        if (graph == null || src < 0 || src > graph.length)
            return;

        int n = graph.length;

        // Visited array
        boolean[] visited = new boolean[n];
        // Min distances array
        int[] dists = new int[n];
        for (int i = 0; i < dists.length; i++)
            dists[i] = Integer.MAX_VALUE;

        dists[src] = 0;

        for (int i = 0; i < n; i++) {
            int u = minVertex(visited, dists);
            visited[u] = true;
            for (int j = 0; j < n; j++) {
                boolean notVisited = !visited[j];
                boolean notZero = graph[u][j] != 0;
                boolean initialized = dists[u] != Integer.MAX_VALUE;
                boolean distSmaller = graph[u][j] + dists[u] < dists[j];
                if (notVisited &&
                        notZero &&
//                        initialized &&
                        distSmaller)
                    dists[j] = graph[u][j] + dists[u];
            }
        }

        printSolution(dists);
    }

    private int minVertex(boolean[] visited, int[] dists) {
        int indx = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dists.length; i++) {
            if (!visited[i] && dists[i] < min) {
                min = dists[i];
                indx = i;
            }
        }
        return indx;
    }

    void printSolution(int dist[])
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + " tt " + dist[i]);
    }

    public static void main(String[] args) {
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        DijkstraAdjMatrix t = new DijkstraAdjMatrix();
            t.dijkstra(graph, 0);

        List<Integer>[] arr = new List[3];
    }
}
