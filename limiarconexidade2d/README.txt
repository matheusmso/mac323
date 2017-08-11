ome: Matheus de Mello Santos Oliveira
NUSP: 8642821

Podemos perceber que o numero de testes T, qdo acima de 100 pouco interfere
na qualidade dos resultados, por este motivo analisaremos a influencia de d
e n nos resultados.

Podemos perceber que conforme d diminui, n cresce:

LC2D 0.1 100 100 => n = 290. 1/d2 = 100 n/logn = 117.8
LC2D 0.05 100 100 => n = 1253 1/d2 = 400 n/logn = 404
LC2D 0.02 100 100 => n = 9640 1/d2 = 2500 n/logn = 2419
LC2D 0.01 100 100 => n = 39915 1/d2 = 10000 n/logn = 8675

Com esta tabela podemos ver tambem que a maior convergencia entre 1/d2 e n/logn
acontece para d proximo de 0.05.


