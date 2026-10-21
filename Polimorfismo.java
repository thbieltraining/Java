// E agora sobre Polimorfismo
// Polimorfismo é um conceito que permite que objetos de diferentes classes sejam tratados como objetos de uma classe comum.
// Isso é possível porque as classes podem compartilhar uma interface comum ou herdar de uma classe base.

class Polimorfismo {
	public static void main(String[] args) {
		Conta contaComum = new Conta();
		contaComum.setTitular("Ana");
		contaComum.depositar(1000);

		Conta contaPoupanca = new ContaPoupanca(0.05);
		contaPoupanca.setTitular("Carlos");
		contaPoupanca.depositar(1000);

		Conta[] contas = { contaComum, contaPoupanca };

		for (Conta conta : contas) {
			System.out.println(conta.getTitular() + " receberia de rendimento: "
					+ conta.calcularRendimento());
		}
	}
}

