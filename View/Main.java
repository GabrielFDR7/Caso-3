package View;

import java.util.Scanner;

import Model.Miner;

public class Main
{
    public static void main(String[] args)
    {
        
        System.out.println("Prototipo de miado de bitcoin");

        String algoritmo = null;
        String cadena = null;
        int ceros = 0;
        int threads = 0;
        
        boolean cont = true;
        Scanner sc = new Scanner(System.in);

        while(cont)
        {

            System.out.print("Ingrese el algoritmo criptografico de hash (1. SHA256, 2. SHA512): ");
            int eleccion = sc.nextInt();
            if (eleccion != 1 && eleccion != 2){
                System.out.println("Elección de algoritmo no valida.");
                cont = false;
            }
            if (eleccion == 1){
                algoritmo = "SHA256";
            }
            else if (eleccion == 2){
                algoritmo = "SHA512";
            }

            if (cont){
                System.out.print("Ingrese la cadena de la transacción: ");
                cadena = sc.next();
            }

            if (cont){
                System.out.print("Ingrese el numero de 0s iniciales: ");
                ceros = sc.nextInt();
                int[] arr = {20, 24, 28, 32, 36};
                boolean encontro = false;
                for (int i: arr){if (i == ceros){encontro = true;}}
                if (encontro == false)
                {   
                    System.out.println("Cantidad de 0s incorrecta.");
                    cont = false;
                }
            }
        
            if (cont){
                System.out.print("Ingrese el numero de threads para la busqueda (1 o 2): ");
                threads = sc.nextInt();
                if (threads <= 0 || threads > 2){
                    System.out.println("Ingrese un número de threads valido");
                    cont = false;
                }
            }

            if (cont){
                System.out.print("¿Desea terminar el programa después de esta ejecución? (S/N): ");
                String respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("S")){
                    cont = false;
                }
            }

            if (algoritmo != null && cadena != null && ceros != 0 && threads != 0){
                if(threads == 1){
                    Miner miner = new Miner(algoritmo, cadena, ceros, 1, 7);
                    miner.mine();
                }
                else{
                    Miner miner1 = new Miner(algoritmo, cadena, ceros, 1, 3);
                    miner1.mine();
                    Miner miner2 = new Miner(algoritmo, cadena, ceros, 4, 7);
                    miner2.mine();
                }
            }

        }

        sc.close();
        
        

    }

}

