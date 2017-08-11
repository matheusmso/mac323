/******************************************************************************
 *  Nome: Matheus de Mello Santos Oliveira
 *  NUSP: 8642821
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class ProbCritica3D {

    public static void pfind(int N, int M, double x0, double x1) {
        double err = .001;
        double xm = (x0 + x1) / 2;
        double fxm = Estimate.eval(N, xm, M);
        if (Math.abs(fxm-.5) < err) {
            StdOut.println(xm);
            return;
        }
        if (fxm > .5)
            pfind(N, M, x0, xm);
        else 
            pfind(N, M, xm, x1);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        ProbCritica3D.pfind(N, M, 0.0, 1.0);
    }
}

