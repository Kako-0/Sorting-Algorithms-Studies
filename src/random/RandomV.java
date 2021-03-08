package src.random;

public class RandomV {
 
    public static Integer[] randomVet( int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (int)Math.round(Math.random() * tamanho);
        }
        return vetor;
    }

}
