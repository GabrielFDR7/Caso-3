package View;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        
        System.out.println("Prototipo de miado de bitcoin");
        
        boolean cont = true;
        Scanner sc = new Scanner(System.in);

        while(cont)
        {
            System.out.println("Ingrese el algoritmo criptografico de hash");
            System.out.println("1. SHA256");
            System.out.println("2. SHA512");
            int algoritmo = sc.nextInt();
            if (algoritmo != 0 || algoritmo != 1)
            {
                System.out.println("Elección no valida");
                cont = false;
            }
        }
        
        cont = true;
        
        while (cont)
        {
            System.out.println("Ingrese la cadena de la transacción: ");
            String cadena = sc.nextLine();
        }
        
        while (cont)
        {
            System.out.println("Ingrese el numero de 0s iniciales");
            int ceros = sc.nextInt();
            int[] arr = {20, 24, 28, 32, 36};
            boolean encontro = false;
            for (int i: arr){if (i == ceros){encontro = true;}}
            if (encontro == false)
            {   
                System.out.println("Ingrese un valor valido");
                cont = false;
            }
        }
        cont = true;
        
        while (cont)
        {
            System.out.println("Ingrese el numero de threads para la busqueda");
            int threads = sc.nextInt();
            if (threads > 0)
            {
                System.out.println("Ingrese un valor valido");
                cont = false;
            }
        }
    }
}

