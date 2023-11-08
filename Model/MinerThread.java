package Model;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MinerThread extends Thread 
{
    private Monitor monitor;

    String algoritmo;
    String cadena;
    int ceros;
    int longitudInicial;
    int longitudFinal;
    MessageDigest algoritmoDigest;
    String inicio;
    String fin;
    Hash hash;

    public MinerThread(Monitor pMonitor, String algoritmo, String cadena, int ceros, String pInicio, String pFin)
    {
        this.monitor = pMonitor;
        this.algoritmo = algoritmo;
        this.cadena = cadena;
        this.ceros = ceros;
        this.inicio = pInicio;
        this.fin = pFin;

        try {
            algoritmoDigest = MessageDigest.getInstance(algoritmo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.hash = new Hash();
    }

    public void run()
    {
        List<String> strings = new ArrayList<>();
        while (monitor.getContinuar())
        {
            this.generateStringsInRange(inicio, fin, strings);
        }
    }

    private void generateStringsInRange(String startString, String endString, List<String> strings) {
        boolean continuarGenerando = true;
        String currentString = startString;
        
        while (continuarGenerando) {
            if (currentString.equals(endString)) {
                continuarGenerando = false;
            }
    
            // Verificar el hash
            boolean verificacion = hash.crearValidarHash(currentString, cadena, algoritmoDigest, ceros);
    
            if (verificacion) {
                System.out.println("Resultado: " + cadena + currentString);
                System.out.println("Para la cadena " + cadena + " el v que permite cumplir la condición es " + currentString + " formando el hash " + hash.darHexString());
                monitor.setContinuar();
                continuarGenerando = false; // Detener la generación de cadenas
            }
    
            strings.add(currentString);
    
            // Generar el siguiente string lexicográficamente
            currentString = generateNextString(currentString);
        }
    }
    
    private String generateNextString(String currentString) {
        char[] chars = currentString.toCharArray();
        int index = chars.length - 1;
    
        while (index >= 0) {
            if (chars[index] < 'z') {
                chars[index]++;
                break;
            } else {
                chars[index] = 'a';
                index--;
            }
        }
    
        return new String(chars);
    }
    
}
