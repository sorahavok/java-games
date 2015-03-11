package life.board;

import java.awt.*;

public class SquareBoard implements Board {

	private static final Color OFF = new Color(51, 102, 204);
	private static final Color ON = new Color(255, 102, 0);
	
	private final BoardHandler board;
	private final int blockSize;	
	private final int height;
	private final int width;
	
	public SquareBoard(BoardHandler board, int tileSize) {
		this.board = board;
		this.blockSize = tileSize;
		this.height = board.getHeight();
		this.width = board.getWidth();
	}
	
	@Override
	public void update() {
		int aboveY;
		int belowY;
		int leftX;
		int rightX;
		for(int y=0; y < height; y++) {
			aboveY = (y - 1 + height) % height;
			belowY = (y + 1) % height;
			
			for(int x=0; x < width; x++) {
				leftX = (x - 1 + width) % width;
				rightX = (x + 1) % width;
				
				int[] adj = { board.get(aboveY,leftX),
						board.get(aboveY,x),
						board.get(aboveY,rightX),
						board.get(y,leftX),
						board.get(y,rightX),
						board.get(belowY,leftX),
						board.get(belowY,x),
						board.get(belowY,rightX)};
				
				board.updateCell(adj, y, x);
			}
		}
		board.updateBoard();
	}

	@Override
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(board.get(y,x) == 0) { 
					g.setColor(OFF);
				} else { 
					g.setColor(ON);
				}
				g.fillRect(x*blockSize, y*blockSize, blockSize, blockSize);
			}
		}
	}
}
