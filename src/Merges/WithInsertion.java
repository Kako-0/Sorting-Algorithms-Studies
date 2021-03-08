package src.Merges;

import java.util.Arrays;

public class WithInsertion<T extends Comparable<T>> {
    private  T[] insertionSort(T[] array, int esq, int dir) {
        int i, j;
        T chave;

        // Um for que pecorre todo o sub-array
        for (j = esq; j < dir+1; j++) {
            // chave tem o primeiro valor do sub-array
            chave = array[j];

			//Tamanho do sub-array
            i = j-1;
			//Enquanto a chave for menor que o elemento que está na
			//posição i, a função "empurra" esses elementos pra frente
            while ((i >= 0) && (array[i].compareTo(chave) >= 0)) {
                array[i + 1] = array[i];
                i--;
            }
			//Quando a chave for maior ele troca de posição com esse elemento
            array[i + 1] = chave;
        }
		
        return array;
    }
    
    public  T[] mergeSort(T[] array) {
		T[] temp = Arrays.copyOfRange(array, 0, array.length);
		return mergeMain(array, temp, 0, array.length - 1);
	}
	//Divide
	private  T[] mergeMain(T[] array, T[] T, int esq, int dir) {
		int meio;
		//Se a divisão do array chegar a 15 elementos ou menos, aplica-se o método insertionSort()
		if (dir - esq <= 15) {
			insertionSort(array, esq, dir);
		}else {
			meio = (esq + dir) / 2;
			mergeMain(array, T, esq, meio);
			mergeMain(array, T, meio + 1, dir);
			merge(array, T, esq, meio + 1, dir);
		}
		return array;
	}

	//Conquista
	private  void merge(T[] array, T[] T, int esqPos, int dirPos, int dirFim) {
		//último índice do sub-array esquerdo
		int esqFim = dirPos - 1;
		//Posição inicial do array temporário
		int tempPos = esqPos;
		//Tamanho do array
		int numElem = dirFim - esqPos + 1;

		//Ordena os sub-arrays atráves de um revezamento de comparações
		//ex: sub-array1 = [a,c], sub-array2[b,d]
		// se a < b, então a vai pro array temporário e o b passa a comparar c
		//e assim por diante
		while (esqPos <= esqFim && dirPos <= dirFim) {
			if (array[esqPos].compareTo(array[dirPos]) <= 0) {
				T[tempPos++] = array[esqPos++];
			} else {
				T[tempPos++] = array[dirPos++];
			}
		}
		//Se o sub-array esquerdo ou direito chegar no fim, coloca os elementos restantes 
		//direto no array temporário 
		while (esqPos <= esqFim)
			T[tempPos++] = array[esqPos++];
		while (dirPos <= dirFim)
			T[tempPos++] = array[dirPos++];

		//Array sorteado recebe o array temporário ordenado
		for (int i = 0; i < numElem; i++) {
			array[dirFim] = T[dirFim];
			dirFim--;
		}
	}
}
