# Jantar dos Filósofos
O problema "Jantar dos Filósofos" é um clássico da programação concorrente e envolve o uso de recursos compartilhados. Foi proposto pela disciplina Programação Concorrente, cursada no terceiro semestre da Universidade Estadual do Sudoeste da Bahia (UESB). Desenvolvido na linguagem Java.

# Sobre o projeto
O projeto envolve resolver o problema de recurso compartilhado no contexto do jantar dos filósofos. Na interface gráfica são dispostos 5 talheres que deverão ser utilizados para os 5 filósofos comerem. Só é possível que dois filósofos comam ao mesmo tempo (5%2 = 2) e estes não podem ser adjacentes, já que um utilizará o mesmo garfo que o outro, ocasionalmente. Para isso, foi implementada uma solução clássica, bem como uma GUI (interface gráfica) para melhor compreensão do usuário.

# Programa em execução
![Layout](https://github.com/bbering/jantarDosFilosofos/blob/main/assets/executionGif20FPS.gif)

# Como executar o projeto
## Pré-Requisito: Java 8 (inclui a biblioteca javaFX nativa)

```
Clonar repositorio
git clone git@github.com:bbering/jantarDosFilosofos.git

Executar na pasta raiz do programa (onde se encontra o .java principal) o seguinte comando:

javac Principal.java (Compila o projeto)

java Principal (Executa o projeto)

E pronto! O projeto foi executado.

```

