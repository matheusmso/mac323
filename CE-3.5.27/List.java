/***************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ******************/

import edu.princeton.cs.algs4.*;
import java.util.Iterator;

public class List<Item extends Comparable<Item>> implements Iterable<Item> {
    private int N;
    private RedBlackBST<Double, Item> st;
    private RedBlackBST<Item, SET<Double>> aux;
    private double low;
    private double high;
    public List() {
        st = new RedBlackBST<Double, Item>();
        aux = new RedBlackBST<Item, SET<Double>>();
        N = 0;
        low = high = 0.0;
    }
    public void addFront(Item item) {
        low = low - 1.0;
        double t = low;
        st.put(t, item);
        if(aux.contains(item)) {
            SET<Double> a = aux.get(item);
            a.add(t);
        }
        else {
            SET<Double> a = new SET<Double>();
            a.add(t);
            aux.put(item, a);
        }
        N++;
    }
    public void addBack(Item item) {
        high = high + 1.0;
        double t = high;
        st.put(t, item);
        if(aux.contains(item)) {
            SET<Double> a = aux.get(item);
            a.add(t);
        }
        else {
            SET<Double> a = new SET<Double>();
            a.add(t);
            aux.put(item, a);
        }
        N++;
    }
    public Item deleteFront() {
        Item item = null;
        if (N == 0) 
            return item;
        else {
            double t = st.select(0);
            item = st.get(t);
            st.deleteMin();
            SET<Double> a = aux.get(item);
            if(a.size() == 1)
                aux.delete(item);
            else
                a.delete(t);
            N--;
        }
        return item;
    }
    Item deleteBack() {
        Item item = null;
        if (N == 0) 
            return item;
        else {
            double t = st.select(N-1);
            item = st.get(t);
            st.deleteMax();
            SET<Double> a = aux.get(item);
            if(a.size() == 1)
                aux.delete(item);
            else
                a.delete(t);
            N--;
        }
        return item;
    }
    void delete(Item item) {
        if (!aux.contains(item))
            return;
        else {
            SET<Double> a = aux.get(item);
            aux.delete(item);
            for (double x : a) {
                st.delete(x);
                N--;
            }
        }
    }
    public void add(int i, Item item) {
        if(i > N)
            return;
        if(i == 0) {
            addFront(item);
            return;
        }
        if(i == N) {
            addBack(item);
            return;
        }
        double left = st.select(i-1);
        double right = st.select(i);
        st.put((left+right)/2, item);
        if(aux.contains(item)) {
            SET<Double> a = aux.get(item);
            a.add((left+right)/2);
        }
        else {
            SET<Double> a = new SET<Double>();
            a.add((left+right)/2);
            aux.put(item, a);
        }
        N++;
    }
    Item delete(int i) {
        Item item = null;
        if (i > N)
            return item;
        else {
            double t = st.select(i);
            item = st.get(t);
            st.delete(t);
            SET<Double> a = aux.get(item);
            if(a.size() == 1)
                aux.delete(item);
            else
                a.delete(t);
            N--;
        }
        return item;
    }
    public boolean contains(Item item) {
        return aux.contains(item);
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return N;
    }
    Item get(int i) {
        Item item = null;
        if (i >= N)
            return item;
        else {
            double t = st.select(i);
            item = st.get(t);
        }
        return item;
    }
    public Iterator<Item> iterator() { return new ListIterator(); }
    private class ListIterator implements Iterator<Item> {
        private Iterable<Double> q = st.keys();
        Iterator<Double> it = q.iterator();
        public boolean hasNext() { return it.hasNext(); }
        public Item next() {
            double x = it.next();
            Item item = st.get(x);
            return item;
        }
    }
    public static void main(String[] args) {
        List<String> a = new List<String>();
            a.addFront("a");
            a.addBack("IME");
            for (int i = 0; i < 4; i++)
                a.addBack("POLI");
            for(String s: a)
                StdOut.print(s + " ");
            StdOut.println();
        a.deleteFront();
        a.deleteBack();
        a.deleteBack();
        a.delete(1);
        for (String s: a)
            StdOut.print(s + " ");
        StdOut.println();
        a.delete("POLI");
        for (String s: a)
            StdOut.print(s + " ");
        StdOut.println();
        for (int i = 0; i < 10000; i++)
            a.addFront("ALOW");
        StdOut.println(a.size());
        for (int i = 0; i < 10000; i++)
            a.addBack("TCHAU");
        StdOut.println(a.size());
        for (int i = 0; i < 9997; i++)
            a.deleteBack();
        StdOut.println(a.size());
        StdOut.println(a.get(10000));
        a.delete("ALOW");
        a.delete("TCHAU");
        for (String s: a)
            StdOut.print(s);
        StdOut.println();
    }
}

