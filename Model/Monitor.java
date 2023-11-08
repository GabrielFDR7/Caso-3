package Model;

public class Monitor 
{
    private static boolean continuar;

    private MinerThread minerThread1;
    
    private MinerThread minerThread2;

    public synchronized boolean getContinuar()
    {
        return continuar;
    }
    public synchronized void setContinuar()
    {
        continuar = false;
    }

    public Monitor(String pAlgoritmo, String pCadena, int ceros)
    {
        Monitor.continuar = true;
        minerThread1 = new MinerThread(this, pAlgoritmo, pCadena, ceros, "a", "zzzm");
        minerThread2 = new MinerThread(this, pAlgoritmo, pCadena, ceros, "zzzn", "zzzzzzz");

        minerThread1.start();
        minerThread2.start();
    }
}
