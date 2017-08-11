// Nome: Matheu de Mello Santos Oliveira - NUSP: 8642821

import edu.princeton.cs.algs4.*;

public class TrieST<Value> {
    private static final int R = 256;        // extended ASCII


    private Node root;      // root of trie
    private int N;          // number of keys in trie

    // R-way trie node
    private static class Node {
        private Object val;
        private int k;
        private Node[] next = new Node[R];
    }

    public TrieST() {
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public String floor(String x) {
        return floor(root, x, new StringBuilder());
    }

    private String floor(Node x, String key, StringBuilder a) {
        if (key.length() == a.length()) {
            if (x.val != null)// && key.compareTo(a) == 0)
                return a.toString();
            else 
                return null;
        }
        char c = key.charAt(a.length());
        if (x.next[c] != null) {
            String t = floor(x.next[c], key, a.append(c));
            if (t != null)
                return t;
            else
                a.deleteCharAt(a.length()-1);
        }
        int i;
        for (i = c-1; i >= 0; i--)
            if (x.next[i] != null)
                break;
        if (i >= 0) {
            String t = max(x.next[i], a.append(Character.toString((char) i)));
            if (t != null)
                return t;
            else
                a.deleteCharAt(a.length()-1);
        }
        if (x.val != null) {
            return a.toString();
        }
        return null;
    }

    private String max(Node x, StringBuilder a) {
        for (int i = 255; i >= 0; i--) {
            if (x.next[i] != null) {
                String t = max(x.next[i], a.append(Character.toString((char) i)));
                if (t != null)
                    return t;
                else {
                    a.deleteCharAt(a.length()-1);
                    break;
                }
            }
        }
        if (x.val != null)
            return a.toString();
        return null;
    }

    public String ceil(String x) {
        return ceil(root, x, new StringBuilder());// + " RESPOSTA";
    }

    private String ceil(Node x, String key, StringBuilder a) {
        if (key.length() == a.length()) {
            //StdOut.println("LOW");
            if (x.val != null)
                return a.toString();
            else
                return min(x, a);
        }
        char c = key.charAt(a.length());
        //StdOut.println(a+c + "1");
        if (x.next[c] != null) {
            String t = ceil(x.next[c], key, a.append(c));
            if (t != null)
                return t;
            else
                a.deleteCharAt(a.length()-1);
        }
        int i;
        for (i = c+1; i < 256; i++) 
            if (x.next[i] != null)
                break;
        //StdOut.println(Character.toString((char) i) + "2" + a + " " + i);
        if (i < 256) {
            String t = min(x.next[i], a.append(Character.toString((char) i)));
            if (t!= null)
                return t;
        }
        return null;
    }

    private String min(Node x, StringBuilder a) {
        //StdOut.println(a + "min");
        if (x.val != null)
            return a.toString();
        for (int i = 0; i < 256; i++) {
            if (x.next[i] != null) {
                String t = min(x.next[i], a.append(Character.toString((char) i)));
                if (t != null)
                    return t;
                else 
                    break;
            }
        }
        return null;
    }

    public int test() {
        return count;
    }

    public int rank(String key) {
        return rank(root, key, 0);
    }

    private int rank(Node x, String key, int d) {
        if (x == null || d == key.length())
            return 0;
        char c = key.charAt(d);
        int l = 0;
        for (int i = 0; i < c; i++) 
            if (x.next[i] != null) 
                l += x.next[i].k;
        //StdOut.println(c + " " + l);
        if (x.val != null)
            l++;
        return l + rank (x.next[c], key, d+1);
    }

    public int count;

    public String select(int i) {
        if (i > -1 && i < N)
            return select(root, i, new StringBuilder());
        else 
            return null;
    }

    private String select(Node x, int i, StringBuilder a) {
        if (i >= x.k || i < 0)
            return null;
        if (i == 0)
            return min(x, a);
        int c;
        String t = null;
        for (c = 0; c < 256; c++) {
            if (x.next[c] != null) {
                if (x.val != null) i--;
                t = select(x.next[c], i, a.append(Character.toString((char) c)));
                a.deleteCharAt(a.length()-1);
                i -= x.next[c].k;
                if (t != null)
                    break;
            }
        }
        //StdOut.println(i + " " + Character.toString((char) c) + " " + a);
        return t;
    }

    public void put(String key, Value val) {
        int i = 1;
        if (contains(key)) i = 0;
        if (val == null) delete(key);
        else root = put(root, key, val, 0, i);
    }

    private Node put(Node x, String key, Value val, int d, int i) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null) N++;
            x.k += i;
            count++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.k += i;
        count++;
        x.next[c] = put(x.next[c], key, val, d+1, i);
        return x;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.val != null) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
            results.enqueue(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

    public void delete(String key) {
        int i = 0;
        if (contains(key)) i = 1;
        else return;
        root = delete(root, key, 0, i);
    }

    private Node delete(Node x, String key, int d, int i) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) N--;
            x.k -= i;
            count--;
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c].k -= i;
            count--;
            x.next[c] = delete(x.next[c], key, d+1, i);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    public static void main(String[] args) {

        // build symbol table from standard input
        TrieST<Integer> st = new TrieST<Integer>();
        String[] in = {
            "she",
            "sells",
            "sea",
            "shells",
            "by",
            "the",
            "sea",
            "shore" };
        for (int i = 0; i < in.length; i++)
            st.put(in[i], i);
        // print results
        for (String key : st.keys())
            StdOut.println(key + " " + st.get(key));
        StdOut.println();
        
        StdOut.println(st.rank("she"));
        StdOut.println(st.rank("sells"));
        StdOut.println(st.rank("sea"));
        StdOut.println(st.rank("shells"));
        StdOut.println(st.rank("by"));
        StdOut.println(st.rank("the"));
        StdOut.println(st.rank("sea"));
        StdOut.println(st.rank("shore"));
        
        for (int i = 0; i < st.size(); i++)
            StdOut.println(st.select(i));

        StdOut.println(st.select(1));

        st.delete("sea");
        st.delete("shwhatever");
        
        StdOut.println(st.rank("she"));
        StdOut.println(st.rank("sells"));
        StdOut.println(st.rank("sea"));
        StdOut.println(st.rank("shells"));
        StdOut.println(st.rank("by"));
        StdOut.println(st.rank("the"));
        StdOut.println(st.rank("sea"));
        StdOut.println(st.rank("shore"));

        StdOut.println(st.select(2));
        
        StdOut.println(st.floor("sellsxyz"));
        StdOut.println(st.floor("sell"));
        StdOut.println(st.floor("sells"));
        StdOut.println(st.ceil("sh"));
        StdOut.println(st.ceil("shellsabc"));
        StdOut.println(st.ceil("shells"));
        StdOut.println(st.test());
        StdOut.println(st.select(5));
        st.put("they", 90);
        StdOut.println(st.select(6));
        st.put("theyus", 190);
        StdOut.println(st.select(6));
        StdOut.println(st.select(7));
    }
}

