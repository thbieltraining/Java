// Parte 2 de Generics 
// Chegamos na caixa
// Vamos criar uma classe genérica chamada Caixa que pode armazenar um objeto de qualquer tipo.

// Mais vamos usar

public class Caixa<T> {
    private T conteudo;

    public void set(T conteudo) {
        this.conteudo = conteudo;
    }

    public T get() {
        return conteudo;
    }
}