package life.board;

import java.awt.*;

public class Triangle4Board implements Board {
	
	private final BoardHandler board;
	private final int blockSize;
	private final int halfBlockSize;
	private final int height;
	private final int width;
	
	public Triangle4Board(BoardHandler board, int tileSize) {
		this.board = board;
		this.blockSize = tileSize;
		this.halfBlockSize = this.blockSize/2;
		this.height = board.getHeight();
		this.width = board.getWidth();
	}
	
	@Override
	public void update() {
		boolean pointUp = true;
		for(int y=0; y < height; y++) {
			int aboveY = (y - 1 + height) % height;
			int belowY = (y + 1) % height;
			for(int x=0; x < width; x++) {
				int leftX = (x - 1 + width) % width;
				int rightX = (x + 1) % width;
				
				int[] adj;
				if (pointUp) {
					adj = new int[]{
							board.get(y, leftX),						
							board.get(y, rightX),
							board.get(belowY, x), };
				} else {
					adj = new int[]{
							board.get(y, rightX),						
							board.get(y, leftX),
							board.get(aboveY, x), };
				}
				board.updateCell(adj, y, x);
				
				pointUp = !pointUp;
			}
		}
		board.updateBoard();
	}

	@Override
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			boolean pointUp = true;
			int yDisp = y*blockSize;
			for(int x = 0; x < width; x++) {
				if(board.get(y,x) == 0) { 
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.BLACK);
				}
				int xDisp = x*halfBlockSize-halfBlockSize;
				if(pointUp) {
					g.fillPolygon(new int[]{halfBlockSize+xDisp,xDisp,blockSize+xDisp}, new int[]{yDisp,blockSize+yDisp,blockSize+yDisp}, 3);
				} else {
					g.fillPolygon(new int[]{xDisp,blockSize+xDisp,halfBlockSize+xDisp}, new int[]{yDisp,yDisp,blockSize+yDisp}, 3);
				}
				pointUp = !pointUp;
			}
		}
	}
}
