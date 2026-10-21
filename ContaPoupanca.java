public class ContaPoupanca extends Conta {
    private final double taxaRendimento;

    public ContaPoupanca(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public double calcularRendimento() {
        if (taxaRendimento > 0) {
            return getSaldo() * taxaRendimento;
        }
        return 0;
    }

    public void aplicarRendimento() {
        depositar(calcularRendimento());
    }
}

// Aqui estamos criando uma subclasse chamada ContaPoupanca que herda da classe Conta.
//  A subclasse possui um atributo adicional chamado taxaRendimento, que representa a taxa de rendimento da conta poupança.
//  O método aplicarRendimento calcula o rendimento com base no saldo atual e na taxa de rendimento, e adiciona esse valor ao saldo da conta.