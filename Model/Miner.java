package Model;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Miner{
    String algoritmo;
    String cadena;
    int ceros;
    int longitudInicial;
    int longitudFinal;
    MessageDigest algoritmoDigest;
    Hash hash;

    public Miner(String algoritmo, String cadena, int ceros, int longitudInicial, int longitudFinal)
    {
        this.algoritmo = algoritmo;
        this.cadena = cadena;
        this.ceros = ceros;
        this.longitudInicial = longitudInicial;
        this.longitudFinal = longitudFinal;
        try {
            algoritmoDigest = MessageDigest.getInstance(algoritmo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.hash = new Hash();
    }

    public void mine(){
        generateCombinations(this.longitudInicial, this.longitudFinal);
    }



    public void generateCombinations(int minLength, int maxLength) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsRecursive("", minLength, maxLength, combinations);
    }

    private void generateCombinationsRecursive(String prefix, int minLength, int maxLength, List<String> combinations) {
        if (prefix.length() >= minLength) {
            combinations.add(prefix);
            this.hash.crearValidarHash(prefix, this.cadena, this.algoritmoDigest, this.ceros);
        }
        if (prefix.length() >= maxLength) {
            return;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != 'ñ') {
                generateCombinationsRecursive(prefix + c, minLength, maxLength, combinations);
            }
        }
    }




}

