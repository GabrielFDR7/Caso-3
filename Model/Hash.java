package Model;

import java.security.MessageDigest;

public class Hash {

    private String hexHash;

    public boolean crearValidarHash(String v, String cadena, MessageDigest algoritmoDigest, int cantidadCeros) {

        // Creacion de hash
        String concatenacion = cadena + v;
        byte[] hash = algoritmoDigest.digest(concatenacion.getBytes());
        StringBuilder hexHash = new StringBuilder();
        for (byte b : hash) {
            hexHash.append(String.format("%02x", b));
        }


        // Validacion de hash
        int index = 0; 
        while (index<cantidadCeros/4){
            char character = hexHash.charAt(index);
            if (character != '0'){
                return false;
            }
            index++;
        }

        this.hexHash = hexHash.toString();
        return true;

    }

    public String darHexString()
    {
        return hexHash;
    }
}
