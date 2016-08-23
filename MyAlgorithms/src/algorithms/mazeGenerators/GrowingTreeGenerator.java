package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represent a maze generator algorithm
 * It generate the maze by Growing Tree algorithm
 */
public class GrowingTreeGenerator extends Maze3dGeneratorBase {
	
	/**
	 * Generate the maze by inputs (num. of floors, rows, cols)
	 * call the method to generate it by Growing Tree algorithm
	 * next move chosen by 2 options: random neighbor or last neighbor added to the list
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 * @return initialed Maze3d object
	 */
	@Override
	public Maze3d generate(int floors, int rows, int cols) {
		myMaze = new Maze3d(floors, rows, cols);
		myMaze.setStartPosition(generateRandomStartPosition(rows));
		
		// Generates the maze
		// Chooses the next position by strategy pattern (by last added position or randomly)
		generateMyMaze(new NextPosRandom());
		return myMaze;
	}
	
	/**
	 * Gets the method to make the next move (NextPosRandom() OR NextPosLastAdded())
	 * This method build the maze using the Growing Tree algorithm
	 * @param nextPos NextPosChooser, (NextPosRandom() OR NextPosLastAdded())
	 */
	private void generateMyMaze(NextPosChooser nextPos)
	{
		Position goal = null, c, n = null, startPos;
		List<Position> cells = new ArrayList<Position>();
		List<Position> neighbors = new ArrayList<Position>();
		startPos = myMaze.getStartPosition();
		freeEnterPosition(startPos);
		goal = new Position(startPos.z, startPos.y, startPos.x);
		cells.add(startPos);
		while (!cells.isEmpty())
		{
			c = nextPos.chooseMyNextPos(cells);
			neighbors = getValidNeighbors(c);
			if (!neighbors.isEmpty())
			{
				n = nextPos.chooseMyNextPos(neighbors);
				brakeWall(c, n);
				cells.add(n);
			}
			else cells.remove(c);
			if (n != null)
				goal = n;
		}
		myMaze.setGoalPosition(goal);
	}
	
	/**
	 * to avoid moving on the edge of the maze
	 * free the entrance position of the maze on the edges
	 * @param enterPos Position
	 */
	private void freeEnterPosition(Position enterPos) {
		enterPos = new Position(enterPos.z, enterPos.y, enterPos.x-1);
		myMaze.setFree(enterPos);
	}

	/**
	 * This method brake the wall between two positions
	 * Set the current position and the destination to 0 (FREE)
	 * also set the middle position to 0 (FREE) to create path
	 * @param from Position
	 * @param to Position
	 */
	private void brakeWall(Position from, Position to) {
		int middlePathZ, middlePathY, middlePathX;
		// Calculates the middle position (path)
		middlePathX = (from.x + to.x) / 2;
		middlePathY = (from.y + to.y) / 2;
		middlePathZ = (from.z + to.z) / 2;
		
		myMaze.setFree(from);
		myMaze.setFree(new Position(middlePathZ, middlePathY, middlePathX));
		myMaze.setFree(to);
	}

	/**
	 * Gets a position and return a list of all the possible and valid neighbors
	 * (valid neighbors = valid moves in the maze)
	 * @param pos Position
	 * @return List<Position>
	 */
	private List<Position> getValidNeighbors(Position pos) {
		List<Position> validNeighbors = new ArrayList<Position>();
		Position posNeighbor[] = new Position[6];
		posNeighbor[0] = new Position(pos.z+1, pos.y, pos.x);	// UP
		posNeighbor[1] = new Position(pos.z-1, pos.y, pos.x);	// DOWN
		posNeighbor[2] = new Position(pos.z, pos.y+2, pos.x);	// FORWARD
		posNeighbor[3] = new Position(pos.z, pos.y-2, pos.x);	// BACKWARD
		posNeighbor[4] = new Position(pos.z, pos.y, pos.x+2);	// RIGHT
		posNeighbor[5] = new Position(pos.z, pos.y, pos.x-2);	// LEFT
		for (int i = 0; i < 6; i++)
			if (myMaze.validPos(posNeighbor[i]) && myMaze.isWall(posNeighbor[i]))
				validNeighbors.add(posNeighbor[i]);
		return validNeighbors;
	}

	/**
	 * Get the number of the rows in the maze and randomize the start position
	 * the start position will be placed on the edge (example: start position presented by 'E')
	 * 1111111
	 * E000001
	 * 1011101
	 * 1000101
	 * 1111111
	 * @param rows int
	 * @return Position (the start position in the maze)
	 */
	private Position generateRandomStartPosition(int rows) {
		Random rand = new Random();
		int yPos = 0;
		while (yPos < 1 || yPos >= rows-1 || yPos % 2 == 0)
			yPos = rand.nextInt(rows);
		return new Position(0, yPos, 1);
	}
}