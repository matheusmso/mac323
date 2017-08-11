/********************
Nome: Matheus de Mello Santos Oliveira
NUSP: 8642821
 ********************/
import edu.princeton.cs.algs4.*;

//@SuppressWarnings("unchecked")

public class PC2D {
    public class Pt {
        private int id;
        private Point2D p;
        public Pt(int id, double x, double y) {
            this.id = id;
            this.p = new Point2D(x, y);
        }
    }
    public static void main (String[] args) {
        int N = StdIn.readInt();
        double d = StdIn.readDouble();
        int n = (int) Math.ceil(1.0/d);
        PC2D a = new PC2D();
        Queue<Pt>[][] grid = (Queue<Pt>[][]) new Queue[n+2][n+2];
        UF uf = new UF(N);
        for (int i = 0; i <= n+1; i++)
            for (int j = 0; j <= n+1; j++)
                grid[i][j] = new Queue<Pt>();
        for (int k = 0; k < N; k++) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            PC2D.Pt t = a.new Pt(k, x, y);
            int row = 1 + (int) (x*n);
            int col = 1 + (int) (y*n);
            for (int i = row-1; i <= row+1; i++) 
                for (int j = col-1; j <=col+1; j++) 
                    for (Pt l: grid[i][j]) 
                        if (t.p.distanceTo(l.p) <= d)
                            uf.union(t.id, l.id);
            grid[row][col].enqueue(t);
        }
        int h = uf.count();
        if (h == 1)
            StdOut.println("Sim");
        else
            StdOut.println("Nao");
    }
}
