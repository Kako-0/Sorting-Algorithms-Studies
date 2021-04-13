import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader("dados.txt"));
      ArrayList<String> fifo = new ArrayList<String>();
      while(br.ready()){ 
         String linha = br.readLine();
         String aLinha[] = linha.split(" ");

         for (String a : aLinha) {
            fifo.add(a);
         }
         fifo.add(" ");
         
      }
      br.close();

      for (String object : fifo) {
         System.out.print(object + " ");
         if (object == " ") {
            System.out.println();
         }
      }

   }
}
