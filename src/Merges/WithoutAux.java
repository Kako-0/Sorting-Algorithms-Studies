package src.Merges;

public class WithoutAux {
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
            //Ao colocar um segundo merge, o array copia o vetor temporário recursivamente
            //O primeiro merge ordena T e o segundo ordena o array, através de T.
			merge(array, T, esq, meio + 1, dir);
			merge(T, array, esq, meio + 1, dir);
		}
		return array;
	}

    //Conquista
	private static void merge(int[] array, int[] T, int esqPos, int dirPos, int dirFim) {
		//último índice do sub-array esquerdo
		int esqFim = dirPos - 1;
		//Posição inicial do segundo array 
		int tempPos = esqPos;

        //Ordena os sub-arrays atráves de um revezamento de comparações
		//ex: sub-array1 = [a,c], sub-array2[b,d]
		// se a < b, então a vai pro segundo array e o b passa a comparar c
		//e assim por diante
		while (esqPos <= esqFim && dirPos <= dirFim) {
			if (array[esqPos] <= array[dirPos]) {
				T[tempPos++] = array[esqPos++];
			} else {
				T[tempPos++] = array[dirPos++];
			}
		}
        //Se o sub-array esquerdo ou direito chegar no fim, coloca os elementos restantes 
		//direto no segundo array 
		while (esqPos <= esqFim)
			T[tempPos++] = array[esqPos++];
		while (dirPos <= dirFim)
			T[tempPos++] = array[dirPos++];
	}
}
