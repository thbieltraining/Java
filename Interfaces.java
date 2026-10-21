// Vamos falar de interfaces.
// Uma interface é um contrato que define um conjunto de métodos que uma classe deve implementar. 
// Diferente da herança, onde uma classe pode herdar de apenas uma superclasse, uma classe pode implementar várias interfaces. 
// Isso permite que diferentes classes compartilhem a mesma interface, mesmo que não estejam relacionadas por herança.

interface Calculavel {
	double calcularTarifa();
}

class ContaComTarifa extends Conta implements Calculavel {
	@Override
	public double calcularTarifa() {
		return getSaldo() * 0.01;
	}
}

class Interfaces {
	public static void main(String[] args) {
		ContaComTarifa conta = new ContaComTarifa();
		conta.setTitular("Ana");
		conta.depositar(1000);

		mostrarTarifa(conta);
	}

	private static void mostrarTarifa(Calculavel calculavel) {
		System.out.println("Tarifa da conta: " + calculavel.calcularTarifa());
	}
}

