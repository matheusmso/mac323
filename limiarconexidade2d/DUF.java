/*************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ***********/

import edu.princeton.cs.algs4.*;

public class DUF {
    private int[] id;
    private int[] sz;
    private int count;
    private int usable;
    private int capacity;

    public DUF() {
        count = 1;
        usable = 0;
        capacity = 1;
        id = new int[capacity];
        sz = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int newSite() {
        usable++;
        count++;
        if (usable >= capacity)
            resize();
        id[usable] = usable;
        sz[usable] = 1;
        return usable;
    }

    private void resize () {
        capacity *= 2;
        int[] idx = new int[capacity];
        int[] szx = new int[capacity];
        for (int i = 0; i < usable; i++) {
            idx[i] = id[i];
            szx[i] = sz[i];
        }
        id = idx;
        sz = szx;
    }

    public int count () {
        return count;
    }

    public int find (int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected (int x, int y) {
        return find(x) == find(y);
    }

    public void union (int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx == rooty) return;
        if (sz[rootx] < sz[rooty]) {
            id[rootx] = rooty;
            sz[rooty] += sz[rootx];
        }
        else {
            id[rooty] = rootx;
            sz[rootx] += sz[rooty];
        }
        count--;
    }

    public static void main (String[] args) {
        DUF uf = new DUF();
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            while (x > uf.usable || y > uf.usable)
                uf.newSite();
            if (uf.connected (x, y))
                continue;
            uf.union(x, y);
        }
        for (int i = 0; i <= uf.usable; i++)
            if (i != uf.find(i))
                StdOut.println(i + " " + uf.find(i));
    }
}




