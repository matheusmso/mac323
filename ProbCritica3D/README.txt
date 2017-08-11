Nome: Matheus de Mello Santos Oliveira
NUSP: 8642821


O programa acha o p critico executando uma especie de busca binaria,
se o f(x) para dado x for maior que .5 mais o erro entao busca a esquerda
deste x e se for menor que .5 menos o erro busque a direita. O erro foi estipulado
para 0.001 que na escala representa um erro de .1%, mas precebe-se resultados
muito similares para erros de 2.5%.

O numero de testes M afeta linearmente o tempo, mas o lado da matriz N afeta cubicamente.
Porem os resultados obtidos para N > 40 (expandindo a memoria do java vm) sao extremamente
parecidos com aqueles obtidos para N = 20 (com M fixado em 100).


