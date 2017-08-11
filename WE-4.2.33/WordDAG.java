/*********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ******************/

import edu.princeton.cs.algs4.*;
import java.util.*;
import java.math.*;

public class WordDAG {
    private HashMap<String, Integer> st;
    private HashMap<Integer, String> ts;
    private Digraph D;
    private int count;
    private BigInteger[] cache;
    private boolean[] seen;
    private class Comp implements Comparator<String> {
        private int i;
        public Comp(int i) {
            this.i = i;
        }
        public int compare(String a, String b) {
            if (a.length() == b.length()) {
                int l = a.length();
                for (int k = 0; k < l; k++) {
                    int x = a.charAt((k+i)%l) - b.charAt((k+i)%l);
                    if (x != 0) return x;
                }
            }
            return a.length() - b.length();
        }
    }
    public WordDAG (String[] words) {
        st = new HashMap<String, Integer>();
        ts = new HashMap<Integer, String>();
        int max = 0;
        count = 0;
        for (String a : words) {
            if (st.containsKey(a)) continue;
            if (a.length() > max)
                max = a.length();
            st.put(a, count);
            ts.put(count++, a);
        }
        D = new Digraph (count);
        Arrays.sort(words, new Comp(0));
        int i = 0, j;
        while (i < words.length-1) {
            for (j = i + 1; j < words.length && words[j].length() == words[i].length(); j++);
            for (int pos = 0; pos < words[i].length(); pos++) {
                Arrays.sort(words, i, j, new Comp(pos));
                for (int a = i; a < j-1; a++) {
                    for (int b = a + 1; b < j; b++) {
                        int k;
                        for (k = 0 ; k < words[a].length()-1; k++)
                            if (words[a].charAt((k+pos)%words[a].length()) != words[b].charAt((k+pos)%words[b].length()))
                                break;
                        if (k == words[a].length()-1)
                            D.addEdge(st.get(words[b]),st.get(words[a]));
                        else break;
                    }
                }
            }
            i = j;
        }
        Arrays.sort(words);
        for (i = 0; i < words.length-1; i++) {
            for (j = i+1; j < words.length; j++) {
                if (words[j].startsWith(words[i])){
                    if (words[i].length()+1 == words[j].length()) {
                        D.addEdge(st.get(words[j]), st.get(words[i]));
                    }
                }
                else
                    break;
            }
        }
    }
    public void PrintDAG() {
        Out out = new Out("saida.txt");
        for (int i = 0; i < count; i++)
            for (int k : D.adj(i))
                out.println(ts.get(i)+" "+ts.get(k));
        out.close();
    }
    public void PrintPathCount(String a, String b) {
        cache = new BigInteger[D.V()];
        seen = new boolean[D.V()];
        Arrays.fill(seen, false);
        Out out = new Out("saida.txt");
        out.println(count(D, st.get(a),st.get(b)));
        out.close();
    }
    private BigInteger count (Digraph D, int u, int v) {
        if (u == v) return BigInteger.ONE;
        if (seen[u]) return cache[u];
        BigInteger paths = BigInteger.ZERO;
        for (int i : D.adj(u))
            paths = paths.add(count (D, i, v));
        seen[u] = true;
        return cache[u] = paths;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        ArrayList<String> arr = new ArrayList<String>();
        String from = in.readString();
        String to = in.readString();
        while (!in.isEmpty()) {
            String a = in.readString();
            arr.add(a);
        }
        String[] words = new String[arr.size()];
        words = arr.toArray(words);
        //String[] words = {"cobra", "sobra", "sobrar", "cobro", "cobrou", "sobrando", "cobre"};
        WordDAG w = new WordDAG(words);
        w.PrintDAG();
        //w.PrintPathCount(from, to);
        in.close();
    }
}

