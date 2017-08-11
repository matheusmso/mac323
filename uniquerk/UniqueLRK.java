/**********************************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ********************************/

import edu.princeton.cs.algs4.*;
import java.math.BigInteger;
import java.util.*;

public class UniqueLRK {
    private static long Q = longRandomPrime();        
    private static int R = 256;
    private static long RM = 1;
    private static long hash(String key, int M) { 
        long h = 0; 
        for (int j = 0; j < M; j++) 
            h = (R * h + key.charAt(j)) % Q; 
        return h;
    }
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
    public static void main(String[] args) {
        int L = Integer.parseInt(args[0]);
        HashSet<Long> st = new HashSet<Long>();
        if (L > 0) {
            for (int i = 1; i < L; i++)
                RM = (R*RM)%Q;
            String s = StdIn.readAll();
            s = s.trim();
            if (s.length() < L)
                StdOut.println("Tamanho indevido");
            else {
                long h = hash(s, L);
                st.add(h);
                for (int i = L; i < s.length(); i++) {
                    h = (h + Q - RM*s.charAt(i-L) % Q) % Q;
                    h = (h*R + s.charAt(i)) % Q;
                    st.add(h);
                }
                StdOut.println(st.size());
            }
        }
        else if (args.length == 1) {
            String s = StdIn.readAll();
            int count = 10, ans = 0, deli = 10;
            while (true) {
                long h = hash(s, ans+1);
                st.add(h);
                for (int i = ans+1; i < s.length() && st.size() < count; i++) {
                    h = (h + Q - RM*s.charAt(i-ans-1) % Q) % Q;
                    h = (h*R + s.charAt(i)) % Q;
                    st.add(h);
                }
                RM = (R*RM)%Q;
                if (st.size() == count) {
                    ans++;
                    deli *= 10;
                    count += deli;
                }
                else 
                    break;
            }
            StdOut.println(ans);
        }
        else {
            int N = Integer.parseInt(args[1]);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < N; i++)
                s.append(StdRandom.uniform(10));
            int count = 10, ans = 0, deli = 10;
            while (true ){
                long h = hash(s.toString(), ans+1);
                st.add(h);
                for (int i = ans+1; i < N && st.size() < count; i++) {
                    h = (h + Q - RM*s.charAt(i-ans-1) % Q) % Q;
                    h = (h*R + s.charAt(i)) % Q;
                    st.add(h);
                }
                RM = (R*RM)%Q;
                if (st.size() == count) {
                    ans++;
                    deli *= 10;
                    count += deli;
                }
                else break;
            }
            StdOut.println(ans);
        }
    }
}

