import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrincipalColecoes {
    public static void main(String[] args) {

        // QUEUE (fila): segue a regra FIFO, do inglês "First In, First Out".
        // Isso significa que o primeiro elemento que entra é o primeiro a sair,
        // como uma fila de atendimento.
        // Queue define as operações de fila; LinkedList é a classe usada para armazená-la.
        Queue<Conta> fila = new LinkedList<>();
        fila.add(criarPoupanca("Bia", 200));       // adiciona um elemento à fila
        fila.offer(criarConta("Carlos", 300));     // outra forma de adicionar
        fila.offer(criarPoupanca("Ana", 50));

        // peek consulta o primeiro elemento sem removê-lo.
        System.out.println("Primeiro da fila: " + fila.peek().getTitular());
        System.out.println("Tamanho: " + fila.size());

        // poll remove e devolve o primeiro elemento; retorna null se a fila estiver vazia.
        Conta atendida = fila.poll();
        System.out.println("Atendida: " + atendida.getTitular());
        System.out.println("Tamanho: " + fila.size());

        // DEQUE: permite inserir, consultar e remover elementos nas duas pontas.
        // Pode ser usado como fila (FIFO) ou como pilha (LIFO).
        Deque<Conta> deque = new LinkedList<>();
        deque.addLast(criarPoupanca("Bia", 200));   // insere no final
        deque.addLast(criarConta("Carlos", 300));
        deque.addFirst(criarPoupanca("Ana", 50));  // insere no início

        // peekFirst e peekLast consultam as pontas sem remover os elementos.
        System.out.println("Primeiro: " + deque.peekFirst().getTitular());
        System.out.println("Último: " + deque.peekLast().getTitular());
        deque.pollLast(); // remove e devolve o elemento do final
        System.out.println("Tamanho do deque: " + deque.size());

        // LIST e ARRAYLIST: lista mantém os elementos em uma sequência.
        // O tipo Conta permite guardar Conta e também subclasses, como ContaPoupanca.
        List<Conta> contas = new ArrayList<>();
        contas.add(criarPoupanca("Bia", 200));
        contas.add(criarConta("Carlos", 300));
        contas.add(criarPoupanca("Ana", 50));

        // FOR-EACH percorre os elementos da lista, um por vez.
        // Aqui apenas lemos e exibimos os dados; a lista não é modificada.
        for (Conta conta : contas) {
            System.out.println(conta.getTitular() + ": " + conta.getSaldo());
        }

        // ITERATOR também percorre a lista, permitindo remover o elemento atual
        // com it.remove() sem modificar a lista diretamente durante a travessia.
        Iterator<Conta> it = contas.iterator();
        while (it.hasNext()) { // verifica se ainda há um próximo elemento
            Conta conta = it.next(); // avança e devolve o próximo elemento
            if (conta.getSaldo() < 100) {
                it.remove(); // remove a conta com saldo menor que 100
            }
        }
        System.out.println("Depois do Iterator: " + contas.size());
    }

    // Cria uma conta comum usando os métodos disponíveis na classe Conta.
    private static Conta criarConta(String titular, double saldo) {
        Conta conta = new Conta();
        conta.setTitular(titular);
        conta.depositar(saldo);
        return conta;
    }

    // Cria uma poupança; a taxa é necessária pelo construtor existente no projeto.
    private static ContaPoupanca criarPoupanca(String titular, double saldo) {
        ContaPoupanca poupanca = new ContaPoupanca(0.05);
        poupanca.setTitular(titular);
        poupanca.depositar(saldo);
        return poupanca;
    }
}

// Acabamos de aplicar os conceitos de herança, polimorfismo, interfaces, generics e coleções em um mesmo projeto