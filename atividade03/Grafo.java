import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grafo<Value extends Comparable<Value>> {
    private class Vertice {
        private String nome;
        private List<Aresta> adj;

        private Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        private void addAdj(Aresta e) {
            adj.add(e);
        }
    }

    private class Aresta {
        private Vertice origem;
        private Vertice destino;

        Aresta(Vertice origem, Vertice destino) {
            this.origem = origem;
            this.destino = destino;
        }
    }
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    
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

    public void showList(){
        System.out.println(fifo.size());
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i+1+": ");
            for (Value value : graph[i]) {
                System.out.print(1+" ");
            }
            System.out.println();
        }
    }
}