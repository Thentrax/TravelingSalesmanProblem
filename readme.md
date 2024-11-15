# Solução do Problema do Caixeiro Viajante

Para solucionar o problema utilizamos de duas abordagens diferentes:

## Solução com Força Bruta

Essa abordagem calcula todas as permutações possíveis das cidades e verifica a distância total para cada uma delas.
A solução com a menor distância é considerada a melhor.

### Complexidade do Algotítmo: O(n!) onde 𝑛 n é o número de cidades.

Isso acontece pois todas os valores dentro da matriz são percorridos e considerados.
<br>
Uma solução eficiente porém nada escalável em comparação a de Held-Karp.

## Solução por Programação Dinâmica (Held-Karp)

A abordagem de Held-Karp utiliza programação dinâmica para reduzir a redundância do cálculo.
<br>
Em vez de explorar todas as permutações, mantém um histórico dos estados já calculados e reutiliza resultados.

### Complexidade do Algotítmo: O(2^n × n²), onde n é o número de cidades.

Cada estado na programação dinâmica é representado por:
* Um subconjunto de cidades já visitadas.
* A cidade atual onde o caixeiro viajante está.
<br>
O número total de subconjuntos possíveis para n cidades é 2^n x 2n (todas as combinações de n elementos,
incluindo o conjunto vazio).

Uma solução mais escalável que a de Força Bruta.