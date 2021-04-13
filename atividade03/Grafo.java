import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grafo<Value extends Comparable<Value>> {
    private ArrayList<Value> fifo = new ArrayList<Value>();

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
        br.close();
    }

    public void showTxt(){
        for (Value val : fifo) {
            System.out.print(val + " ");
            if (val == " ") {
                System.out.println();
            }
        }
    }
}