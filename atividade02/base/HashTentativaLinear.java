public class HashTentativaLinear<Key, Value> {
	private int N; // numero de pares de chaves na tabela
	private int M = 16; // Tamanho da tabela hash com tratamento linear
	private Key[] keys; // the keys
	public Value[] vals; // the values
	
	//Constructor com a tabela de tamanho padrão igual a 16
	@SuppressWarnings("unchecked")
	public HashTentativaLinear() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	//Constructor com a tabela de tamanho igual a cap
	@SuppressWarnings("unchecked")
	public HashTentativaLinear(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
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
	
	/**
	 * Redimensiona a tabela de acordo com a quantidade de chaves.
	 * @param cap
	 */
	private void resize(int cap) {
		
		HashTentativaLinear<Key, Value> t;
		t = new HashTentativaLinear<Key, Value> (cap);
		
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
		
	}
	
	 public boolean contains(Key key) {
	        if (key == null) {
	            throw new IllegalArgumentException("Argument to contains() cannot be null");
	        }

	        return get(key) != null;
	 }
	
	/**
	 * Insere um novo objeto no Hash 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		int i;
		if (N >= M/2) 
			resize(2*M); // double M 
		
		//Enquanto keys[i] != null, incrementa +1(tentativa linear)
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			//quando a key for igual a keys[i], atribue o value no value[i]
			//Essa função sobrescreve o value caso a keys sejam iguais
			if (keys[i].equals(key)) { 
				vals[i] = val; 
				return; 
			}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

//////////// Hashing duplo ////////////////////////////////////////////////
	private int hash2(Key key){ 
		return (key.hashCode() & 0x7fffffff) % (M - 1); 
	}

	public void putDuplo(Key key, Value val) {
		int i = 0;
		int h1;
		// (k mod (M - 1)) + 1		
		int h2 = hash2(key) + 1;

		if (N >= M/2) 
			resize(2*M); // double M
		
		for (h1 = hash(key); keys[h1] != null; h1 = (h1 + i * h2) % M){
			i++;
			if (keys[h1].equals(key)) { 
				vals[h1] = val; 
				return; 
			}
		}
		keys[h1] = key;
		vals[h1] = val;
		N++;	
	}
	
	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	public void delete(Key key)
	{
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		
		if (!contains(key))
			return;
			
		int i = hash(key);
		//usa a tentativa linear para encontrar a key certa
		while (!key.equals(keys[i]))
			i = (i + 1) % M;
		
		//Deleta da tabela
		keys[i] = null;
		vals[i] = null;
		//Pega o próximo índice para verificar se possue o mesmo hash
		i = (i + 1) % M;
		
		//Algoritmo que deleta fisicamente e decrementa as keys na tabela se possível
		while (keys[i] != null){
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N--;
		if (N > 0 && N == M/8) 
			resize(M/2);
	}
	
	/**
	 * Busca um objeto no Hash
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}
}
