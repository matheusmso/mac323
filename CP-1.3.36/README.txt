Nome: Matheus de Mello Santos Oliveira
NUSP: 8642821

O iterador foi implementado de maneira similar ao metodo dequeue.
Sorteamos um numero aleatorio e trocamos a ultima casa com a casa da posicao
sorteada, nao removendo-a, porem neste novo cenario a penultima carta torna-se 
a ultima.

Para montar o histograma e preciso enumerar as permutacoes, ou seja, precisamos
diferenciar 1234 de 1243, por exemplo. Para tal fazemos o seguinte:

1 esta na primeira posicao neste cenario, onde e o primeiro; logo o removemos 
e nao sabemos nada.(+0 x o numero de permutacoes que ja passaram, que e 0 tambem)
2 esta na primeira posicao neste cenario, onde e o primeiro; logo o removemos
e nao sabemos nada.(+0 x 0)
4 esta na primeira posicao neste cenario, porem e o segundo; logo sabemos que ele
esta 1 a frente das permutacoes anteriores (+1 x 1 pois a permutacao 34 ja passou)
3 esta na primeira posicao neste cenario e e o primeiro; logo nao sabemos nada (+0).
Como podemos verificar, indexando de 0 a N!-1 temos que a permutacao 1243 e a 1 (segunda).

Agora, para sabermos quem e segundo, primeiro ou quinto, temos que verificar para cada
cenario a posicao relativa de cada membro, e para isso usamos um vetor de zeros e uns, onde 
a casa de p vale um quando o p em questao esta presente no cenario e zero caso p ja tehna sido
removida. Assim a posicao relativa de p num devido cenario e a soma ate ele (< p) desse vetor 
auxiliar.

Em seguida usamos esse valor da posicao relativa vezes o numero de permutacoes que ja passaram, 
ou seja, (n-1)! do n do cenario (n = tamanho da colecao do cenario).

Digamos que temos N = 4 e a permutacao em questao seja: 1342

1 esta em primeiro e deveria ser primeiro; logo 0*(4-1)! = 0, somamos 0 e "removemos" o 1, 
resultando no cenario 342.
3 esta em primeiro mas deveria ser segundo (2,3,4); logo somamos 1*(3-1)! = 2 e "removemos" o 3
resultando no cenario 42.
4 esta em primeiro mas deveria ser o segundo (2,4); logo somamos 1*(2-1)! = 1 e "removemos" o 4
2 esta em primeiro e deveria ser primeiro; logo somamos 0*(1-1)! = 0.
Desta forma essa permutacao e a quarta, numero 3 indexado de 0 e podemos verificar a verdade:
1234
1243
1324
1342***
...

As imagens em anexo (N = 5 e T = 1.000 crescendo de *10 ate 1.000.000) mostram que para n pequeno 
ate existe uma pequena variacao no histograma, mas conforme o T cresce percebemos que este tende 
a um bloco, com a mesma frequencia para todas as permutacoes, e essa frequencia tende a T/(N!).

Para N = 5, T = 1000 temos max = 15 T/(N!) = 8.3
Para N = 5, T = 10000 temos max = 105 T/(N!) = 83.3
Para N = 5, T = 100000 temos max = 908 T/(N!) = 833.3
Para N = 5, T = 1000000 temos max = 8563 T/(N!) = 8333.3

Podemos perceber que para T->inf freq max = T/(N!) 

Como cada permutacao e independente da anterior, e o numero de permutacoes de
uma colecao de tamanho N e N!:

**Tentativa de prova formal**

Temos que para n = 1
o maxn = max1 = T/n! = T
para n = 2
max2 = T/n! = T/2 = max1 * (1/2)

assumimos para que e valido para n e temos:
maxn = T/n! = max(n+1)*(n+1)

para n+1 temos:

T/n! * 1/(n+1) = max(n+1)*(n+1)*1/(n+1) =>
T/(n+1)! = max(n+1)








