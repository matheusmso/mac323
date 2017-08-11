/***********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ********************/

import edu.princeton.cs.algs4.*;

public class BattleForTheKingdom {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        int K = StdIn.readInt();
        EdgeWeightedDigraph D = new EdgeWeightedDigraph(N);
        int[] HeroStartingPos = new int[K];
        for (int i = 0; i < M; i++) {
            int a = StdIn.readInt();
            int b = StdIn.readInt();
            double t = StdIn.readDouble();
            D.addEdge(new DirectedEdge(a,b,t));
        }
        for (int i = 0; i < K; i++) {
            int c = StdIn.readInt();
            HeroStartingPos[i] = c;
        }
        int F = StdIn.readInt();
        DijkstraSP spf = new DijkstraSP(D, F);
        double[] MinDestroyerPath = new double[N];
        for (int i = 0; i < N; i++) {
            if (spf.hasPathTo(i))
                MinDestroyerPath[i] = spf.distTo(i);
            else
                MinDestroyerPath[i] = -1;
        }
        double[][] MinHeroesPath = new double[K][N];
        for (int i = 0; i < K; i++) {
            DijkstraSP sp = new DijkstraSP(D, HeroStartingPos[i]);
            for (int j = 0; j < N; j++) {
                if (sp.hasPathTo(j))
                    MinHeroesPath[i][j] = sp.distTo(j);
                else
                    MinHeroesPath[i][j] = -1;
            }
        }
        int[] answer = new int[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if (MinHeroesPath[j][i] >= MinDestroyerPath[i] || 
                        MinHeroesPath[j][i] == -1) {
                    answer[i] = -1;
                    break;
                }
            }
            if (answer[i] == 0) {
                answer[i] = 1;
                count++;
            }
        }
        if (count > 0) {
            StdOut.println("VICTORY AND HAPPY EVER AFTER");
            StdOut.println(count);
            for (int i = 0; i < N; i++)
                if (answer[i] > 0)
                    StdOut.print(i + " ");
            StdOut.println();
        }
        else
            StdOut.println("DEMISE OF THE KINGDOM");
    }
}

