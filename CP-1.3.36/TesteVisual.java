/************************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * para compilar e necessario o uso da flag -Xlint:unchecked
 * ********************/




import edu.princeton.cs.algs4.*;


public class TesteVisual {
    private double[] freq;
    private double max;
    public TesteVisual() {}
    public void addDataPoint(int x) {
        freq[x]++;
        if  (freq[x] > max)
            max = freq[x];
    }
    public void draw() {
        StdDraw.setYscale(-1, max + 1);
        StdStats.plotBars(freq);
    }
    int fat(int x) {
        if (x == 0)
            return 1;
        return x*fat(x-1);
    }
    int posrel(int[] a, int pos) {
        int sum = 0;
        for (int i = 1; i < pos; i++)
            sum += a[i];
        return sum;
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        RandomQueue<Integer> a = new RandomQueue<Integer>();
        TesteVisual histogram = new TesteVisual();
        histogram.freq = new double[histogram.fat(N)];
        for (int i = 0; i < N; i++)
            a.enqueue(i+1);
        for (int i = 0; i < T; i++) {
            int sum = 0;
            int size = N;
            int[] inside  = new int[N+1];
            for (int j = 1; j < N+1; j++)
                inside[j] = 1;
            for (int x: a) {
                //StdOut.print(x);
                sum += histogram.posrel(inside, x)*histogram.fat(size-1);
                size--;
                inside[x] = 0;
            }
            //StdOut.println("a" + sum);
            histogram.addDataPoint(sum);
        }
        StdDraw.setCanvasSize(1000, 150);
        histogram.draw();
        //StdOut.println(histogram.max);
    }
}

