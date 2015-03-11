package life.board;

import life.ruleset.*;

public class BoardHandler {

	private final RuleSet rules;
	
	private int[][] boardArray;
	private int[][] boardArrayBuffer;
	
	public BoardHandler(RuleSet rules, int[][] boardArray) {
		this.rules = rules;
		this.boardArray = boardArray;
		this.boardArrayBuffer = new int[getHeight()][getWidth()];
	}

	public int getHeight() {
		return boardArray.length;
	}

	public int getWidth() {
		return boardArray[0].length;
	}

	
	public int get(int y, int x) {
		return boardArray[y][x];
	}

	
	public void updateCell(int[] neighbors, int y, int x) {
		BlockState nextState = rules.getState(neighbors, boardArray[y][x]);
		switch (nextState) {
			case STAY: boardArrayBuffer[y][x] = boardArray[y][x]; break;
			case LIVE: boardArrayBuffer[y][x] = 1; break;
			case DIE: boardArrayBuffer[y][x] = 0; break;
			default: System.out.println("Error, Unknown BlockState: " + nextState);
		}		
	}

	public void updateBoard() {
		int[][] temp = boardArray;
		boardArray = boardArrayBuffer;
		boardArrayBuffer = temp;
	}
}
