package algorithms.mazeGenerators;

/**
 * This class represent an object that carries 3d position (by x, y, z)
 */
public class Position {
	public int z;
	public int y;
	public int x;
	
	/**
	 * Constructor
	 * @param floor int, the z of the position
	 * @param row int, the y of the position
	 * @param col int, the x of the position
	 */
	public Position(int floor, int row, int col) {
		this.z = floor;
		this.y = row;
		this.x = col;
	}
	
	/**
	 * override the default toString()
	 * @return String "{z, y, x}"
	 */
	@Override
	public String toString() {
		return "{" + z + ", " + y + ", " + x + "}";
	}
	
	/**
	 * override the equals method
	 * check if the positions are the same positions (by z, y, x)
	 * @param obj Object, casting to Position
	 * @return boolean true if equals, else false
	 */
	@Override
	public boolean equals(Object obj) {
		Position pos = (Position)obj; 
		return (this.z == pos.z && this.y == pos.y && this.x == pos.x);
	}
}
