/********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * *****************/

import edu.princeton.cs.algs4.*;

public class LevelTraversal {
    private Node root;
    
    private class Node {
        private int key;
        private Node left, right;

        public Node (int key) {
            this.key = key;
        }
    }

    public Node get (int key) {
        return get(root, key);
    }

    private Node get (Node r, int key) {
        if (r == null)
            return null;
        if (key < r.key)
            return get (r.left, key);
        else if (key > r.key)
            return get (r.right, key);
        else
            return r;
    }

    public void put (int key) {
        root = put (root, key);
    }

    private Node put (Node r, int key) {
        if (r == null) 
            return new Node (key);
        if (key < r.key)
            r.left = put (r.left, key);
        else if (key > r.key)
            r.right = put (r.right, key);
        else 
            r.key = key;
        return r;
    }

    public void printLevel (Node r) {
        Queue<Node> q = new Queue<Node>();
        if (r != null)
            q.enqueue(r);
        while (!q.isEmpty()) {
            Node t = q.dequeue();
            StdOut.print(t.key + " ");
            if (t.left != null)
                q.enqueue (t.left);
            if (t.right != null) 
                q.enqueue (t.right);
        }
        StdOut.println();
    }

    public static void main (String[] args) {
        LevelTraversal st = new LevelTraversal();
        int N, M;
        N = StdIn.readInt();
        M = StdIn.readInt();
        for (int i = 0; i < N; i++)
            st.put(StdIn.readInt());
        for (int i = 0; i < M; i++) {
            int x = StdIn.readInt();
            st.printLevel(st.get(x));
        }
    }
}


