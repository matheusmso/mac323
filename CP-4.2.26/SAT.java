/****************************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ***********************/

import edu.princeton.cs.algs4.*;

public class SAT {
    public static int not(int x, int N) {
        if (x > N)
            return x-N;
        return x+N;
    }
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        Digraph D = new Digraph(N*2);
        for (int i = 0; i < M; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            if (x < 0) {
                if (y < 0) {
                    x = N-x;
                    y = N-y;
                }
                else 
                    x = N-x;
            }
            else {
                if (y < 0)
                    y = N-y;
            }
            D.addEdge(not(x,N)-1,y-1);
            D.addEdge(not(y,N)-1,x-1);
        }
        boolean pos = true;
        KosarajuSharirSCC scc = new KosarajuSharirSCC(D);
        for (int i = 0; i < N; i++) {
            if(scc.stronglyConnected(i, i+N)) {
                pos = false;
                break;
            }
        }
        if (pos) {
            StdOut.println("VERDADE");
            Queue<Integer>[] q = (Queue<Integer>[]) new Queue[scc.count()];
            for (int i = 0; i < scc.count(); i++)
                q[i] = new Queue<Integer>();
            for (int i = 0; i < D.V(); i++)
                q[scc.id(i)].enqueue(i);
            int[] aux = new int [2*N];
            for (int i = 0; i < 2*N; i++)
                aux[i] = -1;
            for (int i = 0; i < scc.count(); i++) {
                for (int j : q[i]) {
                    if (aux[j] == -1) {
                        aux[j] = 1;
                        if (j < N)
                            aux[j+N] = 0;
                        else
                            aux[j-N] = 0;
                    }
                }
            }
            for (int i = 0; i < N; i++)
                StdOut.print(aux[i] + " ");
            StdOut.println();
        }
        else
            StdOut.println("MENTIRA");
    }
}

