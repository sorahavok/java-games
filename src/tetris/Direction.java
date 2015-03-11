package tetris;

public enum Direction {
	LEFT, UP, DOWN, RIGHT, HERE;
	
	public final static int UP_MOVE = -1, LEFT_MOVE = -1, DOWN_MOVE = 1, RIGHT_MOVE = 1;
	
	private static int upKey, downKey, rightKey, leftKey;
	
	public static void setKeys(int up, int down, int right, int left){
		upKey = up;
		downKey = down;
		rightKey = right;
		leftKey = left;
	}
	
	public static Direction getDir(int dir){
		if (dir == upKey)
			return UP;
		if (dir == downKey)
			return DOWN;
		if (dir == rightKey)
			return RIGHT;
		if (dir == leftKey)
			return LEFT;
		return null;
	}
}
