// Vamos falar de conjuntos em Java.
// Um conjunto é uma coleção que não permite elementos duplicados.
// Set é a interface que define o comportamento de um conjunto.

// A interface Set não define uma ordem específica para os elementos.
// Algumas implementações, como LinkedHashSet e TreeSet, têm regras de ordenação próprias.
// Neste exemplo, usamos HashSet, que não garante uma ordem específica.

// Um Set é útil quando queremos manter elementos únicos,
// verificar se um elemento existe ou remover duplicados de uma lista.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrincipalConjuntos {
    public static void main(String[] args) {

        // SET (conjunto): guarda elementos sem repetir valores iguais.
        // HashSet é uma implementação de Set e não garante a ordem dos elementos.
        Set<String> cargos = new HashSet<>();

        // add adiciona o elemento e retorna true quando ele ainda não existia.
        System.out.println("Primeiro Diretor: " + cargos.add("Diretor"));
        cargos.add("Gerente");
        cargos.add("Presidente");

        // Como "Diretor" já está no conjunto, ele não é adicionado novamente.
        // Nesse caso, add retorna false.
        System.out.println("Segundo Diretor: " + cargos.add("Diretor"));

        // size informa quantos elementos únicos existem no conjunto.
        System.out.println("Tamanho: " + cargos.size());

        // contains verifica se o conjunto contém o elemento informado.
        System.out.println("Tem Gerente? " + cargos.contains("Gerente"));

        // LIST permite manter uma sequência de contas, inclusive com titulares repetidos.
        List<Conta> contas = new ArrayList<>();
        contas.add(criarPoupanca("Bia", 200));
        contas.add(criarConta("Carlos", 300));
        contas.add(criarPoupanca("Bia", 80));

        // Criamos um conjunto de nomes para guardar cada titular apenas uma vez.
        Set<String> titulares = new HashSet<>();

        // Percorre as contas e adiciona o titular ao conjunto.
        // O segundo "Bia" não cria um elemento repetido.
        for (Conta conta : contas) {
            titulares.add(conta.getTitular());
        }

        // A ordem dos nomes exibidos pode variar, pois titulares é um HashSet.
        System.out.println("Titulares únicos: " + titulares);
        System.out.println(contas.size() + " contas, " + titulares.size() + " titulares");
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
