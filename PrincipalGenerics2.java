public class PrincipalGenerics2 {
    public static void main(String[] args) {

        // O tipo entre <> define o que cada caixa pode armazenar.
        Caixa<String> caixaTexto = new Caixa<>();
        caixaTexto.set("Ana");
        String nome = caixaTexto.get(); // sem cast

        Caixa<Integer> caixaNumero = new Caixa<>();
        caixaNumero.set(10);
        int numero = caixaNumero.get();

        // ContaPoupanca é uma subclasse de Conta, então pode ser guardada aqui.
        Caixa<Conta> caixaConta = new Caixa<>();
        ContaPoupanca poupanca = new ContaPoupanca(0.05);
        poupanca.setTitular("Bia");
        poupanca.depositar(200);
        caixaConta.set(poupanca);

        Conta c = caixaConta.get();
        System.out.println(c.getTitular() + " - saldo: " + c.getSaldo());

        System.out.println(nome + " / " + numero);
    }
}