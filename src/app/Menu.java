package src.app;

/*Classe de menus*/

public class Menu {
	//menu principal
	public static void menu(){
        System.out.println("\n-------------------------------------------------------------------\n");
        System.out.println("\tEscolha o método do MergeSort:");
        System.out.println("1. Padrão");
        System.out.println("2. Usando o InsertionSort para subarrays pequenos");
        System.out.println("3. Testando se o vetor já está ordenado");
        System.out.println("4. Eliminando a cópia para o vetor auxiliar");
        System.out.println("5. Todos os MergesSorts");
        System.out.println("0. Sair");
        System.out.print("Opcao: ");
    }
}