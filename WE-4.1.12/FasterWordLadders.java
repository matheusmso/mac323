/*******************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ****************/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class FasterWordLadders {
    private static class Comp implements Comparator<String>{
        private int i;
        public Comp(int i) {
            this.i = i;
        }
        public int compare(String a, String b) {
            char x = 0, y = 0;
            if (a.length() != b.length())
                return a.length() - b.length();
            else {
                for (int k = 0; k < a.length()-1; k++) {
                    x = a.charAt((k+i)%a.length());
                    y = b.charAt((k+i)%b.length());
                    if (x - y != 0)
                        return x - y;
                }
                return 0;
            }
        }
    }
    public static void main(String[] args) {
        In in = new In (args[0]);
        String[] words = in.readAllStrings();
        HashMap<String, Integer> st = new HashMap<String, Integer>();
        HashMap<Integer, String> ts = new HashMap<Integer, String>();
        int max = 0;
        int count = 0;
        for (String a : words) {
            if (st.containsKey(a)) continue;
            if (a.length() > max)
                max = a.length();
            st.put(a, count);
            ts.put(count++, a);
        }
        Graph G = new Graph (words.length);
        Arrays.sort(words, new Comp(0));
        int i = 0, j;
        while (i < words.length-1) {
            for (j = i + 1; j < words.length && words[j].length() == words[i].length(); j++);
            for (int pos = 0; pos < words[i].length(); pos++) {
                if (pos > 0)
                    Arrays.sort(words, i, j, new Comp(pos));
                for (int a = i; a < j-1; a++) {
                    for (int b = a + 1; b < j; b++) {
                        int k;
                        for (k = 0 ; k < words[a].length()-1; k++) {
                            if (words[a].charAt((k+pos)%words[a].length()) == words[b].charAt((k+pos)%words[b].length()))
                                continue;
                            else 
                                break;
                        }
                        if (k == words[a].length()-1) 
                            G.addEdge(st.get(words[a]),st.get(words[b]));
                        else
                            break;
                    }
                }
            }
            i = j;
        }
        Arrays.sort(words);
        for (i = 0; i < words.length-1; i++) {
            for (j = i+1; j < words.length; j++) {
                if (words[j].startsWith(words[i])){
                    if (words[i].length()+1 == words[j].length()) 
                        G.addEdge(st.get(words[i]), st.get(words[j]));
                }
                else
                    break;
            }
        }
        while (!StdIn.isEmpty()) {
          String from = StdIn.readString();
          String to = StdIn.readString();
          BreadthFirstPaths bfs = new BreadthFirstPaths(G, st.get(from));
          if (bfs.hasPathTo(st.get(to))) {
              StdOut.println(bfs.distTo(st.get(to)));
              for (int k : bfs.pathTo(st.get(to)))
                  StdOut.println(ts.get(k));
          }
          else
              StdOut.println("NOT CONNECTED");
        }
        in.close();
    }
}

