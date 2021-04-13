import java.io.IOException;

public class Main {
   public static void main(String[] args) throws IOException {
      Grafo grafo = new Grafo<>();

      grafo.getGraph("dados");
      grafo.showTxt();

   }
}
