import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    private int tam;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    public void getGraph(String txt) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(txt+".txt"));
        tam = Integer.parseInt(br.readLine());
        labelWhile:
        while(br.ready()){ 
            String linha = br.readLine();
            String aLinha[] = linha.split(" ");
            if (aLinha.length == 2) {
                String linhaIndice0 = aLinha[0];
                String linhaIndice1 = aLinha[1];
                
                if(vertices.isEmpty()){
                    Vertice aux1 = addVertice(linhaIndice0);
                    Vertice aux2 = addVertice(linhaIndice1);;
                    addAresta(aux1, aux2);
                    continue labelWhile;
                }

                
                for (int i = 0; i < vertices.size(); i++) {
                    if(vertices.get(i).nome.equals(linhaIndice0)){
                        for (Vertice v : vertices) {
                            if(v.nome.equals(linhaIndice1)){
                                addAresta(vertices.get(i), v);
                                continue labelWhile;
                            }
                        }
                        Vertice aux1 = addVertice(linhaIndice1);
                        addAresta(vertices.get(i), aux1);
                        continue labelWhile;
                    }
                }
                Vertice aux1 = addVertice(linhaIndice0);
                Vertice aux2 = new Vertice(linhaIndice1);
                addAresta(aux1, aux2);
            }  
        }
        
        br.close();
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

    private Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    private Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }
}