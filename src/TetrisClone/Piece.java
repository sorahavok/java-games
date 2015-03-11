package TetrisClone;

import java.util.Random;

public class Piece {
	private static Random rand = new Random();
	public int type, Xloc, Yloc;
	public Block[] blocks;

	public Piece() {
		Xloc = 5;
		Yloc = 0;
		type = rand.nextInt(7) + 1;
		createPiece();
	}

	private void createPiece() {
		blocks = new Block[4];
		// zig
		if (type == 1) {
			blocks[0] = new Block(0, 0);
			blocks[1] = new Block(1, 0);
			blocks[2] = new Block(1, 1);
			blocks[3] = new Block(2, 1);
		}
		// zag
		if (type == 2) {
			blocks[0] = new Block(0, 1);
			blocks[1] = new Block(1, 1);
			blocks[2] = new Block(1, 0);
			blocks[3] = new Block(2, 0);
		}
		// block
		if (type == 3) {
			blocks[0] = new Block(0, 0);
			blocks[1] = new Block(1, 0);
			blocks[2] = new Block(0, 1);
			blocks[3] = new Block(1, 1);
		}
		// line
		if (type == 4) {
			blocks[0] = new Block(0, 0);
			blocks[1] = new Block(0, 1);
			blocks[2] = new Block(0, 2);
			blocks[3] = new Block(0, 3);
		}
		// backward L
		if (type == 5) {
			blocks[0] = new Block(0, 0);
			blocks[1] = new Block(0, 1);
			blocks[2] = new Block(0, 2);
			blocks[3] = new Block(1, 2);
		}
		// L piece
		if (type == 6) {
			blocks[0] = new Block(1, 0);
			blocks[1] = new Block(1, 1);
			blocks[2] = new Block(1, 2);
			blocks[3] = new Block(0, 2);
		}
		// T piece
		if (type == 7) {
			blocks[0] = new Block(1, 0);
			blocks[1] = new Block(0, 1);
			blocks[2] = new Block(1, 1);
			blocks[3] = new Block(2, 1);
		}
	}

	public void move(int dir) {
		// down:0 left:37 (-1) right:39 (1) space:32
		int side = 0, down = 0;
		if (dir == 40) {
			down = 1;
		} else if (dir == 37) {
			side = -1;
		} else if (dir == 39) {
			side = 1;
		} else if (dir == 32) {
			rotate();
			return;
		}

		if (canMove(side, down)) {
			updateMap(0);
			Xloc += side;
			Yloc += down;
			updateMap(type);
		}
	}

	private void rotate() {
		Block[] t = getBlocks();
		int xMin = 0, yMin = 0;
		for (Block b : t) {
			int temp = b.X;
			b.X = -b.Y;
			b.Y = temp;
			if (b.X < xMin)
				xMin = b.X;
			if (b.Y < yMin)
				yMin = b.Y;
		}
		for (Block b : t) {
			b.X -= xMin;
			b.Y -= yMin;
			int X = b.X + Xloc, Y = b.Y + Yloc;
			if (X > GameField.map[0].length || Y > GameField.map.length) {
				System.out.println("Out of Bounds");
				return;
			} else if (GameField.map[Y][X] > 10) {
				System.out.println("It's a WALL!");
				return;
			}
		}
		updateMap(0);
		blocks = t;
		updateMap(type);
	}

	private Block[] getBlocks() {
		Block[] a = new Block[blocks.length];
		for (int i = 0; i < blocks.length; i++)
			a[i] = new Block(blocks[i].X, blocks[i].Y);
		return a;
	}

	public boolean autoDown() {
		for (Block b : blocks) {
			if (GameField.map[b.Y + Yloc + 1][b.X + Xloc] > 10) {
				updateMap(type + 10);
				return false;
			}
		}
		updateMap(0);
		Yloc++;
		updateMap(type);
		return true;
	}

	private boolean canMove(int s, int d) {
		for (Block b : blocks) {
			if (GameField.map[b.Y + Yloc + d][b.X + Xloc + s] > 10)
				return false;
		}
		return true;
	}

	public void updateMap(int i) {
		for (Block b : this.blocks) {
			GameField.map[Yloc + b.Y][Xloc + b.X] = i;
		}
	}

	public int getWidth(int size) {
		int x = 0;
		for (Block b : blocks) {
			if (b.X > x) {
				x = b.X;
			}
		}
		return (x + 1) * size;
	}

	public int getHeight(int size) {
		int y = 0;
		for (Block b : blocks) {
			if (b.Y > y)
				y = b.Y;
		}
		return (y + 1) * size;
	}

	@Override
	public String toString() {
		String str = "";
		for (Block b : blocks)
			str += b.toString();
		return str;
	}

	public class Block {
		int X, Y;

		private Block(int x, int y) {
			X = x;
			Y = y;
		}

		@Override
		public String toString() {
			return "(" + X + "," + Y + ")";
		}
	}
}
