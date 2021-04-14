import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo<Value extends Comparable<Value>> {
    private ArrayList<Value> fifo = new ArrayList<Value>();
    private LinkedList<Value> graph[];
    private int tam;

    @SuppressWarnings("unchecked")
    private void receiveGraph(int tam){
        graph = new LinkedList[tam];
        for (int i = 0; i < tam; i++) {
            graph[i] = new LinkedList<Value>();
        }
    }
    @SuppressWarnings("unchecked")
    public void getGraph(String txt) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(txt+".txt"));
        
        while(br.ready()){ 
            String linha = br.readLine();
            String aLinha[] = linha.split(" ");

            for (Value a : (Value[]) aLinha) {
                fifo.add(a);
            }   
        }
        tam = Integer.parseInt((String)fifo.remove(0));
        receiveGraph(tam);
        br.close();
    }

    public void showTxt(){
        System.out.println("Tamanho de vértices: "+tam);
        int a = 0;
        for (Value val : fifo) {
            System.out.print(val + " ");
            a++;
            if (a % 2 == 0) {
                System.out.println();
            }
        }
    }

    public void howMuch(){
        System.out.println(fifo.size()-(tam+1));
    }

}