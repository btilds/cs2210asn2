
public class Node {
	
	private String configString;
	private int score;
	
	private Node next;
	
	public Node(Configuration data) {
		this.configString = data.getStringConfiguration();
		this.score = data.getScore();
	}
	
	public String getConfigString() {
		return this.configString;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public Node getNextNode() {
		return this.next;
	}
	
	public void setNextNode(Node data) {
		this.next = data;
	}
}
