/******************************************************************************
 *  Nome: Matheus de Mello Santos Oliveira
 *  NUSP: 8642821
 ******************************************************************************/
import edu.princeton.cs.algs4.*;

public class Percolation {

    // given an N-by-N matrix of open sites, return an N-by-N matrix
    // of sites reachable from the top
    public static boolean[][][] flow(boolean[][][] open) {
        int N = open.length;
        boolean[][][] full = new boolean[N][N][N];
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                flow(open, full, 0, j, k);
            }
        }
        return full;
    }

    // determine set of full sites using depth first search
    public static void flow(boolean[][][] open, boolean[][][] full, int i, int j, int k) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N) return;    // invalid row
        if (j < 0 || j >= N) return;    // invalid column
        if (k < 0 || k >= N) return;
        if (!open[i][j][k]) return;        // not an open site
        if (full[i][j][k]) return;         // already marked as full

        // mark i-j-k as full
        full[i][j][k] = true;

        flow(open, full, i+1, j, k);   // down
        flow(open, full, i, j+1, k);   // right
        flow(open, full, i, j-1, k);   // left
        flow(open, full, i-1, j, k);   // up
        flow(open, full, i, j, k+1);
        flow(open, full, i, j, k-1);
    }


    // does the system percolate?
    public static boolean percolates(boolean[][][] open) {
        int N = open.length;
        boolean[][][] full = flow(open);
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (full[N-1][j][k]) 
                    return true;
            }
        }
        return false;
    }

    // return a random N-by-N-by-N boolean matrix, where each entry is
    // true with probability p
    public static boolean[][][] random(int N, double p) {
        boolean[][][] a = new boolean[N][N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    a[i][j][k] = StdRandom.bernoulli(p);
        return a;
    }
}
