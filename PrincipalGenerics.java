import java.util.ArrayList;
import java.util.List;

public class PrincipalGenerics {
    public static void main(String[] args) {
        // Generics está aqui: List<Conta>
        // A lista foi declarada para aceitar somente objetos do tipo Conta
        // (ou subclasses de Conta, como ContaPoupanca).
        List<Conta> contas = new ArrayList<>();

        // Aqui o ArrayList também usa generics via <>.
        // Ele sabe que a lista vai guardar objetos do tipo Conta.
        contas.add(new ContaPoupanca(0.05));
        contas.add(new ContaPoupanca(0.08));

        // Outro ponto do generics: o laço foreach já sabe que cada item é um Conta.
        // Então não precisamos fazer cast manual.
        for (Conta c : contas) {
            c.depositar(100);
            System.out.println(c.getTitular() + " - saldo: " + c.getSaldo());
        }

        // Por que usar generics?
        // 1. Segurança em tempo de compilação: evita adicionar tipos errados na lista.
        // 2. Menos casts: o compilador já entende o tipo correto.
        // 3. Código mais limpo e mais legível.
        // 4. Evita erros de execução (ClassCastException) que acontecem quando
        //    a lista aceita qualquer objeto e você precisa converter manualmente.
        //
        // Exemplo do problema sem generics:
        // List lista = new ArrayList();
        // lista.add("texto");
        // Conta conta = (Conta) lista.get(0); // erro em runtime
        //
        // Com generics:
        // List<Conta> contas = new ArrayList<>();
        // contas.add("texto"); // erro de compilação
    }
}