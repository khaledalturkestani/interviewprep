package com.khaled.graph;

/**
 * Dijkstra's Shortest Path Algorithm.
 *
 * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 */
public class DijkstrasShortestPath {

    static void printSolution(int dist[])
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i+" tt "+dist[i]);
    }

    static int minVertex(boolean[] sptSet, int[] dists) {
        int minIdx = -1;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < sptSet.length; i++) {
            if (dists[i] < minVal && sptSet[i] == false) {
                minIdx = i;
                minVal = dists[i];
            }
        }
        return minIdx;
    }

    static void dijkstras(int[][] graph, int src) {

        int dists[] = new int[graph.length];
        boolean sptSet[] = new boolean[graph.length];

        for (int i = 0; i < sptSet.length; i++) {
            dists[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dists[src] = 0;

        for (int i = 0; i < graph.length-1; i++) {
            int u = minVertex(sptSet, dists);
            sptSet[u] = true;

            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] != 0 && sptSet[v] == false && graph[u][v] + dists[u] < dists[v]) {
                    dists[v] = graph[u][v] + dists[u];
                }
            }
        }

        printSolution(dists);
    }

    public static void main (String[] args)
    {
        /* Let us create the example graph discussed above */
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
        dijkstras(graph, 0);
    }}
