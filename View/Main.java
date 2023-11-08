package View;

import java.util.Scanner;

import Model.Miner;

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

                    System.out.println("Ingrese el número (int) de 0's {20,24,32,36} que desea al final de la cadena: ");
                    int numZeros = sc.nextInt();
                    int[] numPosibleZeros = {20,24,32,36};
                    if (! contains(numPosibleZeros, numZeros)){
                        throw new Exception("El número de zeros ingresado no es uno válido");
                    }

                    System.out.println("Ingrese el número (int) de threads para la búsqueda (1 o 2): ");
                    int numThreads = sc.nextInt();
                    if (!(numThreads == 1 || numThreads == 2)){
                        throw new Exception("El número de threads no es \"1\" o \"2\"");
                    }

                    if (numThreads == 1){
                        Miner miner = new Miner(algorithms[algorithmOption-1], cadena, numZeros, 1, 7);
                        miner.mine();
                    }
                    else{

                    }

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

