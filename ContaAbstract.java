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

// Em um conceito amplo, uma classe abstrata é como um esqueleto que define a estrutura e o comportamento esperado de suas subclasses.

// No nosso modelo de conta, um método abstrato tem só a assinatura, sem corpo, e termina em ;.
// calcularRendimento() diz que cada conta concreta deve calcular um rendimento, mas cada subclasse define como.
// Toda subclasse concreta precisa implementar os métodos abstratos da classe mãe.
// Se uma subclasse concreta de ContaAbstract não implementar calcularRendimento(), o código não compila.
