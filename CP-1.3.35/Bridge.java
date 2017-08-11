/********************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * para compilar e necessario usar a flag -Xlint:unchecked
 * ******************/


import edu.princeton.cs.algs4.*;

public class Bridge {
    public static void main(String[] args) {
        RandomQueue<Card> a = new RandomQueue<Card>();
        for(int i = 0; i < 52; i++)
            a.enqueue(new Card(i));
        for(int i = 0; i < 13; i++)
            StdOut.println(a.dequeue());
    }
}
