import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

/**
 * Lê os dados de conta.txt por um fluxo de entrada de caracteres,
 * associa uma chave numérica a cada conta e grava o resultado em saida.txt
 * por um fluxo de saída de caracteres.
 *
 * String é o tipo usado em Java para representar textos, como o nome
 * do titular e o tipo da conta. Cada linha lida do arquivo é uma String.
 */
public class PrincipalArquivoContas {
    // As operações de leitura e gravação podem falhar (por exemplo, se o arquivo
    // não existir). throws IOException informa que essa falha pode ser repassada
    // ao Java que iniciou o programa, em vez de ser ignorada silenciosamente.
    public static void main(String[] args) throws IOException {
        // Map guarda pares de chave e valor. Aqui, a chave Integer identifica
        // a conta e a String contém os dados dessa conta separados por vírgulas.
        // TreeMap mantém as chaves em ordem numérica para a saída ficar organizada.
        Map<Integer, String> contas = new TreeMap<>();

        // Cada conta receberá uma chave numérica diferente, começando em 1001.
        int chave = 1001;

        // BufferedReader é um fluxo de entrada de caracteres: os dados saem
        // do arquivo e chegam ao programa. O buffer lê os caracteres em blocos,
        // e readLine() entrega uma linha por vez como uma String.
        // UTF-8 também representa corretamente caracteres como o "ç".
        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of("conta.txt"), StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                // Ignora linhas vazias para que elas não sejam tratadas como contas.
                if (linha.isBlank()) {
                    continue;
                }

                // split(",") divide o texto nos pontos em que há vírgula e devolve
                // um vetor de Strings, um para cada campo da conta.
                String[] campos = linha.split(",");
                if (campos.length != 4) {
                    throw new IllegalArgumentException(
                            "Cada linha de conta.txt deve ter titular, idade, saldo e tipo de conta: " + linha);
                }

                // Cada posição do vetor corresponde a um campo: titular, idade,
                // saldo e tipo de conta. trim() remove espaços no começo e no fim.
                String titular = campos[0].trim();

                // A idade veio como texto. Removemos caracteres que não sejam
                // números e convertemos o resultado para int para tratá-la como número.
                int idade = Integer.parseInt(campos[1].replaceAll("\\D", ""));

                // Também limpamos o texto do saldo, retirando "R$" e "reais".
                // Mantemos primeiro o conteúdo como String para depois convertê-lo.
                String saldoTexto = campos[2]
                        .replace("R$", "")
                        .replaceAll("(?i)\\s*reais?\\s*", "")
                        .trim();

                // BigDecimal é apropriado para valores monetários porque evita
                // imprecisões comuns em cálculos com double. Duas casas representam centavos.
                BigDecimal saldo = new BigDecimal(saldoTexto).setScale(2, RoundingMode.HALF_UP);
                String tipoConta = campos[3].trim();

                // Monta outra String com os campos limpos e o saldo numérico formatado.
                // Essa String será o valor associado à chave da conta no Map.
                String dadosConta = titular + "," + idade + "," + saldo.toPlainString() + "," + tipoConta;
                contas.put(chave++, dadosConta);
            }
        }

        // BufferedWriter é um fluxo de saída de caracteres: os dados saem
        // do programa e chegam ao arquivo. Ao abrir o arquivo dessa forma,
        // ele é criado ou seu conteúdo anterior é substituído.
        try (BufferedWriter escritor = Files.newBufferedWriter(
                Path.of("saida.txt"), StandardCharsets.UTF_8)) {
            // Percorre cada par do Map: getKey() fornece a chave numérica
            // e getValue() fornece a String com os dados da conta.
            for (Map.Entry<Integer, String> conta : contas.entrySet()) {
                escritor.write(conta.getKey() + "," + conta.getValue());
                escritor.newLine();
            }
        }
        // try-with-resources fecha os fluxos automaticamente, inclusive
        // se ocorrer uma exceção, e garante que os dados pendentes sejam gravados.
    }
}
