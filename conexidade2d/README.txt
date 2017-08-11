Nome: Matheus de Mello Santos Oliveira
NUSP: 8642821

O problema foi implementado como indicado em
Grid.java quebrando o quadrado unitario em
pedacos e calculando as distancias somente entre pontos
que realmente em chance de estar a d de distancia ou menos um
do outro, ou seja, pontos que estao no proprio quadrado
de lado d e nos seus 8 adjacentes.

Isto reduz o problema muito pois agora nao precisamos
testar cada ponto com todos os outros, somente com aqueles
que tem chance de estar a d de distancia ou menos.

No pior caso, em que todos os pontos entram no mesmo quadrado 
de lado d, temos um algoritmo quadratico, porem, no caso medio teremos
algo bem mais rapido.

