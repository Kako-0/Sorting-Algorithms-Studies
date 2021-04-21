import java.io.IOException;

public class Main {
   public static void main(String[] args) throws IOException {
      Grafo grafo = new Grafo();

      grafo.getGraph("dados1");
      System.out.println(grafo.toString());
      //System.out.println(grafo.numArestaBFS("0", "10"));
      //grafo.caminhodfs("a","f");
      //grafo.caminhoBFS("a","d");
      //grafo.distanciaBFS("0","10", 4);
      //grafo.caminhodijkstra("a", "f");
      grafo.buscaCiclo("a");
   }
}
