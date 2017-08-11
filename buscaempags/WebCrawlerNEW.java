/******************************************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ***************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.*;

public class WebCrawlerNEW { 
    public static void main(String[] args) { 
        String s = args[0];
        int N = Integer.parseInt(args[1]);
        Queue<String> q = new Queue<String>();
        q.enqueue(s);
        SET<String> set = new SET<String>();
        if (s.charAt(s.length()-1) == '/')
            s = s.substring(0,s.length()-1);
        set.add(s);
        while (!q.isEmpty()) {
            String v = q.dequeue();
            try {
                In in = new In(v);
                String input = in.readAll();
                String regexp = "<a href=\"([[\\S]&&[^\"]]+)";
                Pattern pattern = Pattern.compile(regexp);
                Matcher matcher = pattern.matcher(input);
                while (matcher.find()) {
                    String w = matcher.group(1);
                    if (w.length() < 2) continue;
                    if (w.charAt(w.length()-1) == '/')
                        w = w.substring(0,w.length()-1);
                    if (w.startsWith("/")) 
                        w = s+w;
                    if (w.startsWith(s) && !set.contains(w)) {
                        q.enqueue(w);
                        set.add(w);
                        if (set.size() == N)
                            break;
                    }
                }
                if (set.size() == N)
                    break;
            }
            catch (Exception e) {
                continue;
            }
        }
        for (String k : set)
            StdOut.println(k);
    }
}
