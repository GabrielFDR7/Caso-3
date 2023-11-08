package Model;

public class Monitor 
{
    private boolean continuar = true;

    private String algoritmo = "No se encontró";
    private String cadena = "No se encontró";
    private String valor_v = "No se encontró";
    private String hash = "No se encontró";
    private long tiempo = 0;
    
    private boolean updated = false;

    public synchronized boolean getContinuar()
    {
        return continuar;
    }

    public synchronized boolean getUpdated(){
        return updated;
    }
    public synchronized void  terminar()
    {
        continuar = false;
    }

    public synchronized void setResults(String pAlgoritmo, String pCadena, String pValor_v, String pHash, long pTiempo)
    {   
        updated = true;
        algoritmo = pAlgoritmo;
        cadena = pCadena;
        
        valor_v = pValor_v;

        hash = pHash;
        tiempo = pTiempo;
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
}
