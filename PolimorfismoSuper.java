class PolimorfismoSuper extends Conta {
	@Override
	public void depositar(double valor) {
		System.out.println("PolimorfismoSuper.depositar: antes de chamar super");
		super.depositar(valor);
		System.out.println("PolimorfismoSuper.depositar: depois de chamar super. Saldo: " + getSaldo());
	}

	public static void main(String[] args) {
		Conta conta = new PolimorfismoSuper();
		conta.setTitular("Ana");
		conta.depositar(300);
	}
}
// Aqui estamos criando uma subclasse chamada PolimorfismoSuper que herda da classe Conta.
// Podemos notar também que o método depositar foi sobrescrito na subclasse PolimorfismoSuper, adicionando um comportamento extra antes e depois de chamar o método depositar da superclasse Conta.