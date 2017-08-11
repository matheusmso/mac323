/******************************************************************************
 *  Nome: Matheus de Mello Santos Oliveira
 *  NUSP: 8642821
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Estimate {

    // do M trials and return fraction that percolate
    public static double eval(int N, double p, int M) {
        int count = 0;
        for (int k = 0; k < M; k++) {
            boolean[][][] open = Percolation.random(N, p);
            if (Percolation.percolates(open))
                count++;
        }
        return (double) count / M;
    }
}

