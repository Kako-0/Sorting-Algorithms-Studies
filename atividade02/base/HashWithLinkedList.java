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

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() cannot be null");
		}

		int i = hash(key);
		if (keys[i].equals(key))
			return true;
		return false;
	}

	/**
	 * Insere um novo objeto no Hash 
	 * @param key
	 * @param vals2
	 */
	public void put(Key key, Value vals2) {
		int i = hash(key);

		keys[i] = key;

		LinkedList<Value> items = vals[i];
		if(items == null) {
			items = new LinkedList<Value>();

			items.add(vals2);

			vals[i] = items;
		}
		else {
			vals[i].add(vals2);
		}
		N++;
	}

	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	public void delete(Key key, Value val)
	{
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		
		if (!contains(key))
			return;
			
		int i = hash(key);
		
		//Deleta da tabela
		keys[i] = null;
		for(int j = 0 ; j < vals[i].size() ; j++){
			if (val.equals(vals[i].get(j))) {
				vals[i].remove(val);
			}
		}

		N--;
	}

	/**
	 * Busca um objeto no Hash
	 * @param key
	 * @return
	 */
	public void get(Key key, Value val) {
		int i = hash(key);
		if(key == null)
			System.out.println("Key not found");

		LinkedList<Value> items = vals[i];

		if(items == null)
			System.out.println("Value not found");


		for(Value item : items) {
			if(item.equals(val))
				System.out.println("Value: "+item+" of key "+key);
		}
		System.out.println("Key or Value not found");
	}
	public void get(Key key) {
		int i = hash(key);
		if(key == null)
			System.out.println("Key not found");

		LinkedList<Value> items = vals[i];

		if(items == null)
			System.out.println("Key not found");

		//System.out.println("Value's key");
		for(Value item : items) {
			System.out.print(item+" ");
		}
		System.out.println();
	}

	public void print() {
		System.out.println("Tabela hash:");
		for (Key key : keys) {
			get(key);
		}
	}
}
