/*UNIVERSIDADE FEDERAL DO MARANHÃO-UFMA
 *Aluno: Kayro Maciel de França
 *Atividade Pratica 1 
 */
package src.app;

import java.util.Scanner;

import src.Merges.Standard;
import src.Merges.WithInsertion;
import src.random.RandomV;

/*Classe principal para iniciar o programa*/

public class App {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		int option;
        long inicio, fim;
        int a[] = RandomV.randomVet(10000);
        int res[];
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+",");
        }
        System.out.print("\n");
        do{
        	Menu.menu();
            option = in.nextInt();
            switch(option){
            case 1:
            	System.out.print("Padrão\n");

                inicio = System.currentTimeMillis();
                res = Standard.mergeSort(a);
            	fim = System.currentTimeMillis();
    			System.out.printf("\nTempo de processamento InsertionSort: %.3f ms%n\n", (fim - inicio) / 1000d);

                for (int i = 0; i < res.length; i++) {
            	    System.out.print(res[i]+",");
                }
                
                System.out.print("\n\n\n\n\n\n");

                break;
            case 2:
                System.out.print("Usando o InsertionSort para subarrays pequenos\n");
                
                inicio = System.currentTimeMillis();
                res = WithInsertion.mergeSort(a);
            	fim = System.currentTimeMillis();
    			System.out.printf("\nTempo de processamento InsertionSort: %.3f ms%n\n", (fim - inicio) / 1000d);
            	
                for (int i = 0; i < res.length; i++) {
            	    System.out.print(res[i]+",");
                }
                System.out.print("\n\n\n\n\n\n");
                option=0;
                break;
            case 3:
                System.out.print("Testando se o vetor já está ordenado\n");
                break;
            case 4:
                System.out.print("Eliminando a cópia para o vetor auxiliar\n");
                break;
            case 0:
            	System.out.println("Programa encerrado\n");
                break;
            default:
                System.out.println("Opção inválida.\n");
            }
        } while(option != 0);
        in.close();
    }

}