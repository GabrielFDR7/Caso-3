package Model;

import java.security.MessageDigest;

public class Hash {

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

        System.out.println("Para la cadena " + cadena + " el v que permite cumplir la condicion es " + v +" formando el hash " + hexHash.toString());
        return true;

    }
    
}
