import java.util.LinkedList;

public class HashWithLinkedList<Key, Value> {
	private int N; // numero de pares de chaves na tabela
	private int M = 16; // Tamanho da tabela hash com tratamento linear
	private Key[] keys; // the keys
	public LinkedList<Value>[] vals; // the values
	
	//Constructor com a tabela de tamanho padrão igual a 16
	@SuppressWarnings("unchecked")
	public HashWithLinkedList() {
		keys = (Key[]) new Object[M];
		vals = new LinkedList[M];
		//Atribuindo null pra cada posição do array de value
		for(int i = 0; i < M; i++) {
            vals[i] = null;
        }
	}
	
	//Constructor com a tabela de tamanho igual a cap
	@SuppressWarnings("unchecked")
	public HashWithLinkedList(int cap) {
		keys = (Key[]) new Object[cap];
		vals = new LinkedList[cap];
		//Atribuindo null pra cada posição do array de value
		for(int i = 0; i < cap; i++) {
            vals[i] = null;
        }
		M = cap;
	}
	
	/**
	 * Calcula o Hash
	 * @param key
	 * @return
	 */
	private int hash(Key key){ 
		return (key.hashCode() & 0x7fffffff) % M; 
	}

	
}
