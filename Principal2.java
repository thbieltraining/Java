// Vamos falar de Herança em  Java? 
// Herança é um dos pilares da programação orientada a objetos (POO) e permite que uma classe (subclasse) herde atributos e métodos de outra classe (superclasse). 
// Isso promove a reutilização de código e a criação de hierarquias de classes. 

public class Principal2 {
    public static void main(String[] args) {

        Conta c1 = new Conta();          // cria o primeiro objeto
        c1.setTitular("Ana");
        c1.depositar(100);
        c1.depositar(50);
        c1.depositar(-30);               // recusado pela regra (valor > 0)

        Conta c2 = new Conta();          // outro objeto, independente
        c2.setTitular("Carlos");
        c2.depositar(200);

        System.out.println(c1.getTitular() + ": " + c1.getSaldo());
        System.out.println(c2.getTitular() + ": " + c2.getSaldo());

        ContaPoupanca poupanca = new ContaPoupanca(0.05);
        poupanca.setTitular("Marina");
        poupanca.depositar(1000);
        poupanca.aplicarRendimento();

        System.out.println(poupanca.getTitular() + ": " + poupanca.getSaldo());
    }
}

// A classe ContaPoupanca herda da classe Conta, o que significa que ela possui todos os atributos e métodos da classe Conta, além de seus próprios atributos e métodos específicos.