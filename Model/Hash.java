package Model;

import java.security.MessageDigest;

public class Hash {

    private MessageDigest algoritmoDigest;

    public Hash(String algoritmo){
        try {
            algoritmoDigest = MessageDigest.getInstance(algoritmo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public boolean crearValidarHash(String v, String cadena, int cantidadCeros) {

        // Creacion de hash
        String concatenacion = cadena + v;
        byte[] hash = algoritmoDigest.digest(concatenacion.getBytes());
       
        String hashBinaryString =  bytesToBinaryString(hash);
        // Validacion de hash
        int index = 0; 
        while (index<cantidadCeros){
            char character = hashBinaryString.charAt(index);
            if (character != '0'){
                return false;
            }
            index++;
        }
        return true;

    }

    public String  binaryPrettyString (String cadena, String v){
        String concatenacion = cadena + v;
        byte[] bytes = algoritmoDigest.digest(concatenacion.getBytes());

        String hashString = bytesToBinaryString(bytes);

        StringBuilder prettyAnswer = new StringBuilder(40);
        for (int i = 0; i < 40; i++) {
            if (i > 0 && i % 4 == 0) {
                prettyAnswer.append(" ");
            }
            prettyAnswer.append(hashString.charAt(i));
        }
        return prettyAnswer.toString();
    }
    
    private String bytesToBinaryString(byte[] bytes) {
        StringBuilder resultado = new StringBuilder(40);
        for (int i = 0; i < 5; i++) {
            for (int j = 7; j >= 0; j--) {
                resultado.append((bytes[i] >> j) & 1);
            }
        }
        return resultado.toString();
    }
}
