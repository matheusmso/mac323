// Nome: Matheus de Mello Santos Oliveira
// NUSP: 8642821

import edu.princeton.cs.algs4.*;

public class Generator {
   public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      int H = Integer.parseInt(args[2]);
      int A = Integer.parseInt(args[3]);
      StdOut.println(N + H + A);
      for (int i = 0; i < M; i++) {
         StdOut.print(StdRandom.uniform(N) + " ");
         StdOut.println(StdRandom.uniform(N));
      }
      for (int j = 0; j < H; j++) {
         for (int i = 0; i < (int) Math.ceil(.1 * N); i++) { //Hubs
            StdOut.print(StdRandom.uniform(N) + " ");
            StdOut.println(N + j);
         }
      }
      for (int j = 0; j < A; j++) {
         for (int i = 0; i < (int) Math.ceil(.1 * N); i++) { //Authorities
            StdOut.print(N + H + j + " ");
            StdOut.println(StdRandom.uniform(N));
         }
      }
   }
}

/*
O arquivo Transition.java foi modificado em relacao ao original da
seguinte forma:

na linha 55 do original temos:
// Print probability for column j.
e temos um for na linha logo abaixo.
Este for foi substituido por:
for (int j = 0; j < N; j++) {
   double p;
   if (outDegree[i] == 0) {
      p = 1.0/N;
   }
   else {
      p = .90*counts[i][j]/outDegree[i] + .10/N;
   }
   StdOut.printf("%7.5f ", p);
}
para seguir a nova regra descrita no enunciado do PACA.

Os resultados dos hubs and authorities quando introduzidos sao de que
ambos tem PageRank bem mais baixo que as paginas "comuns", mas percebe-se
que as authorities tem um PageRank ainda mais baixo que os hubs.

Exemplo:

para java Generator 10 10 4 4 | java Transition | java RandomSurfer 1000000
temos:
0.06623 0.04312 0.05626 0.10223
0.05633 0.10683 0.05760 0.03023
0.12196 0.03939
//Hubs
0.04320 0.06568 0.04309 0.04692
//Authorities
0.03048 0.02988 0.03078 0.02979

por construcao os authorities sao os 4 ultimos e os hubs sao os quatro
anteriores.
*/
