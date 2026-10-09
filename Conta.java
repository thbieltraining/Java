/*
 * Classe que representa uma conta bancária em Java.
 */
// Mas o que é uma classe? Uma classe é um modelo ou uma estrutura que define as características e comportamentos de um objeto.
// No exemplo acima, a classe "Conta" define os atributos "titular" e "saldo", bem como o método "depositar" que permite adicionar dinheiro à conta. 
// A classe serve como um molde para criar objetos específicos, cada um com seus próprios valores para os atributos definidos na classe.

public class Conta {
    String titular;     // atributo
    double saldo;       // atributo

  public double getSaldo() {           // getter: lê
        return saldo;
    }

    public void setTitular(String titular) {   // setter: altera
        this.titular = titular;
    }

    public void depositar(double valor) {
        if (valor > 0) {                 // controle: regra de negócio
            saldo = saldo + valor;
        }
    }
}

