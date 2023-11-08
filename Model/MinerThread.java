package Model;

import java.util.concurrent.CyclicBarrier;

public class MinerThread extends Thread 
{
    private CyclicBarrier cb;
    private Monitor monitor;

    private String algoritmo;
    private String cadena;
    private int ceros;
    
    private String fin;
    private String currentV;


    private Hash hash;

    public MinerThread(CyclicBarrier cb ,Monitor pMonitor, String algoritmo, String cadena, int ceros, String pInicio, String pFin)
    {
        this.cb = cb;

        this.monitor = pMonitor;
        this.algoritmo = algoritmo;

        this.cadena = cadena;
        this.ceros = ceros;

        this.currentV = pInicio;
        this.fin = pFin;
    
        this.hash = new Hash(algoritmo);
    }

    public void run()
    {
        long startTime = System.currentTimeMillis();
        
        boolean foundAnswer = this.generateStringsInRange();

        long endTime = System.currentTimeMillis();
        
        long finalTime = endTime - startTime;
        

        if(! monitor.getUpdated() && foundAnswer)
            monitor.setResults(algoritmo, cadena, currentV, hash.binaryPrettyString(cadena, currentV), finalTime);
       
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean generateStringsInRange() {
        boolean verificacion = false;

        while (monitor.getContinuar() && fixAlphabeticalOrderError(currentV).compareTo(fin)<=0) {
 
            verificacion = hash.crearValidarHash(currentV, cadena, ceros);
            
            if (verificacion)
            {
                monitor.terminar();
            }
            else 
            {
                currentV = generateNextString(currentV);
            }
        }
        return verificacion;
    }
    
    private String generateNextString(String currentString) {
        char[] chars = currentString.toCharArray();
        int lastChar = chars.length - 1;
        String resp;
        boolean continuar = true;
        for (int i = lastChar; 0<=i && continuar; i--){
            if(chars[i] < 'z'){
                chars[i]++;
                continuar = false;
            }
            else{
                chars[i] = 'a';
            }
        }
        resp = new String(chars);
        if (continuar){
            resp +="a";     
        }
        return resp;
    }
    
    private String fixAlphabeticalOrderError(String s){
        String a =  "a".repeat(7-s.length()) + s;
        return a;
    } 

}
