// Bora falar de uma classe abstrata? 
// Bora! Uma classe abstrata é uma classe que não pode ser instanciada diretamente, ou seja, você não pode criar objetos dela. 
// Ela serve como um modelo para outras classes que irão herdar suas características e implementar seus métodos abstratos.

public abstract class ContaAbstract {
	private String titular;
	private double saldo;

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
		}
	}

	public abstract double calcularRendimento();
}

