/***********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * **********************/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Pontes {
    private static int count;
    private static int[] prev;
    private static int[] low;
    public static void dfs(Graph G, int p, int v) {
        prev[v] = count++;
        low[v] = prev[v];
        for (int vv : G.adj(v)) {
            if (prev[vv] == -1) {
                dfs(G,v,vv);
                low[v] = Math.min(low[v],low[vv]);
                if (low[vv] == prev[vv])
                    StdOut.println(v + " " + vv);
            }
            else if (vv != p)
                low[v] = Math.min(low[v],prev[vv]);
        }
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int m = StdIn.readInt();
        Graph G = new Graph(n);
        for (int i = 0; i < m; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            G.addEdge(x, y);
        }
        prev = new int[n];
        low = new int[n];
        Arrays.fill(prev, -1);
        Arrays.fill(low, -1);
        count = 0;
        for (int v = 0; v < n; v++)
            if (prev[v] == -1)
                dfs(G, v, v);
    }
}

