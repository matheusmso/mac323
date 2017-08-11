/********************
Nome: Matheus de Mello Santos Oliveira
NUSP: 8642821
 ********************/
import edu.princeton.cs.algs4.*;

public class LC2D {
    public class Pt {
        private int id;
        private Point2D p;
        public Pt(int id, double x, double y) {
            this.id = id;
            this.p = new Point2D(x, y);
        }
    }
    public static void main (String[] args) {
        double d = Double.parseDouble(args[0]);
        int T = Integer.parseInt(args[1]);
        LC2D a = new LC2D();
        if (args.length == 3) {
            int seed = Integer.parseInt(args[2]);
            StdRandom.setSeed(seed);
        }
        int n = (int) Math.ceil(1.0/d);
        double[] tests = new double[T];
        for (int run = 0; run < T; run++) {
            Queue<Pt>[][] grid = (Queue<Pt>[][]) new Queue[n+2][n+2];
            DUF uf = new DUF();
            for (int i = 0; i <= n+1; i++)
                for (int j = 0; j <= n+1; j++)
                    grid[i][j] = new Queue<Pt>();
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            LC2D.Pt t = a.new Pt(0, x, y);
            int row = 1 + (int) (x*n);
            int col = 1 + (int) (y*n);
            grid[row][col].enqueue(t);
            int e = 0;
            while (true) {
                x = StdRandom.uniform();
                y = StdRandom.uniform();
                e = uf.newSite();
                t = a.new Pt(e, x, y);
                row = 1 + (int) (x*n);
                col = 1 + (int) (y*n);
                for (int i = row-1; i <= row+1; i++) 
                    for (int j = col-1; j <=col+1; j++) 
                        for (Pt l: grid[i][j]) 
                            if (t.p.distanceTo(l.p) <= d)
                                uf.union(t.id, l.id);
                grid[row][col].enqueue(t);
                if (uf.count() == 1) {
                    tests[run] = e+1;
                    break;
                }
            }
        }
        StdOut.println(StdStats.mean(tests) + " " + StdStats.var(tests));
    }
}
