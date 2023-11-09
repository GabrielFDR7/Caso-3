package Model;

public class Monitor 
{
    private boolean continuar = true;

    private String algoritmo = "No se encontró";
    private String cadena = "No se encontró";
    private int numZeros = 0;
    private String valor_v = "No se encontró";
    private String hash = "No se encontró";
    private long tiempo = 0;
    
    private boolean found = false;

    public synchronized boolean getContinuar()
    {
        return continuar;
    }

    public synchronized boolean getFound(){
        return found;
    }
    public synchronized void  terminar()
    {
        continuar = false;
    }

    public synchronized void setResults(String pAlgoritmo, String pCadena, String pValor_v, String pHash, long pTiempo, int numZeros, boolean found)
    {   
        if(!this.found){
            this.found = found;
            algoritmo = pAlgoritmo;
            cadena = pCadena;
            this.numZeros = numZeros;
            valor_v = pValor_v;
            hash = pHash;
            tiempo = pTiempo;
        }
        
    }

    public String getAlgoritmo()
    {
        return algoritmo;
    }
    public String getCadena()
    {
        return cadena;
    }
    public String getValor_v()
    {
        return valor_v;
    }
    public String getHash()
    {
        return hash;
    }
    public long getTiempo()
    {
        return tiempo;
    }
    public int getNumZeros(){
        return numZeros;
    }
}
