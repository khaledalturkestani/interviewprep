package com.khaled.graph;

import java.util.*;

/**
 *
 *
 * http://www.geeksforgeeks.org/bipartite-graph/
 *
 */
public class BipartiteGraph {


    static boolean isBipartite(int[][] graph) {
        int[] colorArr = new int[graph.length];
        for (int i = 0; i < colorArr.length; i++) {
            colorArr[i] = -1;
        }

        List<Integer> vertices = new ArrayList<>();
        vertices.add(0);
        colorArr[0] = 1;

        while (!vertices.isEmpty()) {
            int u = vertices.remove(0);
            // Return false if there's a self loop
            if (graph[u][u] == 1)
                return false;

            for (int v = 0; v < graph.length; v++) {
                // If there's an edge AND v hasn't been visited yet
                if (graph[u][v] == 1 && colorArr[u] == -1) {
                    colorArr[v] = 1-colorArr[u];
                    vertices.add(v);
                }
                // Else if there's an edge AND v has been visited (implied) and is the same color as u
                else if (graph[u][v] == 1 && colorArr[u] == colorArr[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    //////////////////////////////////////////
    // THIS IS THE WEBSITE'S IMPLEMENTATION///
    //////////////////////////////////////////

    // This function returns true if graph G[V][V] is Bipartite, else false
    static boolean isBipartite(int G[][],int src)
    {
        // Create a color array to store colors assigned to all veritces.
        // Vertex number is used as index in this array. The value '-1'
        // of  colorArr[i] is used to indicate that no color is assigned
        // to vertex 'i'.  The value 1 is used to indicate first color
        // is assigned and value 0 indicates second color is assigned.
        int colorArr[] = new int[G.length];
        for (int i=0; i<G.length; ++i)
            colorArr[i] = -1;

        // Assign first color to source
        colorArr[src] = 1;

        // Create a queue (FIFO) of vertex numbers and enqueue
        // source vertex for BFS traversal
        LinkedList<Integer>q = new LinkedList<Integer>();
        q.add(src);

        // Run while there are vertices in queue (Similar to BFS)
        while (q.size() != 0)
        {
            // Dequeue a vertex from queue
            int u = q.poll();

            // Return false if there is a self-loop
            if (G[u][u] == 1)
                return false;

            // Find all non-colored adjacent vertices
            for (int v=0; v<G.length; ++v)
            {
                // An edge from u to v exists and destination v is
                // not colored
                if (G[u][v]==1 && colorArr[v]==-1)
                {
                    // Assign alternate color to this adjacent v of u
                    colorArr[v] = 1-colorArr[u];
                    q.add(v);
                }

                // An edge from u to v exists and destination v is
                // colored with same color as u
                else if (G[u][v]==1 && colorArr[v]==colorArr[u])
                    return false;
            }
        }
        // If we reach here, then all adjacent vertices can
        //  be colored with alternate color
        return true;
    }

    public static void main (String[] args)
    {
        int G[][] = {{0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };
        if (isBipartite(G))
//        if (isBipartite(G, 0))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
