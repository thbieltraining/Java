public class Principal {
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
    }
}

// O que nós acabamos de fazer foi criar dois objetos independentes da classe Conta, cada um com seus próprios valores de atributos. 
// O objeto c1 tem o titular "Ana" e um saldo de 150, enquanto o objeto c2 tem o titular "Carlos" e um saldo de 200.