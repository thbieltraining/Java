/**
 * Classe que representa uma conta bancária.
 *
 * Uma classe é um modelo que define as características (atributos)
 * e os comportamentos (métodos) de um conjunto de objetos do mesmo tipo.
 * Cada objeto criado a partir dela tem seus próprios valores de atributos.
 */
public class Conta {
    private String titular;     // atributo
    private double saldo;       // atributo

    public String getTitular() {          // getter: lê
        return titular;
    }

    public void setTitular(String titular) {   // setter: altera
        this.titular = titular;
    }

    public double getSaldo() {            // getter: lê
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {                  // controle: regra de negócio
            saldo = saldo + valor;
        }
    }
}

/* Objetos
 * Para criar um objeto a partir da classe, usamos "new" seguido do nome
 * da classe e parênteses:
 *
 *     Conta minhaConta = new Conta();
 *
 * Vamos ver isso melhor no próximo arquivo (Principal.java).
 */