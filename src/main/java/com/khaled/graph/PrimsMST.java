package com.khaled.graph;

/**
 * Prim's Minimum Spanning Tree.
 *
 * http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
 */
public class PrimsMST {

    static void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge   Weight");
        for (int i = 1; i < graph.length; i++)
            System.out.println(parent[i]+" - "+ i+"    "+
                    graph[i][parent[i]]);
    }

    public static int minVertex(int[] key, boolean[] mstSet) {
        int minIdx = -1;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < key.length; i++) {
            if (mstSet[i] != true && key[i] < minVal) {
                minIdx = i;
                minVal = key[i];
            }
        }
        return minIdx;
    }

    public static void mst(int[][] graph) {

        // True if vertex was already added to MST
        boolean[] mstSet = new boolean[graph.length];
        // Vertex weights
        int[] key = new int[graph.length];
        // Parents
        int[] parent = new int[graph.length];

        // Init weights to MAX
        for (int i = 0; i < key.length; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Init first node's weight to zero so that it's picked up first
        key[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < graph.length-1; i++) {

            int u = minVertex(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
/* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        // Print the solution
        mst(graph);
    }
}
