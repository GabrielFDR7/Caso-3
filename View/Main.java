package View;

import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

import Model.MinerThread;
import Model.Monitor;

public class Main
{
    public static void main(String[] args)
    {
        
        System.out.println("Prototipo de miado de bitcoin"); 
        boolean cont = true;
        Scanner sc = new Scanner(System.in);

        while(cont)
        {
            System.out.println("Eliga una opción:");
            System.out.println("1. Iniciar Proceso de Minado");
            System.out.println("2. Terminar Programa");
            
            String menuOption =  sc.next();
            if (menuOption.equals("1")){

                
                try {

                    System.out.println("Ingrese el algoritmo criptografico (int) de hash (1. SHA256, 2. SHA512): ");
                    String[] algorithms = {"SHA256", "SHA512"};
                    int algorithmOption =  sc.nextInt();
                    if ( !(algorithmOption == 1 || algorithmOption== 2)){
                        throw new Exception("La elección de algoritmo no fue \"1\" o \"2\"");
                    }

                    System.out.println("Ingrese la cadena C: ");
                    String cadena =  sc.next();
                    if (cadena.length() < 16 || cadena.length() > 20){
                        throw new Exception("La cadena no tiene una longitud entre 16 o 20 caracteres");
                    }

                    System.out.println("Ingrese el número (int) de 0's {20,24,28,32,36} que desea al final de la cadena: ");
                    int numZeros = sc.nextInt();
                    int[] numPosibleZeros = {20,24,28,32,36};
                    if (! contains(numPosibleZeros, numZeros)){
                        throw new Exception("El número de zeros ingresado no es uno válido");
                    }

                    System.out.println("Ingrese el número (int) de threads para la búsqueda (1 o 2): ");
                    int numThreads = sc.nextInt();
                    if (!(numThreads == 1 || numThreads == 2)){
                        throw new Exception("El número de threads no es \"1\" o \"2\"");
                    }
                    
                    Monitor m = new Monitor();
                    if (numThreads == 1){
                        CyclicBarrier cb = new CyclicBarrier(2);
                        MinerThread miner = new MinerThread(cb, m, algorithms[algorithmOption-1], cadena, numZeros, "a", "zzzzzzz");
                        miner.start();
                        
                        cb.await();
                    }
                    
                    else
                    {
                        CyclicBarrier cb = new CyclicBarrier(3);
                        MinerThread miner1 = new MinerThread(cb, m, algorithms[algorithmOption-1], cadena, numZeros, "a", "lzzzzzz");
                        MinerThread miner2 = new MinerThread(cb, m, algorithms[algorithmOption-1], cadena, numZeros, "maaaaaa", "zzzzzzz");

                        miner1.start();
                        miner2.start();
                        cb.await();
                    }

                    String resultado = "\n\nTerminado!!!!\n\n";
                    resultado += "Algoritmo: " + m.getAlgoritmo() + "\n";
                    resultado += "Cadena: " + m.getCadena() + "\n";
                    resultado += "Ceros: " + m.getNumZeros() + "\n";
                    resultado += "Valor v: " + m.getValor_v() + "\n";
                    resultado += "Hash: " + m.getHash() + "...\n";
                    resultado += "Tiempo(ms): " + m.getTiempo() + "\n";

                    System.out.println(resultado);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
           }
           else if (menuOption.equals("2")){
                System.out.println("Terminando programa");
                cont = false;
           }
           else {
                System.out.println("La elección no fue válida");
           }

        }
        sc.close();   
    }

    public static boolean contains(int[] arr, int ceros) {
        boolean encontro = false;
        for (int i: arr){
            if (i == ceros){
                encontro = true;
            }
        }
        return encontro;
    }
}

