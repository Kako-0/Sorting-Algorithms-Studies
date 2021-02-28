/*UNIVERSIDADE FEDERAL DO MARANHÃO-UFMA
 *Aluno: Kayro Maciel de França
 *Atividade Pratica 1 
 */
package src.app;

import java.util.Scanner;

/*Classe principal para iniciar o programa*/

public class App {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		int option;
        
        do{
        	Menu.menu();
            option = in.nextInt();
            switch(option){
            case 1:
            	System.out.print("Padrão");
                break;
            case 2:
                System.out.print("Usando o InsertionSort para subarrays pequenos");
                break;
            case 3:
                System.out.print("Testando se o vetor já está ordenado");
                break;
            case 4:
                System.out.print("Eliminando a cópia para o vetor auxiliar");
                break;
            case 0:
            	System.out.println("Programa encerrado");
                break;
            default:
                System.out.println("Opção inválida.");
            }
        } while(option != 0);
       
    }

}