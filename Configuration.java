
public class Configuration {
	
	private String configString;
	private int configScore;
	
	public Configuration(String config, int score) {
		this.configString = config;
		this.configScore = score;
	}
	public String getStringConfiguration() {
		return this.configString;
	}
	public int getScore() {
		return this.configScore;
	}

}
