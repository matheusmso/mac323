Nome: Matheus de Mello Santos Oliveira
NUSP: 8642821

Identificacao dos arquivos:

RedBlackBST.java - implementacao original de llrbt de S&W, sem caching
RedBlackBSTCaching.java - implementacao original de llrbt de S&W, com caching
FrequencyCounter.java - original de S&W implementado usando ST.java
FrequencyCounterRedBlackNoCaching - utiliza RedBlackBST.java ao invez de ST.java
FrequencyCounterRB.java - utiliza RedBlackBSTCaching.java

O inteiro que todos os FrequencyCounter leem implicam em quao grande a arvore
sera, ou seja, se o inteiro for pequeno, quase todas as palavras do texto entrarao
na arvore, ao passo que quando este cresce, o programa se torna mais seletivo, e 
a arvore por consequencia menor. Para testar a diferenca do caching de uma forma
mais realista precisa-se de um input grande, como por exemplo o leipzig1m.txt, porem
se limitarmos o int para 8 utilizaria-se apenas uma fracao do arquivo. Com isso em
mente os testes a seguir foram realizados com o inteiro setado para 1.

Arquivo utilizado no teste: leipzig1m.txt
Tempos registrados estao em segundos

10 testes do FrequencyCounter original, que utiliza ST.java: 24.883
10 testes do FrequencyCounterRedBlackNoCaching, que utiliza as llrbt de S&W: 30.007
10 testes do FrequencyCounterRB, que utiliza as llrbt de S&W, modificado para ter caching:
17.893

Com inteiros maiores, a diferenca e praticamente imperceptivel, para n = 7 temos tempos
praticamente iguais e para n > 8 temos tempos iguais.

Para n = 1 percebe-se que ha uma melhora de quase 30% em relacao ao tempo original de 24.883
(17.893/24.883 = 0.719).

Em relacao a implementacao de S&W de llrbt esta e um pouco mais lenta que a incluida na 
biblioteca padrao do java, provavelmente pelo fato da implementacao de S&W ter sido produzida
para fins didaticos.

Essa diferencao de velocidade entre a versao de S&W com e sem caching se da principalmente pelo 
fato de que o algoritimo do FrequencyCounter, para cada palavra sempre chama o contais (que 
chama o get) para depois ou chamar o get novamente, ou o put. Se na primeira vez que o contains 
e chamado a palavra e guardada, nao so a palavra, mas o seu node, pelo seguinte motivo: se ela 
ja esta no dicionario tudo que queremos e aumentar seu valor tendo um "ponteiro" para sua 
posicao, isso se torna trivial.

Guarda-se um cachekey e cacheval para podermos tomar vantagem de x.equals(null) == false - ao
passo que x.equals(node.key), com node nao inicializado retorna um null pointer exception - pois
o node cache sera null ate a primeira insercao, e para evitar de checar se cache != null sempre.

Conclui-se que o cache e uma ferramente muito boa, pois acelera ainda mais um processo ja extre-
mamente rapido custando muito pouco para faze-lo.


