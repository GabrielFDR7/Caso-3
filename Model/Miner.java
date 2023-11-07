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
        generateStringsInRange(this.longitudInicial, this.longitudFinal);
    }

    public List<String> generateStringsInRange(int minLength, int maxLength) 
    {
        List<String> strings = new ArrayList<>();
    
        generateStringsForLength(maxLength, strings);

        return strings;
    }
    
    private void generateStringsForLength(int length, List<String> strings) {
        boolean continuarGenerando = true;
    
        for (int i = 0; i < Math.pow(25, length) && continuarGenerando; i++) {
            StringBuilder currentString = new StringBuilder();
            int num = i;
            boolean verificacion = false;
    
            for (int j = 0; j < length; j++) {
                char c = (char) ('a' + (num % 25));
                if (c >= 'ñ') {
                    c++; // Saltar la letra 'ñ'
                }
                currentString.insert(0, c);
                num /= 25;
            }
    
            verificacion = hash.crearValidarHash(currentString.toString(), cadena, algoritmoDigest, ceros);
    
            if (verificacion) {
                System.out.println("Resultado: " + cadena + currentString.toString());
                System.out.println("Para la cadena " + cadena + " el v que permite cumplir la condición es " + currentString.toString() + " formando el hash " + hash.darHexString());
                continuarGenerando = false; // Detener la generación de cadenas
            }
    
            strings.add(currentString.toString());
        }
    }
    

}

