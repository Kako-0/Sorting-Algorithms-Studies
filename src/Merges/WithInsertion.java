package src.Merges;

public class WithInsertion {
    public static int[] InsertionSort(int[] array, int esq, int dir) {
        int i, j;
        int chave;

        // Um for que pecorre todo o array
        for (j = esq; j < dir; j++) {
            // chave tem o primeiro valor do array
            chave = array[j];

            i = j - 1;
            while (i >= 0 && array[i] > chave) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = chave;
        }
		
        return array;
    }
    
    public static int[] mergeSort(int[] array) {
		int[] temp = new int[array.length];
		return mergeMain(array, temp, 0, array.length - 1);
	}

	private static int[] mergeMain(int[] array, int[] T, int esq, int dir) {
		int meio;
		if (dir - esq <= 15) {
			InsertionSort(array, esq, dir);
		}else {
			meio = (esq + dir) / 2;
			mergeMain(array, T, esq, meio);
			mergeMain(array, T, meio + 1, dir);
			merge(array, T, esq, meio + 1, dir);
		}
		return array;
	}

	private static void merge(int[] array, int[] T, int esqPos, int dirPos, int dirFim) {
		int esqFim = dirPos - 1;
		int tempPos = esqPos;
		int numElem = dirFim - esqPos + 1;

		while (esqPos <= esqFim && dirPos <= dirFim) {
			if (array[esqPos] <= array[dirPos]) {
				T[tempPos++] = array[esqPos++];
			} else {
				T[tempPos++] = array[dirPos++];
			}
		}
		while (esqPos <= esqFim)
			T[tempPos++] = array[esqPos++];
		while (dirPos <= dirFim)
			T[tempPos++] = array[dirPos++];

		for (int i = 0; i < numElem; i++) {
			array[dirFim] = T[dirFim];
			dirFim--;
		}
	}
}
