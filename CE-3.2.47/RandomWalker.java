/***********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ********************/



import edu.princeton.cs.algs4.*;


public class RandomWalker {
    int x;
    int y;
    public RandomWalker() {
        x = 0;
        y = 0;
    }
    public void step(int random) {
        switch (random) {
            case 0: x++; break;
            case 1: y--; break;
            case 2: x--; break;
            case 3: y++; break;
        }
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomWalker a = new RandomWalker();
        for (int i = 0; i < N; i++) {
            a.step(StdRandom.uniform(4));
            StdOut.println(a.x + " " + a.y);
        }
        StdOut.println("\n" + a.x + " " + a.y);
    }
}

