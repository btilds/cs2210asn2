
public class HashDictionary {
	
	private Node[] table;
	private int size;
	
	public HashDictionary(int size) {
		this.table = new Node[size];
		this.size = size;
		for(int i = 0; i < size; i++) {
			table[i] = null;
		}
	}
	
	public int put(Configuration data) throws DictionaryException {
		int index = hashFunction(data.getStringConfiguration());
		Node headNode = table[index];
		while (headNode != null) {
			if(headNode.getConfigString().equals(data.getStringConfiguration())) {
				throw new DictionaryException("Configuration string is already stored in the dictionary.");
			}
			headNode = headNode.getNextNode();
		}
		headNode = table[index];
		Node newNode = new Node(data);
		if (headNode == null) {
			table[index] = newNode;
			return 0;
		}
		else {
			newNode.setNextNode(headNode);
			table[index] = newNode;
			return 1;
		}
	}
	
	public void remove(String config) throws DictionaryException {
		int index = hashFunction(config);
		Node headNode = table[index];
		Node prevNode = null;
		while (headNode != null) {
			if(headNode.getConfigString().equals(config)) {
				break;
			}
			prevNode = headNode;
			headNode = headNode.getNextNode();
		}
		if(headNode == null) {
			throw new DictionaryException("Configuration is not in the dictionary.");
		}
		if(prevNode != null) {
			prevNode.setNextNode(headNode.getNextNode());
		}
		else {
			table[index] = headNode.getNextNode();
		}
	}
	
	public int getScore(String config) {
		int index = hashFunction(config);
		Node headNode = table[index];
		while(headNode != null) {
			if(headNode.getConfigString().equals(config)) {
				return headNode.getScore();
			}
			headNode = headNode.getNextNode();
		}
		return -1;
	}
	
	private int findPrimeNumber(int size) {
		int result = 0;
		int temp = size;
		while (result == 0) {
			if(isPrime(temp - 1)) {
				result = temp - 1;
			}
			temp = temp - 1;
		}
		return result;
	}
	
	private boolean isPrime(int size) {
		boolean isPrime = true;
		for(int divisor = 2; divisor <= size / 2; divisor++) {
		    if (size % divisor == 0) {
		        isPrime = false;
		    }
		}
		return isPrime;
	}
	
	private int hashFunction(String config) {
		int hash = 0;
		int length = config.length();
		char c;
		for(int i = 0; i<length; i++) {
			c = config.charAt(i);
			hash = hash + (int) (c * (Math.pow(31, (length - i))));
		}
		hash = Math.abs(hash % findPrimeNumber(size));
		return hash;
	}
}
