/*********************************************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ****************************************/
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.*;

public class LinkFinderNEW { 
    public static void main(String[] args) { 
        if (args.length > 1) {
            In in = new In(args[1]);
            String input = in.readAll();
            SET<String> set = new SET<String>();
            /* A seguinte regex da match em links que estao
             * realmente dispostos na pagina, links em comentario
             * como nao aparecem na versao "visualizavel" da pagina
             * nao sao capturados*/
            String regexp = "<a href=\"([[\\S]&&[^\"]]+).*?>";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String s = matcher.group(1);
                if (s.startsWith("http"))
                    set.add(s);
                else if (s.startsWith("/"))
                    set.add(args[1]+s);
            }
            for (String x : set)
                StdOut.println(x);
        }
        else {
            In in = new In(args[0]);
            String input = in.readAll();
            String regexp = "<a href=\"([[\\S]&&[^\"]]+).*?>";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            while(matcher.find()) {
                String s = matcher.group(1);
                if (s.startsWith("http"))
                    StdOut.println(s);
                else if (s.startsWith("/"))
                    StdOut.println(args[0]+s);
            }
        }
    }
}
