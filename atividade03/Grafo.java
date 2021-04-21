import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Grafo{
    private class Vertice implements Comparable<Vertice> {
        private String nome;
        private List<Aresta> adj;
        private String cor = "branco";
        private double distancia = 0;
        private boolean visitado = false;
        private Vertice pai;

        private Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        private void addAdj(Aresta e) {
            adj.add(e);
        }

        private void setCor(String cor) {
            this.cor = cor;
        }

        private void setDistancia(double distancia) {
            this.distancia = distancia;
        }

        private void setPai(Vertice pai){
            this.pai = pai;
        }

        private boolean isVisitado() {
            return visitado;
        }
        
        private void setVisitado(boolean visitado){
            this.visitado = visitado;
        }

        @Override
        public int compareTo(Vertice vertice) {
            if(this.distancia < vertice.distancia) 
        	    return -1;
            else if(this.distancia == vertice.distancia) 
        	    return 0;

            return 1;  
        }
    }

    private class Aresta {
        private Vertice origem;
        private Vertice destino;
        private double peso;
        private boolean visitado = false;

        Aresta(Vertice origem, Vertice destino, double peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public boolean isVisitado() {
            return visitado;
        }

        public void setVisitado(boolean visitado) {
            this.visitado = visitado;
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
        // Arraylist que retorna as arestas entre origem e destino
    	ArrayList<Aresta> arvoreLargura = new ArrayList<Aresta>();
        // Reseta as cores
        for (Vertice v : vertices) {
            v.cor = "branco";
        }
        // Pega o vertice do parametro e seta como cinza
        Vertice aux = getVertice(origem);
    	aux.setCor("cinza");
    	
        // Adiciona na fila o vertice origem
    	LinkedList<Vertice> queue= new LinkedList<Vertice>();
    	queue.add(aux);
        // Flag pra parar o while;
    	boolean achou = false;
    	while (!queue.isEmpty()) {
            // Pega a primeira posicao da fila e seta como preto
    		Vertice current = queue.remove();
    		current.setCor("preto");
            // Se o vertice pego da fila for igual ao destino, encerra o metodo
    		if (current.nome.equals(destino)) {
    			achou = true;
    			break;
    		}
    		// Visita os vertices ligado ao vertice current
    		for (Aresta aresta : current.adj) {
                Vertice vizinho = aresta.destino;
                // Se for branco seta como cinza e adiciona na fila e tambem na arraylist de retorno
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

    // Retorna quantas arestas existem entre origem e destino 
    public int numArestaBFS(String origem, String destino){
        return buscaEmLargura(origem, destino).size();
    }

    // Printa na tela o caminho da origem pro destino
    public void caminhoBFS(String origem, String destino){
        ArrayList<Aresta> caminho = buscaEmLargura(origem, destino);

        // Pega o vertice escolhido
        Vertice vDest = getVertice(destino);
        // Arralist do caminho
        ArrayList<Aresta> caminhoOrder = new ArrayList<Aresta>();
        // While faz um caminho inverso até chegar no vertice origem
        labelWhile:
        while (!vDest.nome.equals(origem)) {
            for (Aresta aresta : caminho) {
                if (aresta.destino.nome.equals(vDest.nome)) {
                    caminhoOrder.add(aresta);
                    vDest = aresta.origem;
                    continue labelWhile;
                }
            }
        }
        // Inverte a ordem para printar corretamente
        Collections.reverse(caminhoOrder);
        for (Aresta aresta : caminhoOrder) {
            System.out.print("("+aresta.origem.nome+", "+aresta.destino.nome+"), ");
        }
        System.out.println();
    }

    public void distanciaBFS(String origem, String destino, int distancia){
        ArrayList<Aresta> caminho = buscaEmLargura(origem, destino);
        // Pega o vertice escolhido
        Vertice vDest = getVertice(origem);
        double maxDist = 0;
        
        // For pra encontrar a maior distancia
        for (Aresta aresta : caminho) {
            vDest = aresta.destino;
            //vDest.distancia = aresta.origem.distancia + 1;
            vDest.setDistancia( aresta.origem.distancia + 1 );
            maxDist = vDest.distancia;
        }
        // Ternario pra agilizar o for adiante que printa os vertices
        // até a distancia dada  
        double dist = distancia >= maxDist ? maxDist : distancia;
        for (Aresta aresta : caminho) {
            if (aresta.destino.distancia <= dist) {
                System.out.print("("+aresta.destino.nome+"), ");    
            }
        }
        System.out.println();
    }

    // Caminho da busca em profundidade
    public void caminhodfs(String o, String d){
        ArrayList<Aresta> caminho = buscaProfundidade(o, d);

        for (Aresta aresta : caminho) {
            System.out.print("("+aresta.origem.nome+", "+aresta.destino.nome+"), ");
        }
        System.out.println();
    }
    private ArrayList<Aresta> buscaProfundidade(String origem, String destino){
        ArrayList<Aresta> arvoreProfundidade = new ArrayList<Aresta>(); 
    	
    	if(this.buscaRecursiva(origem, destino))
    		System.out.println("Vertice encontrado");
    	else
    		System.out.println("Vertice nao encontrado");
    	// Adiciona no arrayList os vertices visitados
    	for (int i=0; i<this.arestas.size(); i++){
    		if(this.arestas.get(i).isVisitado())
    			arvoreProfundidade.add(this.arestas.get(i));
    	}
        // Reseta os vertices
        for (Vertice v : vertices) {
            v.setVisitado(false);
        }
    	
    	return arvoreProfundidade;
    }
    //metodo recursivo que retorna um booleano como resposta da busca pelo vertice e seta como true os vertices e arestas que estarao na arvore de Busca em Profundidade
    private boolean buscaRecursiva(String origem, String destino){
		// Pega o vertice escolhido
    	Vertice aux = getVertice(origem);
        aux.setVisitado(true);
		
    	if (!origem.equals(destino)){
    		for(int i = 0; i < aux.adj.size(); i++){
    			
    			if (!aux.adj.get(i).destino.isVisitado()){
	    			//seta como visitada a aresta
	    			aux.adj.get(i).setVisitado(true);
	    			//continua busca recursivamente
	    			if (buscaRecursiva(aux.adj.get(i).destino.nome, destino))
	    				return true;
	    		}
	    	}
    	}else{
    		return true;
    	}
    	return false;
    }

    public boolean buscaCiclo(String nome)
    {
        for (Vertice v : vertices) {
            v.setDistancia(0);
            v.setVisitado(false);
        }
        Stack<Vertice> pilha = new  Stack<Vertice>();
        ArrayList<Vertice> auxV = new ArrayList<Vertice>();
        Vertice aux = getVertice(nome);
        double peso = 0;
        int time = 0;
        pilha.add(aux);
        auxV.add(aux);
        while (!pilha.isEmpty())
        {
            time += 1;
            Vertice atual = pilha.pop();
            if(!atual.visitado)
            {
                atual.setVisitado(true);
                atual.setDistancia(atual.distancia + 1);
            }
 
            for (int i = 0; i < atual.adj.size(); i++) {
                Vertice n = atual.adj.get(i).destino;
                n.setDistancia(atual.adj.get(i).origem.distancia + 1);
                
                if (n.nome.equals(aux.nome) && time > 1) {
                    System.out.println("é ciclo");
                    System.out.println("peso: "+ peso);
                    System.out.println("("+atual.adj.get(i).origem.nome+", "+atual.adj.get(i).destino.nome+")");
                    
                    for (Vertice aresta : auxV) {
                        System.out.print("("+aresta.nome+"), ");
                    }
                    System.out.println();
                    return true;
                }
                peso += atual.adj.get(i).peso;
                if(n != null && !n.visitado)
                {
                    pilha.add(n);
                    auxV.add(n);
                }
            }
        }
        System.out.println("sem ciclo");
        return false;
    }

    //----------------------DIJKSTRA-------------------------------------------
	
	//metodo que retorna o caminho menos custoso entre dois vertices a partid do algoritmo de Dijkstra
    public ArrayList<Vertice> encontrarMenorCaminhoDijkstra(String origem, String destino) {
        // Pega os respectivos vertices no grafo;
        Vertice v1 = getVertice(origem);
        Vertice v2 = getVertice(destino);
        // Lista que guarda os vertices pertencentes ao menor caminho encontrado
    	ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
        // Variavel que recebe os vertices pertencentes ao menor caminho
        Vertice verticeCaminho;
        // Variavel que guarda o vertice que esta sendo visitado
        Vertice atual;
        // Variavel que marca o vizinho do vertice atualmente visitado
        Vertice vizinho;
        // Aresta que liga o atual ao seu vizinho;
        Aresta ligacao;
        // Lista dos vertices que ainda nao foram visitados
        ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();
        // Algoritmo de Dijkstra
    	// Adiciona a origem na lista do menor caminho
        menorCaminho.add(v1);
        // Colocando a distancias iniciais 
        for (int i = 0; i < vertices.size(); i++) {
            // Vertice atual tem distancia zero, e todos os outros,
            // 9999("infinita")
            if (vertices.get(i).nome.equals(v1.nome))
                vertices.get(i).setDistancia(0);
            else
                vertices.get(i).setDistancia(9999);
            // Insere o vertice na lista de vertices nao visitados
            naoVisitados.add(vertices.get(i));
        }
        Collections.sort(naoVisitados);
        // O algoritmo continua ate que todos os vertices sejam visitados
        while (!naoVisitados.isEmpty()) {
            // O primeiro é sempre o que tem a menor distância
            atual = naoVisitados.get(0);
            /*
             * Para cada aresta, calcula-se a sua possivel distancia, 
             * somando a distancia do vertice atual com a da aresta
             * correspondente. Se essa distancia for menor que a distancia do
             * vizinho, ela é atualizada.
             */
            for (int i = 0; i < atual.adj.size(); i++) {
                vizinho = atual.adj.get(i).destino;
                if (!vizinho.isVisitado()) {    	
                    // Comparando a distância do vizinho com a possível
                    // distância
                	ligacao = atual.adj.get(i);
                    if (vizinho.distancia > (atual.distancia + ligacao.peso)) {
                        vizinho.setDistancia(atual.distancia + ligacao.peso);
                        vizinho.setPai(atual);
                        /*
                         * Se o vizinho é o vertice procurado, e foi feita uma
                         * mudança na distancia, a lista com o menor caminho
                         * anterior é apagada, pois existe um caminho menor do
                         * vertices pais, até a vertice origem.
                         */
                        if (vizinho == v2) {
                            menorCaminho.clear();
                            verticeCaminho = vizinho;
                            menorCaminho.add(vizinho);
                            while (verticeCaminho.pai != null) {
                                menorCaminho.add(verticeCaminho.pai);
                                verticeCaminho = verticeCaminho.pai;

                            }
                            // Ordena a lista do menor caminho, para que ele
                            // seja exibido da origem ao destino.
                            Collections.sort(menorCaminho);
                        }
                    }
                }
            }
            // Marca o vertice atual como visitado e o retira da lista de nao
            // visitados
            atual.setVisitado(true);
            naoVisitados.remove(atual);
            /*
             * Ordena a lista, para que o vertice com menor distancia fique na
             * primeira posicao
             */
            Collections.sort(naoVisitados);
        }
        // Reseta os pais de todos os vertices
        for (Vertice vertice : vertices) {
            vertice.setPai(null);
        }
        return menorCaminho;
    }
    public void caminhodijkstra(String o, String d){
        ArrayList<Vertice> caminho = encontrarMenorCaminhoDijkstra(o, d);

        for (Vertice aresta : caminho) {
            System.out.print("("+aresta.nome+"), ");
        }
        System.out.println();
    }
}