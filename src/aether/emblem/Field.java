package aether.emblem;

public class Field {
	public int[][][] Field;
	public int Size, Depth, count, waterStart;

	public Field(int Dimention, int Plane) {
		Field = new int[Dimention][Dimention][Plane];
		Size = Dimention;
		Depth = Plane;
		this.setField();
	}

	public void setCharacters() {
		int SetX, SetY;
		for(Character character : Generator.getBlueList()) {
			SetX = character.getXLocation();
			SetY = character.getYLocation();
			this.setNum(SetX, SetY, 2, character.getListPosition());
		}
		for(Character character : Generator.getRedList()) {
			SetX = character.getXLocation();
			SetY = character.getYLocation();
			this.setNum(SetX, SetY, 3, character.getListPosition());
		}

	}

	public int[][][] setField() {
		// 0=Grass 1=Mud/Mountan/Wall 2=water
		int direction, current_x = 0;
		int theSize = this.getSize();
		int current_y = (int) ((Math.random() * (theSize - (theSize * .85))) + theSize / 2);
		waterStart = current_y;
		for(int y = 0; y < Size; y++)
			for(int x = 0; x < Size; x++) {// if field is grass, which is defalt
				if(Field[x][y][0] == 0 || Field[x][y][0] == 2)
				// create a new random terain with 0&2=grass 1=Mud/Mountan/Wall
				{
					if(Field[x][y][0] == 0) {
						Field[x][y][0] = (int) (Math.random() * 8);
						if(Field[x][y][0] != 1) {
							Field[x][y][0] = 0;
						}
					}
					// set location of water
					if(x == 0 && y == 0) {
						Field[x][current_y][0] = 2;
					}
					// if a mountain is chosen create a Mt. range for 1-4 blocks
					if(Field[x][y][0] == 1) {
						int length = (int) Math.random() * 4 + 1;
						while(length + x > this.getSize() - 1) {
							length--;
						}
						while(length + y > this.getSize() - 1) {
							length--;
						}
						// in a randomly generated direction of down
						// or right 0=down 1=right
						direction = (int) Math.random() * 2;
						for(int Length = length; Length > 0; Length--) {
							if(direction == 0 && Field[x + Length][y][0] != 2)
								Field[x + Length][y][0] = 1;
							if(direction == 1 && Field[x + Length][y][0] != 2)
								Field[x][y + Length][0] = 1;
						}
					}
					// if a water is chosen create a river
					int up = 0, down = 0;
					if(Field[0][current_y][0] == 2 && x == 0) {
						while(current_x <= this.getSize() - 2) { // chooses direction 0=up 1=right 2=down
							if(current_y == 1)// if it is at the top
							{
								up = 1;
							}
							if(current_y == this.getSize() - 2) {
								down = 1;
							}
							direction = 2;
							// move any direction, direction= 0 or 1 or 2
							// move only right or down, direction= 1 or 2
							if(up == 1) {
								direction = (int) (Math.random() * 2) + 1;
							}
							// move only right or up, direction= 0 or 1
							if(down == 1) {
								direction = (int) (Math.random() * 2);
							}
							if(direction == 0) {// move up
								up = 1;// down=0;
								current_y--;
								Field[current_x][current_y][0] = 2;
							}
							if(direction == 1) {// move right
								up = 0;
								down = 0;
								current_x++;
								Field[current_x][current_y][0] = 2;
							}
							if(direction == 2) {// move down
								down = 1;// up=0;
								current_y += 1;
								Field[current_x][current_y][0] = 2;
							}
							if(up == 1 && current_x <= this.getSize() - 3) {
								up = 0;
								down = 1;
								current_x++;
								Field[current_x][current_y][0] = 3;
								current_x++;
								Field[current_x][current_y][0] = 2;
							} else {
								if(down == 1 && current_x <= this.getSize() - 3) {
									up = 1;
									down = 0;
									current_x++;
									Field[current_x][current_y][0] = 3;
									current_x++;
									Field[current_x][current_y][0] = 2;
								}
							}
						}
					}
				}
				// take the ground and create its terrain effects into
				// the next layer 0=Grass 1=Mud/Mountain/Wall 2=water 3=bridge
				// grass =0= pass-able bridge =0= pass-able Mountain =1= slow move
				// water =2= cannot enter
				if(Field[x][y][0] == 0) {
					Field[x][y][1] = 0;
				}
				if(Field[x][y][0] == 1) {
					Field[x][y][1] = 1;
				}
				if(Field[x][y][0] == 2) {
					Field[x][y][1] = 2;
				}
				if(Field[x][y][0] == 3) {
					Field[x][y][1] = 0;
				}
			}
		return Field;
	}

	@Override
	public String toString() {

		String Grid = new String("[");
		for(int z = 0; z < Depth; z++) {
			Grid += "\n\n";
			for(int y = 0; y < Size; y++) {
				Grid += "]\n[";
				for(int x = 0; x < Size; x++)
					Grid += "," + Field[x][y][z];
			}
		}
		Grid += "]]";
		return Grid;
	}

	public int getSize() {
		return Size;
	}

	public int getDepth() {
		return Depth;
	}

	public int getNum(int x, int y, int z) {
		return Field[x][y][z];
	}

	public void setNum(int x, int y, int z, int newNumber) {
		Field[x][y][z] = newNumber;
	}

	public int water() {
		return waterStart;
	}
}