// Vamos falar de threads em Java!
// Threads são unidades de execução que permitem que um programa realize múltiplas tarefas ao mesmo tempo. 
// Em Java, podemos criar threads de duas maneiras principais: estendendo a classe `Thread` ou implementando a interface `Runnable`.

// Mas qual é a diferença entre essas duas abordagens?
// Vamos explorar isso com um exemplo prático.

import java.math.BigDecimal;

/**
 * Demonstra duas formas de preparar tarefas para execução em threads:
 * uma classe que estende Thread e outra que implementa Runnable.
 */
public class PrincipalThreads {
    public static void main(String[] args) throws InterruptedException {
        // Cada pessoa tem sua própria conta. Assim, uma thread não altera
        // o saldo da conta da outra pessoa.
        Conta contaBia = new Conta();
        Conta contaSilvio = new Conta();

        // Primeira forma: DepositoBia herda de Thread e define o trabalho
        // que será executado sobrescrevendo o método run().
        Thread threadBia = new DepositoBia(contaBia);

        // Segunda forma: DepositoSilvio implementa Runnable, que representa
        // uma tarefa. Passamos essa tarefa para um objeto Thread executar.
        Thread threadSilvio = new Thread(new DepositoSilvio(contaSilvio), "Thread-Silvio");

        // start() inicia cada execução em uma nova thread. Não chamamos run()
        // diretamente: chamar run() como um método comum não cria outra thread.
        threadBia.start();
        threadSilvio.start();

        // join() faz a thread principal esperar pelas duas tarefas.
        // Assim, só mostramos os saldos finais depois de todos os depósitos.
        threadBia.join();
        threadSilvio.join();

        System.out.println();
        System.out.println("Saldo final da Bia: R$ " + Conta.formatar(contaBia.getSaldoCentavos()));
        System.out.println("Saldo final do Silvio: R$ " + Conta.formatar(contaSilvio.getSaldoCentavos()));
    }

    /**
     * Conta simples para o exemplo. O saldo é guardado em centavos para
     * evitar imprecisões de ponto flutuante em valores monetários.
     */
    private static class Conta {
        private long saldoCentavos;

        // synchronized protege a alteração do saldo caso, no futuro,
        // mais de uma thread passe a depositar na mesma conta.
        private synchronized void depositar(long valorCentavos) {
            if (valorCentavos <= 0) {
                throw new IllegalArgumentException("O depósito deve ser maior que zero.");
            }
            saldoCentavos += valorCentavos;
        }

        private synchronized long getSaldoCentavos() {
            return saldoCentavos;
        }

        private static String formatar(long valorCentavos) {
            return BigDecimal.valueOf(valorCentavos, 2).toPlainString();
        }
    }

    /**
     * Nesta abordagem, a própria classe da tarefa é uma Thread.
     * Estender Thread é útil para comparar as abordagens, mas ocupa a
     * possibilidade de estender outra classe Java.
     */
    private static class DepositoBia extends Thread {
        private final Conta conta;

        private DepositoBia(Conta conta) {
            super("Thread-Bia");
            this.conta = conta;
        }

        @Override
        public void run() {
            realizarDepositos(conta, 1000, 3);
        }
    }

    /**
     * Runnable representa o trabalho, separado do objeto Thread que o executa.
     * Essa abordagem costuma ser mais flexível e é comum em programas Java.
     */
    private static class DepositoSilvio implements Runnable {
        private final Conta conta;

        private DepositoSilvio(Conta conta) {
            this.conta = conta;
        }

        @Override
        public void run() {
            realizarDepositos(conta, 2500, 2);
        }
    }

    // O trabalho das duas tarefas é semelhante: fazer vários depósitos
    // e mostrar qual thread está realizando cada operação.
    private static void realizarDepositos(Conta conta, long valorCentavos, int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            conta.depositar(valorCentavos);
            System.out.println(
                    Thread.currentThread().getName()
                            + " fez o depósito " + i
                            + " de R$ " + Conta.formatar(valorCentavos));
        }
    }
}
// Então Threads permitem que diferentes partes de um programa sejam executadas simultaneamente, melhorando a eficiência e a capacidade de resposta.
// E Runnable é uma maneira de definir o trabalho a ser feito, enquanto Thread é a própria execução.
// A escolha entre estender `Thread` ou implementar `Runnable` depende das necessidades do seu design, mas implementar `Runnable` é geralmente mais flexível e recomendado.