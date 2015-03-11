package life.board;

import java.awt.*;

public class SquareBoard implements Board {

	private static final Color OFF = new Color(51, 102, 204);
	private static final Color ON = new Color(255, 102, 0);
	
	private final BoardHandler board;
	private final int blockSize;	
	private final int height;
	private final int width;
	private final int[] adj = new int[8];
	
	public SquareBoard(BoardHandler board, int tileSize) {
		this.board = board;
		this.blockSize = tileSize;
		this.height = board.getHeight();
		this.width = board.getWidth();
	}
	
	@Override
	public void update() {
		for(int y=0; y < height; y++) {
			int aboveY = (y - 1 + height) % height;
			int belowY = (y + 1) % height;
			
			for(int x=0; x < width; x++) {
				int leftX = (x - 1 + width) % width;
				int rightX = (x + 1) % width;
				
				adj[0] = board.get(aboveY,leftX);
				adj[1] = board.get(aboveY,x);
				adj[2] = board.get(aboveY,rightX);
				adj[3] = board.get(y,leftX);
				adj[4] = board.get(y,rightX);
				adj[5] = board.get(belowY,leftX);
				adj[6] = board.get(belowY,x);
				adj[7] = board.get(belowY,rightX);
				
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
