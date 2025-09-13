package javaapplication2;
import java.util.Scanner;

public class Prueba{
   public static void main (String [] args){
      NumerosParImpar npi = new NumerosParImpar();
      Scanner sc = new Scanner(System.in);
      System.out.println("Dame un numero");
      int num = sc.nextInt();
      npi.validar(num);
    }
}