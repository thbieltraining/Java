// Vamos falar sobre Erros e Exceções em Java.
//  Em Java, erros e exceções são mecanismos que permitem lidar com situações inesperadas que podem ocorrer durante a execução de um programa.

// Erros são problemas graves que geralmente não podem ser recuperados, como falhas de hardware ou falta de memória. 
// Eles são representados pela classe Error e suas subclasses.

// Exceções são problemas que podem ser tratados e recuperados durante a execução do programa. 
// Elas são representadas pela classe Exception e suas subclasses.

// Em Java, as exceções podem ser divididas em duas categorias principais: exceções verificadas (checked exceptions) e exceções não verificadas (unchecked exceptions).

// Exceções verificadas são aquelas que o compilador exige que sejam tratadas ou declaradas no método.
// Elas são subclasses de Exception, mas não de RuntimeException. Exemplos incluem IOException e SQLException.

// Exceções não verificadas são aquelas que o compilador não exige que sejam tratadas ou declaradas.
// Elas são subclasses de RuntimeException. Exemplos incluem NullPointerException e ArrayIndexOutOfBoundsException.  

// Para lidar com exceções em Java, podemos usar os blocos try-catch-finally.
// O bloco try contém o código que pode gerar uma exceção.
// O bloco catch é usado para capturar e tratar a exceção lançada no bloco try.
// O bloco finally é opcional e contém código que será executado independentemente de uma exceção ter sido lançada ou não, geralmente usado para liberar recursos.  

// Aqui está um exemplo simples de como lidar com exceções em Java usando nosso contexto de contas de banco:



public class PrincipalExcecoes {
    // Ponto de entrada: a execução do programa começa aqui.
    public static void main(String[] args) {
        // Cria uma conta bancária. O saldo começa em zero.
        Conta conta = new Conta();
        // Define "Ana" como titular da conta.
        conta.setTitular("Ana");

        // Define o valor que tentaremos depositar. Como é negativo, será inválido.
        double valorDeposito = -30;

        // Inicia um bloco que pode lançar uma exceção.
        try {
            // Verifica se o depósito não é positivo.
            if (valorDeposito <= 0) {
                // Interrompe o fluxo normal e lança uma exceção com uma mensagem.
                throw new IllegalArgumentException(
                        "O valor do depósito deve ser maior que zero.");
            }

            // Só é executado se o valor for válido; adiciona o valor ao saldo.
            conta.depositar(valorDeposito);
            // Informa que o depósito foi realizado.
            System.out.println("Depósito realizado com sucesso.");
        // Captura a exceção IllegalArgumentException lançada no bloco try.
        } catch (IllegalArgumentException e) {
            // Exibe uma mensagem de erro e o motivo informado na exceção.
            System.out.println("Não foi possível realizar o depósito: "
                    + e.getMessage());
        // Executa ao final, tanto se houver exceção quanto se não houver.
        } finally {
            // Informa que a tentativa de depósito terminou.
            System.out.println("Operação de depósito finalizada.");
        }

        // Exibe o titular e o saldo final; neste exemplo, o saldo continua zero.
        System.out.println(conta.getTitular() + ": R$ " + conta.getSaldo());
    }
}
// Exemplo de tratamento de exceções usando o contexto das contas bancárias.