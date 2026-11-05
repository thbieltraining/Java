// Em Java, exceções são situações inesperadas que podem ser tratadas pelo programa.
// A classe Error representa problemas graves da JVM ou do ambiente.
// Dividir um inteiro por zero lança ArithmeticException, que é uma exceção,
// não um Error. O try envolve a operação, o catch trata a exceção e o finally
// executa ao final, tenha ocorrido uma exceção ou não.
//
// Neste exemplo, o titular da conta faleceu e os R$ 1.000 serão divididos
// igualmente entre os dependentes. Por um erro nos dados, não há dependentes.
public class PrincipalErros {
    public static void main(String[] args) {
        // Cria a conta, define o titular e deposita o saldo que será dividido.
        Conta conta = new Conta();
        conta.setTitular("João");
        conta.depositar(1000);

        // A quantidade de dependentes está incorreta: deveria ser maior que zero.
        int quantidadeDependentes = 0;

        try {
            // Converte o saldo para inteiro para representar valores em reais.
            int saldoEmReais = (int) conta.getSaldo();

            // A divisão por zero lança ArithmeticException.
            // O restante do try não é executado depois que a exceção é lançada.
            int valorPorDependente = saldoEmReais / quantidadeDependentes;

            // Só seria exibido se a divisão fosse concluída sem exceção.
            System.out.println("Cada dependente receberia R$ " + valorPorDependente);
        } catch (ArithmeticException e) {
            // Trata a divisão inválida e informa o motivo.
            System.out.println("Não foi possível dividir o saldo: "
                    + "a quantidade de dependentes não pode ser zero.");
        } finally {
            // Executa tanto quando a divisão funciona quanto quando ocorre exceção.
            System.out.println("Cálculo da divisão finalizado.");
        }

        // A tentativa de divisão não altera o saldo guardado na conta.
        System.out.println(conta.getTitular() + ": R$ " + conta.getSaldo());
    }
}
