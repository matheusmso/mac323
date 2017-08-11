/********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * *****************/


import edu.princeton.cs.algs4.*;


public class Distribution {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int L = Integer.parseInt(args[2]);
        int[][] f = new int[(2*L)+1][(2*L)+1];
        int max = 0;
        StdDraw.setXscale(-0.5-L, 0.5+L);
        StdDraw.setYscale(-0.5-L, 0.5+L);
        for (int i = 0; i < M; i++) {
            RandomWalker a = new RandomWalker();
            for (int j = 0; j < N; j++) {
                a.step(StdRandom.uniform(4));
                if (Math.abs(a.x) <= L && Math.abs(a.y) <= L) {
                    f[L + a.x][L + a.y]++;
                    if (f[L + a.x][L + a.y] > max)
                        max = f[L + a.x][L + a.y];
                }
            }
            a = null;
        }
        for (int i = 0; i < (2*L)+1; i++) {
            for (int j = 0; j < (2*L)+1; j++) {
                if (f[i][j] > 0)  {
                    StdDraw.setPenColor(f[i][j] * 255/max, 0, 255 - (f[i][j] * 255/max));
                    StdDraw.filledSquare(i-L, j-L, .5);
                }
            }
        }
    }
}

