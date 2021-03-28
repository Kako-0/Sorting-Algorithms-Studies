public class RBTree<Key extends Comparable<Key>, Value>
{	
	
	protected static final boolean RED = true;
    protected static final boolean BLACK = false;

    protected class Node {
        public Key chave;
        public Value valor;
        public Node esq, dir;

        boolean cor = BLACK;
        int size;

        Node(Key key, Value value, int size, boolean color) {
            this.chave = key;
            this.valor = value;

            this.size = size;
            this.cor = color;
        }
    }

    protected Node raiz;

	private boolean isRed(Node h) {
		if (h == null) return BLACK;
        return (h.cor == RED);
	}
	
	@SuppressWarnings("unused")
	private boolean isBlack(Node h) {
		if (h == null) return true;
        return (h.cor == BLACK);
	}
	
	public int size() {
        return size(raiz);
    }

    protected int size(Node no) {
        if (no == null) {
            return 0;
        }

        return no.size;
    }

    public boolean isEmpty() {
        return size(raiz) == 0;
    }
	
	/**
	  * Rotação à equerda
	  * @param node
	  * @return
	*/
	protected Node rotacaoEsquerda(Node no) {
        if (no == null || no.dir == null) {
            return no;
        }

        Node novaRaiz = no.dir;

        no.dir = novaRaiz.esq;
        novaRaiz.esq = no;

		novaRaiz.cor = no.cor;
        no.cor = RED;

        novaRaiz.size = no.size;
        novaRaiz.size = size(no.esq) + 1 + size(no.dir);

        return novaRaiz;
    }
		
	/**
	 * Implementar o esse método
	 * @param h
	 * @return
	*/
	private Node rotacaoDireita(Node h) {
		// Implementar método que aplica a rotação à direita.
		if ( h == null || h.esq == null) {
			return h;
		}

		Node novaRaiz = h.esq;

		h.esq = novaRaiz.dir;
		novaRaiz.dir = h;

		novaRaiz.cor = h.cor;
        h.cor = RED;

		novaRaiz.size = h.size;
        novaRaiz.size = size(h.esq) + 1 + size(h.dir);

		return novaRaiz;
	}
	private void trocaCor(Node h) {
		// Implementar método que troca as cores.
		//Usando o ! fica mais fácil trocas múltiplas
		//Pois !true == false e vice-versa
		h.cor = !h.cor;
        h.esq.cor = !h.esq.cor;
        h.dir.cor = !h.dir.cor;
	}
	
	
	/**
	 * Insere um novo nó
	 * @param key
	 * @param val
	*/
	public void insere(Key key, Value val){ 
		raiz = insere(raiz, key, val);
		raiz.cor = BLACK;
	}
	
	private Node insere(Node h, Key key, Value val){
		if (h == null) // Do standard insert, with red link to parent.
			return new Node(key, val, 1, RED);
		
		int cmp = key.compareTo(h.chave);
		if (cmp < 0) 
			h.esq = insere(h.esq, key, val);
		else if (cmp > 0) 
			h.dir = insere(h.dir, key, val);
		else h.valor = val;
		
		if (isRed(h.dir) && !isRed(h.esq)) 
			h = rotacaoEsquerda(h);
		if (isRed(h.esq) && isRed(h.esq.esq)) 
			h = rotacaoDireita(h);
		if (isRed(h.esq) && isRed(h.dir)) 
			trocaCor(h);
		
		h.size = size(h.esq) + size(h.dir) + 1;
		return h;
	}
	private int count(Node tree) {
		if(tree != null) {
			int redCount = 0;
			if (tree.cor == RED) {
				redCount++;
			}
            redCount += count(tree.esq);
            redCount += count(tree.dir);
			return redCount;
		}
		return 0;
    }
	public void count(){
		double count  = (count(raiz) * 100) / raiz.size;
		String resultado = String.format("%.2f", count);
		System.out.println("Porcentagem de nós vermelhos: "+resultado+"%");
	}
	
	public void print() {
        this.print(raiz, "", 0);
    }

    private void print(Node node, String prefix, int depth) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < (raiz.size - node.size); i++) {
            System.out.print("  ");
        }
        if (!prefix.equals("")) {
            System.out.print(prefix);
            System.out.print(":");
        }
        System.out.print(node.chave);
        System.out.print(" (");
        System.out.print("H:");
        System.out.print(node.size+1);
        System.out.println(")");
        print(node.esq, "L", depth + 1);
        print(node.dir, "R", depth + 1);
    }
}
