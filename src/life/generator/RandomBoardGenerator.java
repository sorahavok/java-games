package life.generator;

import java.util.Random;

public class RandomBoardGenerator implements BoardGenerator {

	private final Random rand = new Random();
	private final int height;
	private final int width;
	
	public RandomBoardGenerator(final int height, final int width) {
		this.height = height;
		this.width = width;// * 2 + 1;
	}
	
	@Override
	public int[][] getBoard() {
		final int[][] boardArray = new int[height][width];
		
		for(int i = 0; i < height*width/2; i++) {
			boardArray[rand.nextInt(height)][rand.nextInt(width)] = 1;
		}
		return boardArray;
	}

}
