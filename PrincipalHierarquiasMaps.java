import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PrincipalHierarquiasMaps {

    // Recebe um Map e adiciona nele os mesmos pares de chave e valor.
    // Como o parâmetro é do tipo Map, podemos passar diferentes implementações.
    private static void preencher(Map<String, Double> mapa) {
        mapa.put("Carlos", 300.0);
        mapa.put("Ana", 50.0);
        mapa.put("Bia", 200.0);
    }

    public static void main(String[] args) {

        // Map é a interface comum: define operações como put, get e remove.
        // HashMap, LinkedHashMap e TreeMap são implementações dessa interface.
        Map<String, Double> hash = new HashMap<>();
        Map<String, Double> linked = new LinkedHashMap<>();
        Map<String, Double> tree = new TreeMap<>();

        // A mesma função preenche cada mapa com os mesmos dados.
        preencher(hash);
        preencher(linked);
        preencher(tree);

        // HashMap não garante uma ordem específica para os elementos.
        System.out.println("HashMap:       " + hash);

        // LinkedHashMap mantém a ordem em que as chaves foram inseridas.
        System.out.println("LinkedHashMap: " + linked);

        // TreeMap organiza as entradas pela ordem natural das chaves.
        // Como as chaves são nomes (String), ficam em ordem alfabética.
        System.out.println("TreeMap:       " + tree);
    }
}
