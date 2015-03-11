package life.generator;

@SuppressWarnings("unused")
public class StaticBoardGenerator implements BoardGenerator {

	@Override
	public int[][] getBoard() {
		return concat(TOAD,concat(TOAD,concat(TOAD,concat(TOAD,TOAD))));
	}
	
	private static int[][] BLINKER = new int[][]{
		{0,0,0,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0}};
	
	private static int[][] GLIDER= new int[][]{
		{0,0,0,0,0,0},
		{0,0,0,1,0,0},
		{0,0,0,0,1,0},
		{0,0,1,1,1,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}};
	
	private static int[][] SPACESHIP = new int[][]{
		{0,0,0,0,0,0,0,0,0},
		{0,0,1,0,0,1,0,0,0},
		{0,0,0,0,0,0,1,0,0},
		{0,0,1,0,0,0,1,0,0},
		{0,0,0,1,1,1,1,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0}};
	
	private static int[][] TOAD = new int[][]{
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,1,1,1,0},
		{0,1,1,1,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}};

	public int[][] concat(int[][] a, int[][] b) {
		   int aLen = a.length;
		   int bLen = b.length;
		   int[][] c= new int[aLen+bLen][];
		   System.arraycopy(a, 0, c, 0, aLen);
		   System.arraycopy(b, 0, c, aLen, bLen);
		   return c;
		}
}
