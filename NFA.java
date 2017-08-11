/****************************************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * **************************************/

import edu.princeton.cs.algs4.*;

public class NFA { 

    private RedBlackBST<Integer, String> map;
    private Digraph G;         // digraph of epsilon transitions
    private String regexp;     // regular expression
    private int M;    // number of characters in regular expression

    // Create the NFA for the given RE   
    public NFA(String regexp) {
        this.regexp = regexp;
        M = regexp.length();
        Stack<Integer> ops = new Stack<Integer>(); 
        G = new Digraph(M+1); 
        for (int i = 0; i < M; i++) { 
            int lp = i; 
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') 
                ops.push(i); 
            else if (regexp.charAt(i) == ')') {
                int or = ops.pop(); 

                // 2-way or operator
                if (regexp.charAt(or) == '|') { 
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                }
                else if (regexp.charAt(or) == '(')
                    lp = or;
                else assert false;
            } 

            // closure operator (uses 1-character lookahead)
            if (i < M-1 && regexp.charAt(i+1) == '*') { 
                G.addEdge(lp, i+1); 
                G.addEdge(i+1, lp);
            } 
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') 
                G.addEdge(i, i+1);
        } 
    } 

    // Does the NFA recognize txt? 
    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(G, 0);
        Bag<Integer> pc = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);

        // Compute possible NFA states for txt[i+1]
        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc) {
                if (v == M) continue;
                if ((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.') 
                    match.add(v+1);
            }
            dfs = new DirectedDFS(G, match); 
            pc = new Bag<Integer>();
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);
            // optimization if no states reachable
            if (pc.size() == 0) return false;
        }

        // check for accept state
        for (int v : pc)
            if (v == M) return true;
        return false;
    }

    public void drakaris(String txt) {
        DirectedDFS dfs = new DirectedDFS(G, 0);
        Bag<Integer> pc = new Bag<Integer>();
        map = new RedBlackBST<Integer, String>();
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v) && txt.charAt(0) == regexp.charAt(v)) 
                pc.add(v);
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, 0);
        for (int dest : pc) {
            String t = "0"; 
            for (int v : bfs.pathTo(dest))
                t += "e"+v;
            map.put(dest, t.substring(2,t.length()));
        }
        pc = new Bag<Integer>();
        RedBlackBST<Integer, String> temp = new RedBlackBST<Integer, String>();
        for (int v : map.keys()) {
            if (Character.isLetter(regexp.charAt(v))) {
                temp.put(v+1, map.get(v)+"m"+(v+1));
                pc.add(v+1);
            }
        }
        for (int v : temp.keys())
            map.put(v, temp.get(v));
        for (int i = 1; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<Integer>();
            for (int from : pc) {
                dfs = new DirectedDFS(G, from);
                for (int v = 0; v < G.V(); v++)
                    if (dfs.marked(v) && txt.charAt(i) == regexp.charAt(v))
                        match.add(v);
            }
            for (int dest : match) {
                for (int from : pc) {
                    bfs = new BreadthFirstDirectedPaths(G, from);
                    String ta = map.get(from);
                    String t = "";
                    if (bfs.hasPathTo(dest)) {
                        for (int v : bfs.pathTo(dest))
                            t += "e"+v;
                        map.put(dest, ta+t.substring(2,t.length()));
                    }
                }
            }
            pc = new Bag<Integer>();
            temp = new RedBlackBST<Integer, String>();
            for (int v : map.keys()) {
                if (Character.isLetter(regexp.charAt(v))) {
                    temp.put(v+1, map.get(v)+"m"+(v+1));
                    pc.add(v+1);
                }
            }
            for (int v : temp.keys())
                map.put(v, temp.get(v));
        }
        Bag<Integer> match = new Bag<Integer>();
        for (int from : pc) {
            dfs = new DirectedDFS(G, from);
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v) && v+1 == regexp.length())
                    map.put(v+1, map.get(v)+"e"+(v+1));
        }
    }

    public void printSol() {
        String s = map.get(regexp.length());
        for (int i = 0; i < s.length(); i++)
            StdOut.print(s.charAt(i) + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        String s = StdIn.readString();
        String regexp = s;
        int N = StdIn.readInt();
        for (int k = 0; k < N; k++) {
            String txt = StdIn.readString();
            if (txt.indexOf('|') >= 0) {
                throw new IllegalArgumentException("| character in text is not supported");
            }
            NFA nfa = new NFA(regexp);
            if (nfa.recognizes(txt)) {
                StdOut.println("true");
                nfa.drakaris(txt);
                nfa.printSol();
            }
            else
                StdOut.println("false");
        }
    }
}
