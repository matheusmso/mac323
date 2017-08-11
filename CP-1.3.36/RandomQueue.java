

import java.util.Iterator;
import edu.princeton.cs.algs4.*;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;
    public RandomQueue() {
        s = (Item[]) new Object[52];
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public void enqueue(Item item) {
        if (N == s.length)
            resize(2*s.length);
        s[N++] = item;
    }
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }
    public Item dequeue() {
        int i = StdRandom.uniform(N);
        Item aux = s[i];
        s[i] = s[--N];
        return aux;
    }
    public Item sample() {
        return s[StdRandom.uniform(N)];
    }
    public Iterator<Item> iterator() {
        return new RQIterator();
    }
    private class RQIterator implements Iterator<Item> {
        private int notVisited = N;
        public boolean hasNext() {
            return notVisited > 0;
        }
        public Item next() {
            int i = StdRandom.uniform(notVisited--);
            Item aux = s[i];
            s[i] = s[notVisited];
            s[notVisited] = aux;
            return aux;
        }
    }
}

