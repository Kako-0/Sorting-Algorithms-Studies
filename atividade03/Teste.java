import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public class Vertice {
        private String nome;
        private List<Aresta> adj;

        private boolean visitado = false;

        public Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        private void addAdj(Aresta e) {
            adj.add(e);
        }

        public boolean isVisitado() {
            return visitado;
        }
        public void setVisitado(boolean visitado){
            this.visitado = visitado;
        }
    }

    private class Aresta {
        private Vertice origem;
        private Vertice destino;
        private boolean visitado = false;

        Aresta(Vertice origem, Vertice destino) {
            this.origem = origem;
            this.destino = destino;
        }

        public boolean isVisitado() {
            return visitado;
        }
        public void setVisitado(boolean visitado){
            this.visitado = visitado;
        }
    }

    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private int tam;

    public Teste() {
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

    public Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    public Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }

    public String toString() {
        String r = "";
        System.out.println("Tamanho de vértices: "+tam);
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + ", ";
            }
            r += "\n";
        }
        return r;
    }  


    public int bfs(String origem, String Destino){
        Vertice aux;
        for (Vertice v : vertices) {
            if(v.nome.equals(origem)){
                aux = v;
            }else{
                return 0;
            }
        }
        return 0;
    }
    private Vertice getVertice(String vertice){
        for (Vertice v : vertices) {
            if(v.nome.equals(vertice))
                return v;
        }
        return null;
    }

    
    public	ArrayList<Aresta> buscaEmProfundidade(String raiz, String buscado){

    	ArrayList<Aresta> arvoreProfundidade = new ArrayList<Aresta>(); 
    	
    	if(this.buscaRecursiva(raiz, buscado))
    		System.out.println("Vertice encontrado");
    	else
    		System.out.println("Vertice nao encontrado");
    	
    	for (int i=0; i<this.arestas.size(); i++){
    		if(this.arestas.get(i).isVisitado())
    			arvoreProfundidade.add(this.arestas.get(i));
    	}
    	
    	return arvoreProfundidade;
    }
    
    //metodo recursivo que retorna um booleano como resposta da busca pelo vertice e seta como true os vertices e arestas que estarao na arvore de Busca em Profundidade
    public boolean buscaRecursiva(String raiz, String buscado){
		
    	Vertice aux = getVertice(raiz);
        aux.setVisitado(true);
		
    	if (!raiz.equals(buscado)){
    		for(int i = 0; i < aux.adj.size(); i++){
    			
    			if (!aux.adj.get(i).destino.isVisitado()){
	    			//acha aresta entre eles e seta como visitada
	    			aux.adj.get(i).setVisitado(true);
	    			//continua busca recursivamente
	    			if (buscaRecursiva(aux.adj.get(i).destino.nome, buscado))
	    				return true;
	    		}
	    	}
    	}else{
    		return true;
    	}
    	return false;
    }
}
