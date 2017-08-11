/*********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ******************/

import edu.princeton.cs.algs4.*;

public class CoreVertices {
    private static boolean[] marked;
    private static int answer;
    public static void dfs(Digraph D, int v) {
        marked[v] = true;
        for (int i : D.adj(v)) {
            if (!marked[i]) {
                dfs(D, i);
                answer++;
            }
        }
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int m = StdIn.readInt();
        Digraph D = new Digraph(n);
        for (int i = 0; i < m; i++) {
            int u = StdIn.readInt();
            int v = StdIn.readInt();
            D.addEdge(u,v);
        }
        KosarajuSharirSCC scc = new KosarajuSharirSCC(D);
        int count = scc.count();
        int[] candidates = new int[n];
        int t = 0;
        answer = 0;
        for (int k = 0; k < D.V(); k++)
            if (scc.id(k) == count-1)
                candidates[t++] = k;
        marked = new boolean[n];
        dfs(D,candidates[0]);
        if (answer == n-1) {
            for (int i = 0; i < t; i++)
                StdOut.print(candidates[i] + " ");
            StdOut.println();
        }
    }
}

