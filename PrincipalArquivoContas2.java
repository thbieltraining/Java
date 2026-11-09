import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Lê as contas de conta.txt, transforma as linhas em objetos e grava esses
 * objetos em formato binário no arquivo contas.ser.
 */
public class PrincipalArquivoContas2 {
    public static void main(String[] args) throws IOException {
        Path arquivoEntrada = Path.of("conta.txt");
        Path arquivoSaida = Path.of("contas.ser");

        // Files.lines cria uma Stream<String>: um fluxo de linhas do arquivo.
        // A Stream API permite compor operações como filter e map para processar
        // os dados em etapas. A stream é fechada automaticamente pelo try.
        List<String> linhasContas;
        try (Stream<String> linhas = Files.lines(arquivoEntrada, StandardCharsets.UTF_8)) {
            linhasContas = linhas
                    .filter(linha -> !linha.isBlank())
                    .toList();
        }

        // IntStream fornece as posições das linhas. A posição + 1001 vira a
        // chave numérica de cada conta, sem precisar alterar estado dentro do pipeline.
        List<ContaSerializavel> contas = IntStream.range(0, linhasContas.size())
                .mapToObj(indice -> converterLinha(linhasContas.get(indice), indice + 1001))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        // ObjectOutputStream transforma objetos Java em bytes e os grava no arquivo.
        // É uma stream de saída binária, diferente da Stream API usada acima
        // para filtrar e transformar as linhas.
        try (ObjectOutputStream escritor = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(arquivoSaida)))) {
            escritor.writeObject(contas);
        }

        System.out.println("Contas gravadas em " + arquivoSaida);
    }

    // Converte uma linha como "Silvio, 20 anos, R$ 4500 reais, poupança"
    // em um objeto com campos tipados, pronto para ser serializado.
    private static ContaSerializavel converterLinha(String linha, int chave) {
        String[] campos = linha.split(",", -1);
        if (campos.length != 4) {
            throw new IllegalArgumentException(
                    "Cada linha de conta.txt deve ter titular, idade, saldo e tipo de conta: " + linha);
        }

        String titular = campos[0].trim();
        int idade = Integer.parseInt(campos[1].replaceAll("\\D", ""));
        String saldoTexto = campos[2]
                .replace("R$", "")
                .replaceAll("(?i)\\s*reais?\\s*", "")
                .trim();
        BigDecimal saldo = new BigDecimal(saldoTexto).setScale(2, RoundingMode.HALF_UP);
        String tipoConta = campos[3].trim();

        return new ContaSerializavel(chave, titular, idade, saldo, tipoConta);
    }

    /**
     * Serializable permite que ObjectOutputStream grave o estado deste objeto.
     * serialVersionUID identifica a versão da estrutura da classe usada no arquivo.
     */
    private static class ContaSerializavel implements Serializable {
        private static final long serialVersionUID = 1L;

        private final int chave;
        private final String titular;
        private final int idade;
        private final BigDecimal saldo;
        private final String tipoConta;

        private ContaSerializavel(int chave, String titular, int idade, BigDecimal saldo, String tipoConta) {
            this.chave = chave;
            this.titular = titular;
            this.idade = idade;
            this.saldo = saldo;
            this.tipoConta = tipoConta;
        }
    }
}
// Basicamente, o código acima lê linhas de um arquivo de texto, processa cada linha para extrair informações sobre contas bancárias, cria objetos representando essas contas e, em seguida, grava esses objetos em um arquivo binário usando serialização. Ele utiliza a Stream API do Java para manipular os dados de forma funcional e eficiente.

// Parece um arquivo chave para uma portabilidade de dados, permitindo que as contas sejam armazenadas em um formato binário que pode ser lido posteriormente por outros programas Java.
