package life.board;

import java.awt.*;
import java.awt.image.ImageObserver;

public class HexBoard implements Board {

	private final BoardHandler board;
	private final int blockSize;
	private final int height;
	private final int width;
	private final Image full;
	private final Image empty;
	private final ImageObserver imageObserver;
	private final int[] adj = new int[6];
	
	public HexBoard(BoardHandler board, int tileSize, Image full, Image empty, ImageObserver imageObserver) {
		this.board = board;
		this.blockSize = tileSize;
		this.height = board.getHeight();
		this.width = board.getWidth();
		this.full = full;
		this.empty = empty;
		this.imageObserver = imageObserver;
	}
	
	@Override
	public void update() {
		for(int y=0; y < height; y++) {
			int aboveY = (y - 1 + height) % height;
			int twoAboveY = (y - 2 + height) % height;
			int belowY = (y + 1) % height;
			int twoBelowY = (y + 2) % height;
			
			for(int x=0; x < width; x++) {
				int leftX = (x - 1 + width) % width;

				adj[0] = board.get(twoAboveY, x);						
				adj[1] = board.get(aboveY, leftX);						
				adj[2] = board.get(aboveY, x);						
				adj[3] = board.get(belowY, leftX);						
				adj[4] = board.get(belowY, x);						
				adj[5] = board.get(twoBelowY, x);
				
				board.updateCell(adj, y, x);
			}
		}
		board.updateBoard();
	}

	@Override
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			
			int yOffset = y % 2 == 0 ? 0 : blockSize/2;
			int xOffset = y % 2 == 0 ? 0 : (int)(blockSize * .75); 
			
			for(int x = 0; x < width; x++) {
				Image img = board.get(y,x) == 0 ? empty : full;
				g.drawImage(img, (int)(1.5*x*blockSize)+xOffset, y/2*blockSize+yOffset, blockSize, blockSize, imageObserver);
			}
		}
	}
}
