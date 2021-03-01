package src.Merges;

public class Standard {
     public static int[] mergeSort(int[] array) {
		int[] temp = new int[array.length];
		return mergeMain(array, temp, 0, array.length - 1);
	}

	private static int[] mergeMain(int[] array, int[] T, int esq, int dir) {
		int meio;
		if (esq < dir) {
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
