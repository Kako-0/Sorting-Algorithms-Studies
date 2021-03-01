package src.random;

public class Random {
 
    public static int[] randomVet( int tamanho) {
        int vetor[] = new int[tamanho];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (int)Math.round(Math.random() * 10);
        }
        return vetor;
    }

}
