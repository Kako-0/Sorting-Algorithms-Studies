package src.Merges;

public class WithOrderedArray {
    public static int[] mergeSort(int[] array) {
		int[] temp = new int[array.length];
		return mergeMain(array, temp, 0, array.length - 1);
	}
	
	//Divide
	private static int[] mergeMain(int[] array, int[] T, int esq, int dir) {
		int meio;
		if (esq < dir) {
			meio = (esq + dir) / 2;
			mergeMain(array, T, esq, meio);
			mergeMain(array, T, meio + 1, dir);
			//Ele verifica se os sub-arrays já estão ordenados
            if (!(array[meio] <= array[meio + 1])) {
                merge(array, T, esq, meio + 1, dir);   
            }
		}
		return array;
	}
	//Conquista
	private static void merge(int[] array, int[] T, int esqPos, int dirPos, int dirFim) {
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
			if (array[esqPos] <= array[dirPos]) {
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
