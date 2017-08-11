/************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ***************/

import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordFrequencies {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        List<Word> sol = new ArrayList<Word>();
        while (!StdIn.isEmpty()) {
            String w = StdIn.readString();
            list.add(w.toLowerCase());
        }
        Collections.sort(list);
        int f = 1;
        String now, before;
        before = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            now = list.get(i);
            if (now.equals(before))
                f++;
            else {
                Word a = new Word();
                a.w = before;
                a.f = f;
                sol.add(a);
                f = 1;
            }
            before = now;
        }
        Collections.sort(sol, new Comparator<Word>() {
            public int compare(Word x, Word y) {
                if (x.f > y.f) return -1;
                if (x.f < y.f) return 1;
                return 0;}});
        for (Word x: sol)
            StdOut.println(x);
    }
}

