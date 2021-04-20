import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Grafo{
    private class Vertice {
        private String nome;
        private List<Aresta> adj;
        private String cor = "branco";
        private int distancia = 0;

        private Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        private void addAdj(Aresta e) {
            adj.add(e);
        }

        public void setCor(String cor) {
            this.cor = cor;
        }

        public void setDistancia(int distancia) {
            this.distancia = distancia;
        }
    }

    private class Aresta {
        private Vertice origem;
        private Vertice destino;
        private double peso;

        Aresta(Vertice origem, Vertice destino, double peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
    }
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    private int tam;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    private Vertice getVertice(String vertice){
        for (Vertice v : vertices) {
            if(v.nome.equals(vertice))
                return v;
        }
        return null;
    }

    public void getGraph(String txt) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(txt+".txt"));
        tam = Integer.parseInt(br.readLine());
        labelWhile:
        while(br.ready()){ 
            String linha = br.readLine();
            String aLinha[] = linha.split(" ");
            double linhaPeso = (aLinha.length == 3) ? Double.parseDouble(aLinha[2]) : 0.0;
            if (aLinha.length >= 2 && aLinha.length <= 3) {
                String linhaIndice0 = aLinha[0];
                String linhaIndice1 = aLinha[1];
                
                if(vertices.isEmpty()){
                    Vertice aux1 = addVertice(linhaIndice0);
                    Vertice aux2 = addVertice(linhaIndice1);;
                    addAresta(aux1, aux2, linhaPeso);
                    continue labelWhile;
                }

                
                for (int i = 0; i < vertices.size(); i++) {
                    if(vertices.get(i).nome.equals(linhaIndice0)){
                        for (Vertice v : vertices) {
                            if(v.nome.equals(linhaIndice1)){
                                addAresta(vertices.get(i), v, linhaPeso);
                                continue labelWhile;
                            }
                        }
                        Vertice aux1 = addVertice(linhaIndice1);
                        addAresta(vertices.get(i), aux1, linhaPeso);
                        continue labelWhile;
                    }
                }
                Vertice aux1 = addVertice(linhaIndice0);
                Vertice aux2 = new Vertice(linhaIndice1);
                addAresta(aux1, aux2, linhaPeso);
            }
        }
        
        br.close();
    }
    
    public String toString() {
        String r = "";
        System.out.println("Tamanho de vértices: "+tam);
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + "("+e.peso+"), ";
            }
            r += "\n";
        }
        return r;
    } 

    // Atualizar Metodo
    // public void showList(){
    //     System.out.println(fifo.size());
    //     for (int i = 0; i < graph.length; i++) {
    //         System.out.print(i+1+": ");
    //         for (Value value : graph[i]) {
    //             System.out.print(1+" ");
    //         }
    //         System.out.println();
    //     }
    // }

    private Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    private Aresta addAresta(Vertice origem, Vertice destino, Double peso) {
        Aresta e = new Aresta(origem, destino, peso);
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }

//------------------BUSCA-EM-LARGURA---------------------------------------    
    private	ArrayList<Aresta> buscaEmLargura(String origem, String destino){

    	ArrayList<Aresta> arvoreLargura = new ArrayList<Aresta>();
        for (Vertice v : vertices) {
            v.cor = "branco";
        }
        Vertice aux = getVertice(origem);
    	aux.setCor("cinza");
    	
    	LinkedList<Vertice> queue= new LinkedList<Vertice>();
    	queue.add(aux);
    	boolean achou = false;
    	while (!queue.isEmpty()) {
    		Vertice current = queue.remove();
    		current.setCor("preto");
    		if (current.nome.equals(destino)) {
    			achou = true;
    			break;
    		}
    		
    		for (Aresta aresta : current.adj) {
                Vertice vizinho = aresta.destino;
    			if(vizinho.cor.equals("branco")){
    				vizinho.setCor("cinza");
    				queue.add(vizinho);
                    
    				arvoreLargura.add(aresta);
    			}
    		}
    	}
    	
    	if (achou) {
    		System.out.println("Vertice encontrado");
    	} else {
    		System.out.println("Vertice nao encontrado");
    	}
    	return arvoreLargura;
    }

    public int numArestaBFS(String origem, String destino){
        return buscaEmLargura(origem, destino).size();
    }

    public void caminhoBFS(String origem, String destino){
        ArrayList<Aresta> caminho = buscaEmLargura(origem, destino);

        for (Aresta aresta : caminho) {
            System.out.print("("+aresta.origem.nome+", "+aresta.destino.nome+"), ");
        }
        System.out.println();
        Vertice vDest = getVertice(destino);
        ArrayList<Aresta> ans = new ArrayList<Aresta>();
        labelWhile:
        while (!vDest.nome.equals(origem)) {
            for (Aresta aresta : caminho) {
                if (aresta.destino.nome.equals(vDest.nome)) {
                    ans.add(aresta);
                    vDest = aresta.origem;
                    continue labelWhile;
                }
            }
        }

        Collections.reverse(ans);
        for (Aresta aresta : ans) {
            System.out.print("("+aresta.origem.nome+", "+aresta.destino.nome+"), ");
        }
        System.out.println();
    }

    public void distanciaBFS(String origem, String destino, int distancia){
        ArrayList<Aresta> caminho = buscaEmLargura(origem, destino);
        Vertice vDest = getVertice(origem);
        int maxDist = 0;
        
        for (Aresta aresta : caminho) {
            vDest = aresta.destino;
            //vDest.distancia = aresta.origem.distancia + 1;
            vDest.setDistancia( aresta.origem.distancia + 1 );
            maxDist = vDest.distancia;
        }
        int dist = distancia >= maxDist ? maxDist : distancia;
        for (Aresta aresta : caminho) {
            if (aresta.destino.distancia <= dist) {
                System.out.print("("+aresta.destino.nome+"), ");    
            }
        }
        System.out.println();
    }
}