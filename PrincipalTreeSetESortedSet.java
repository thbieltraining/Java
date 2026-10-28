// Vamos falar de TreeSet e SortedSet em Java!
// SortedSet é a interface que representa um conjunto ordenado.
// TreeSet é uma implementação de SortedSet que mantém os elementos ordenados.

// Como o conjunto não permite elementos duplicados, cada titular aparece uma vez.
// Neste exemplo, as Strings são organizadas em ordem alfabética.

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PrincipalTreeSetESortedSet {
    public static void main(String[] args) {

        // LIST guarda as contas na sequência em que foram adicionadas.
        // A lista pode ter contas com o mesmo titular.
        List<Conta> contas = new ArrayList<>();
        contas.add(criarPoupanca("Carlos", 300));
        contas.add(criarConta("Ana", 50));
        contas.add(criarPoupanca("Bia", 200));
        contas.add(criarConta("Dani", 120));
        contas.add(criarPoupanca("Bia", 80)); // titular repetido

        // SortedSet define operações de um conjunto que mantém os elementos ordenados.
        // TreeSet implementa essa interface e ordena as Strings alfabeticamente.
        SortedSet<String> titulares = new TreeSet<>();

        // Percorre as contas e adiciona cada titular ao conjunto ordenado.
        // O segundo "Bia" não é adicionado porque o conjunto não aceita duplicados.
        for (Conta conta : contas) {
            titulares.add(conta.getTitular());
        }

        // Exibe os titulares únicos em ordem alfabética.
        System.out.println("Ordenado:        " + titulares);

        // first retorna o primeiro elemento na ordem do conjunto.
        System.out.println("first():         " + titulares.first());

        // last retorna o último elemento na ordem do conjunto.
        System.out.println("last():          " + titulares.last());

        // headSet retorna os elementos anteriores a "Carlos", sem incluir "Carlos".
        System.out.println("headSet(Carlos): " + titulares.headSet("Carlos"));

        // tailSet retorna "Carlos" e os elementos que vêm depois dele.
        System.out.println("tailSet(Carlos): " + titulares.tailSet("Carlos"));
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
