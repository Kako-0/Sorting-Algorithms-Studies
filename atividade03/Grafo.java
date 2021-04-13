import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grafo<Value extends Comparable<Value>> {
    private ArrayList<Value> fifo = new ArrayList<Value>();
    private int tam;

    @SuppressWarnings("unchecked")
    public void getGraph(String txt) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(txt+".txt"));
        
        while(br.ready()){ 
            String linha = br.readLine();
            String aLinha[] = linha.split(" ");

            for (Value a : (Value[]) aLinha) {
                fifo.add(a);
            }
            Value a = (Value) " ";
            fifo.add(a);   
        }
        tam = Integer.parseInt((String)fifo.remove(0));
        br.close();
    }

    public void showTxt(){
        System.out.println("Tamanho do grafo: "+tam);
        for (Value val : fifo) {
            System.out.print(val + " ");
            if (val == " ") {
                System.out.println();
            }
        }
    }

    public void showMatrix(){
        System.out.println("Tamanho do grafo: "+tam);
        for (Value val : fifo) {
            System.out.print(val + " ");
            if (val == " ") {
                System.out.println();
            }
        }
    }
}