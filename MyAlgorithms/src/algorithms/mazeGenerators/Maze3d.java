package algorithms.mazeGenerators;

import java.util.List;
import java.util.ArrayList;

/**
 * Maze3d object (3 dimensional maze)
 */
public class Maze3d {
	private int[][][] maze3d;
	private int floors;
	private int rows;
	private int cols;
	private Position startPosition;
	private Position goalPosition;
	
	public static final int FREE = 0;
	public static final int WALL = 1;
	
	/**
	 * Constructor
	 * fills the matrix created with '1' (walls)
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 */
	public Maze3d(int floors, int rows, int cols) {
		this.floors = floors;
		this.rows = rows;
		this.cols = cols;
		maze3d = new int[floors][rows][cols];
		fillMazeWithWalls();
	}
	
	/**
	 * Fills the matrix with '1'
	 */
	private void fillMazeWithWalls() {
		for (int i = 0; i < this.floors; i++)
			for (int j = 0; j < this.rows; j++)
				for (int k = 0; k < this.cols; k++)
					this.maze3d[i][j][k] = WALL;
	}
	
	public Position getStartPosition() {
		return startPosition;
	}
	
	public void setStartPosition(Position startPositon) {
		this.startPosition = startPositon;
		setFree(this.startPosition);
	}
	
	public Position getGoalPosition() {
		return goalPosition;
	}
	
	public void setGoalPosition(Position goalPositon) {
		this.goalPosition = goalPositon;
		setFree(this.goalPosition);
	}
	
	public int[][][] getMaze3d() {
		return maze3d;
	}

	/**
	 * Check if the position on the matrix is valid
	 * @param pos Position
	 * @return boolean true if the position is valid, else false
	 */
	public boolean validPos(Position pos) {
		if (pos.z < this.floors && pos.z >= 0)
			if (pos.y < this.rows-1 && pos.y > 0)
				if (pos.x < this.cols-1 && pos.x > 0)
					return true;
		return false;
	}

	/**
	 * Check if the position sent is wall
	 * @param pos Position
	 * @return boolean
	 */
	public boolean isWall(Position pos)
	{
		if (maze3d[pos.z][pos.y][pos.x] == WALL)
			return true;
		else return false;
	}
	
	/**
	 * Print the maze
	 * E for start position, X for goal position, 1 for wall, 0 for free position
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int z = 0; z < floors; z++) {
			for (int y = 0; y < rows; y++) {
				for (int x = 0; x < cols; x++) {
					if (z == startPosition.z && y == startPosition.y && x == 0 &&maze3d[z][y][x] == 0) 
						sb.append("E");
					else if (z == goalPosition.z && y == goalPosition.y && x == goalPosition.x)
						sb.append("X");
					else
						sb.append(maze3d[z][y][x]);
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Gets a position in the matrix and calculate the next possible moves
	 * each move will represent by String at the String[]
	 * @param pos Position
	 * @return String[]
	 */
	public String[] getPossibleMoves(Position pos) {
		List<Position> possibleMoves = new ArrayList<Position>();
		Position moves[] = new Position[6];
		if (validPos(pos))
		{
			moves[0] = new Position(pos.z+1, pos.y, pos.x);	// UP
			moves[1] = new Position(pos.z-1, pos.y, pos.x);	// DOWN
			moves[2] = new Position(pos.z, pos.y+1, pos.x);	// FORWARD
			moves[3] = new Position(pos.z, pos.y-1, pos.x);	// BACKWARD
			moves[4] = new Position(pos.z, pos.y, pos.x+1);	// RIGHT
			moves[5] = new Position(pos.z, pos.y, pos.x-1);	// LEFT
			for (int i = 0; i < 6; i++)
				if (validPos(moves[i]) && !isWall(moves[i]))
					possibleMoves.add(moves[i]);
		}
		String[] strMoves = new String[possibleMoves.size()];
		for (int i = 0; i < strMoves.length; i++)
			strMoves[i] = possibleMoves.get(i).toString();
		return strMoves;
	}

	/**
	 * 2d maze by specific x
	 * @param x int
	 * @return int[][]
	 */
	public int[][] getCrossSectionByX(int x) {
		if (x < 0 || x >= this.cols)
			throw new IndexOutOfBoundsException();
		int[][] maze2dx = new int[this.floors][this.rows];
		for (int i = 0; i < this.floors; i++)
			for (int j = 0; j < this.rows; j++)
				maze2dx[i][j] = maze3d[i][j][x];
		return maze2dx;
	}

	/**
	 * 2d maze by specific y
	 * @param y int
	 * @return int[][]
	 */
	public int[][] getCrossSectionByY(int y) {
		if (y < 0 || y >= this.rows)
			throw new IndexOutOfBoundsException();
		int[][] maze2dy = new int[this.floors][this.cols];
		for (int i = 0; i < this.floors; i++)
			for (int j = 0; j < this.cols; j++)
				maze2dy[i][j] = maze3d[i][y][j];
		return maze2dy;
	}

	/**
	 * 2d maze by specific z
	 * @param z int
	 * @return int[][]
	 */
	public int[][] getCrossSectionByZ(int z) {
		if (z < 0 || z >= this.floors)
			throw new IndexOutOfBoundsException();
		int[][] maze2dz = new int[this.rows][this.cols];
		for (int i = 0; i < this.rows; i++)
			for (int j = 0; j < this.cols; j++)
				maze2dz[i][j] = maze3d[z][i][j];
		return maze2dz;
	}

	/**
	 * Set position as free (put in this position at the matrix 0)
	 * @param pos Position
	 */
	public void setFree(Position pos) {
		maze3d[pos.z][pos.y][pos.x] = FREE;
	}
}