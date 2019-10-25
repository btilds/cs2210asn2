import java.util.Arrays;

public class BoardGame {
	
	private int n;
	private char[][] gameBoard;
	
	public BoardGame(int board_size, int empty_positions, int max_levels) {
		this.n = board_size;
		this.gameBoard = new char[n][n];
		for(char[] row: gameBoard) {
			Arrays.fill(row, 'g');
		}
	}
	
	public HashDictionary makeDictionary() {
		return new HashDictionary(9973);
	}
	
	public int isRepeatedConfig(HashDictionary dict) {
		String code = Arrays.deepToString(gameBoard).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		return dict.getScore(code);
	}
	
	public void putConfig(HashDictionary dict, int score) {
		String code = Arrays.deepToString(gameBoard).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		Configuration data = new Configuration(code, score);
		try {
			dict.put(data);
		}
		catch (DictionaryException e) {
		}
	}
	
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	public boolean positionIsEmpty(int row, int col) {
		return gameBoard[row][col] == 'g';
	}
	
	public boolean tileOfComputer(int row, int col) {
		return gameBoard[row][col] == 'o';
	}
	
	public boolean tileOfHuman(int row, int col) {
		return gameBoard[row][col] == 'b';
	}
	
	public boolean wins(char symbol) {
		// Check rows for win
		for(int row = 0; row < n; row++) {
			int count = 0;
			for(int column = 0; column < n; column++) {
				if(gameBoard[row][column] == symbol) count++;
			}
			if(count == n) return true;
		}
		// Check columns for win
		for(int column = 0; column < n; column++) {
			int count = 0;
			for(int row = 0; row < n; row++) {
				if(gameBoard[row][column] == symbol) count++;
			}
			if(count == n) return true;
		}
		// Check top left to bottom right diagonal
		int topLeftBottomRight = 0;
		for(int row = 0, column = 0; row < n; row++, column++) {
			if(gameBoard[row][column] == symbol) topLeftBottomRight++;
			if(topLeftBottomRight == n) return true;
		}
		// Check bottom left to top right diagonal
		int bottomLeftTopRight = 0;
		for(int row = n - 1, column = 0; column < n; row--, column++) {
			if(gameBoard[row][column] == symbol) bottomLeftTopRight++;
			if(bottomLeftTopRight == n) return true;
		}
		return false;
	}
	
	public boolean isDraw(char symbol, int empty_positions) {
		int spacesFull = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(gameBoard[i][j] != 'g') spacesFull++;
			}
		}
		if(empty_positions == 0 && spacesFull == (n*n)) return true;
		else if(empty_positions > 0 && spacesFull == (n*n) - empty_positions) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(gameBoard[i][j] == 'g') {
						try {
							if(gameBoard[i+1][j] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i-1][j] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i][j+1] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i][j-1] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i+1][j+1] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i+1][j-1] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i-1][j-1] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
						try {
							if(gameBoard[i-1][j+1] == symbol) return false;
						}
						catch (IndexOutOfBoundsException e) {
						}
					}
					else {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int evalBoard(char symbol, int empty_positions) {
		if(wins('o')) {
			return 3;
		}
		else if(isDraw(symbol, empty_positions)) {
			return 2;
		}
		else if(wins('b')) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
