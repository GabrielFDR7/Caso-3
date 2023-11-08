package Model;

public class Monitor 
{
    private static boolean continuar = true;

    private String algoritmo;
    private String cadena;
    private String valor_v;
    private String hash;
    private long tiempo;

    public synchronized boolean getContinuar()
    {
        return continuar;
    }
    public synchronized void setContinuar()
    {
        continuar = false;
    }

    public void setResults(String pAlgoritmo, String pCadena, String pValor_v, String pHash, long pTiempo)
    {
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
