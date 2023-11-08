package Model;

import java.security.MessageDigest;

public class MinerThread extends Thread 
{
    private Monitor monitor;

    private String algoritmo;
    private String cadena;
    private int ceros;
    private MessageDigest algoritmoDigest;
    private String inicio;
    private String fin;
    private Hash hash;
    private String currentString;

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
        long startTime = System.currentTimeMillis();
        this.generateStringsInRange(inicio, fin);
        long endTime = System.currentTimeMillis();

        long finalTime = endTime - startTime;
        monitor.setResults(algoritmo, cadena, currentString, hash.darHexString(), finalTime);
    }

    private void generateStringsInRange(String startString, String endString) {
        currentString = startString;
        
        
        while (monitor.getContinuar() && currentString.compareTo("zzzzzzza")<0) {
    
            // Verificar el hash
            boolean verificacion = hash.crearValidarHash(currentString, cadena, algoritmoDigest, ceros);
            
            if (verificacion || currentString.equals("zzzzzzz"))
            {
                monitor.setContinuar();
            }
            else 
            {
                currentString = generateNextString(currentString);
            }
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
