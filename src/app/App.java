/*UNIVERSIDADE FEDERAL DO MARANHÃO-UFMA
 *Aluno: Kayro Maciel de França
 *Atividade Pratica 1 
 */
package src.app;

import java.util.Arrays;
import java.util.Scanner;

import src.Merges.Standard;
import src.Merges.WithInsertion;
import src.Merges.WithOrderedArray;
import src.Merges.WithoutAux;
import src.random.RandomV;

/*Classe principal para iniciar o programa*/

public class App {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		int option;
        long inicio, fim;
        //Array que recebe os dados desordenados
        Integer[] a = (Integer[]) RandomV.randomVet(100000);

        System.out.print("\n");
        do{
        	Menu.menu();
            option = in.nextInt();
            switch(option){
            case 1:
                Standard<String> res1 = new Standard<>();
                // Integer[] aCopy = a.clone();
            	
                // for (int i = 0; i < aCopy.length; i++) {
                //     System.out.print(aCopy[i]+", ");
                // }

                String[] randomStrings = {"k", "a", "y", "r", "o", "m", "a", "c", "i", "e", "l"};
                
                System.out.print("Padrão\n");
                
                inicio = System.currentTimeMillis();
                res1.mergeSort(randomStrings);
            	fim = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento merge padrão: %.3f ms%n\n", (fim - inicio) / 1000d);
                
                for (String string : randomStrings) {
                    System.out.print(string+", ");
                }
                System.out.println("\n");
                // for (int i = 0; i < aCopy.length; i++) {
                //     System.out.print(aCopy[i]+", ");
                // }
                break;
            case 2:
                WithInsertion<Integer> res2 = new WithInsertion<>();
                Integer bCopy[] = a.clone();

                for (int i = 0; i < bCopy.length; i++) {
                    System.out.print(bCopy[i]+", ");
                }
                
                System.out.print("Usando o InsertionSort para subarrays pequenos\n");
                
                inicio = System.currentTimeMillis();
                res2.mergeSort(bCopy);
            	fim = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento merge com insertionSort: %.3f ms%n\n", (fim - inicio) / 1000d);

                for (int i = 0; i < bCopy.length; i++) {
                    System.out.print(bCopy[i]+", ");
                }
                break;
            case 3:
                WithOrderedArray<Integer> res3 = new WithOrderedArray<>();
                Integer cCopy[] = a.clone();

                for (int i = 0; i < cCopy.length; i++) {
                    System.out.print(cCopy[i]+", ");
                }
                
                System.out.print("Testando se o vetor já está ordenado\n");
                
                inicio = System.currentTimeMillis();
                res3.mergeSort(cCopy);
            	fim = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento merge com o subvetor ordenado: %.3f ms%n\n", (fim - inicio) / 1000d);
                for (int i = 0; i < cCopy.length; i++) {
                    System.out.print(cCopy[i]+", ");
                }
                break;
            case 4:
                WithoutAux<Integer> res4 = new WithoutAux<>();
                Integer dCopy[] = a.clone();
                
                for (int i = 0; i < dCopy.length; i++) {
                    System.out.print(dCopy[i]+", ");
                }

                System.out.print("Eliminando o vetor auxiliar\n");
                
                inicio = System.currentTimeMillis();
                res4.mergeSort(dCopy);
            	fim = System.currentTimeMillis();
    			System.out.printf("\nTempo de processamento merge sem o auxiliar: %.3f ms%n\n", (fim - inicio) / 1000d);

                for (int i = 0; i < dCopy.length; i++) {
                    System.out.print(dCopy[i]+", ");
                }
                break;
            case 6:
                Integer eCopy[] = a.clone();

                for (int i = 0; i < eCopy.length; i++) {
                    System.out.print(eCopy[i]+", ");
                }
                
                System.out.print("Método nativo\n");
                
                inicio = System.currentTimeMillis();
                Arrays.sort(eCopy);
            	fim = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento método nativo: %.3f ms%n\n", (fim - inicio) / 1000d);

                for (int i = 0; i < eCopy.length; i++) {
                    System.out.print(eCopy[i]+", ");
                }
                break;
            case 5:
                //Roda todos os merges
                Standard<Integer> res11 = new Standard<>();
                Integer[] a2Copy = a.clone();

                long inicio1 = System.currentTimeMillis();
                res11.mergeSort(a2Copy);
                long fim1 = System.currentTimeMillis();
                
                System.out.printf("\nTempo de processamento merge padrão: %.3f ms%n", (fim1 - inicio1) / 1000d);
                
                System.out.println("\n");

                WithInsertion<Integer> res12 = new WithInsertion<>();
                Integer b2Copy[] = a.clone();

                long inicio2 = System.currentTimeMillis();
                res12.mergeSort(b2Copy);
            	long fim2 = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento merge com insertionSort: %.3f ms%n", (fim2 - inicio2) / 1000d);

                System.out.println("\n");

                WithOrderedArray<Integer> res13 = new WithOrderedArray<>();
                Integer c2Copy[] = a.clone();

                long inicio3 = System.currentTimeMillis();
                res13.mergeSort(c2Copy);
            	long fim3 = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento merge com o subvetor ordenado: %.3f ms%n", (fim3 - inicio3) / 1000d);
                
                System.out.println("\n");

                WithoutAux<Integer> res14 = new WithoutAux<>();
                Integer d2Copy[] = a.clone();

                long inicio4 = System.currentTimeMillis();
                res14.mergeSort(d2Copy);
            	long fim4 = System.currentTimeMillis();
    			System.out.printf("\nTempo de processamento merge sem o auxiliar: %.3f ms%n", (fim4 - inicio4) / 1000d);

                
                System.out.println("\n");

                Integer e2Copy[] = a.clone();

                long inicio5 = System.currentTimeMillis();
                Arrays.sort(e2Copy);
            	long fim5  = System.currentTimeMillis();
    			
                System.out.printf("\nTempo de processamento método nativo: %.3f ms%n", (fim5 - inicio5) / 1000d);

                System.out.println("\n");
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