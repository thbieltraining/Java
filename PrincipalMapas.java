// Vamos falar de Maps em Java.
// Um Map é uma coleção que guarda pares de chave e valor. 
// A chave é única, ou seja, não pode haver duas entradas com a mesma chave. 
// O valor pode ser qualquer objeto, incluindo outros Maps.

import java.util.HashMap;
import java.util.Map;

public class PrincipalMapas {
    public static void main(String[] args) {

        // MAP: guarda pares de chave e valor.
        // Neste caso, a chave Integer identifica a conta e o valor é um objeto Conta.
        // HashMap é uma implementação de Map; ela não garante a ordem dos elementos.
        Map<Integer, Conta> contas = new HashMap<>();

        // put adiciona uma conta associada à chave informada.
        contas.put(1001, criarPoupanca("Bia", 200));
        contas.put(1002, criarConta("Carlos", 300));

        // get busca o valor associado à chave 1002.
        Conta conta = contas.get(1002);
        System.out.println(conta.getTitular() + ": " + conta.getSaldo());

        // Uma chave é única: ao usar 1001 novamente, o valor anterior é substituído.
        contas.put(1001, criarPoupanca("Ana", 50));
        System.out.println(contas.get(1001).getTitular());
        System.out.println("Tamanho: " + contas.size()); // size informa quantos pares existem

        // remove apaga o par associado à chave 1002.
        contas.remove(1002);
        System.out.println("Tamanho: " + contas.size());

        // Se a chave não existir, get retorna null.
        System.out.println(contas.get(9999));
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
