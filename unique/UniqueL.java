// Nome: Matheus de Mello Santos Oliveira - NUSP: 8642821

import edu.princeton.cs.algs4.*;

public class UniqueL {
    public static void main(String[] args) {
        int L = Integer.parseInt(args[0]);
        TST<Integer> st = new TST<Integer>();
        if (L > 0) {
            String s = StdIn.readAll();
            s = s.trim();
            for (int i = 0; i+L <= s.length(); i++)
                st.put(s.substring(i, i+L), i);
            StdOut.println(st.size());
            //for (String l : st.keys())
                //StdOut.println(l);
        }
        else if (args.length == 1) {
            String s = StdIn.readAll();
            int count = 0, ans = 0, deli = 10;
            while (true) {
                for (int i = 0; i+ans+1 <= s.length() && 
                        st.size() < count; i++)
                    st.put(s.substring(i, i+ans+1), i);
                //StdOut.println(count + " " + st.size());
                if (st.size() == count) {
                    ans++;
                    deli *= 10;
                    count += deli;
                }
                else if (ans > 8)
                    break;
                else
                    break;
            }
            StdOut.println(ans);
        }
        else {
            int N = Integer.parseInt(args[1]);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < N; i++) {
                int k = StdRandom.uniform(10);
                s.append(k);
            }
            int count = 10, ans = 0, deli = 10;
            while (true) {
                for (int i = 0; i+ans+1 <= s.length() &&
                        st.size() < count; i++)
                    st.put(s.substring(i, i+ans+1), i);
                //StdOut.println(st.size() + " " + count);
                if (st.size() == count) {
                    ans++;
                    deli *= 10;
                    count += deli;
                }
                else if (ans > 8)
                    break;
                else
                    break;
            }
            StdOut.println(ans);
        }
    }
}

