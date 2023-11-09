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
        

        if(foundAnswer)
            monitor.setResults(algoritmo, cadena, currentV, hash.binaryPrettyString(cadena, currentV), finalTime, ceros, foundAnswer);
        else {
            monitor.setResults(algoritmo + " -> No encontrado", cadena + " -> No encontrado", currentV + " -> ultimo (v)", hash.binaryPrettyString(cadena, currentV), finalTime, ceros, foundAnswer);
        }

        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean generateStringsInRange() {
        boolean verificacion = hash.crearValidarHash(currentV, cadena, ceros);

        while (monitor.getContinuar() && compareOrder(currentV, fin)) {
             
            if (verificacion)
            {
                monitor.terminar();
            }
            else 
            {
                currentV = generateNextString(currentV);
                verificacion = hash.crearValidarHash(currentV, cadena, ceros);
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
    
    private boolean compareOrder(String v ,String end){
        if (v.length() == end.length()){
            return v.compareTo(end) < 0;
        }
        else{
            return v.length() < end.length();
        }
    } 

}
