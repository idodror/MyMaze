package algorithms.mazeGenerators;

import java.util.*;

/**
 * This class represent a maze generator algorithm
 * It generate the maze by simple maze algorithm
 */
public class SimpleMaze3dGenerator extends Maze3dGeneratorBase {

	private Position myPos;
	
	/**
	 * Generate the maze by inputs (num. of floors, rows, cols)
	 * call the method to generate it by simple maze algorithm
	 * try to get from the start position to the goal position in minimum steps
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 * @return initialed Maze3d object
	 */
	@Override
	public Maze3d generate(int floors, int rows, int cols) {
		myMaze = new Maze3d(floors, rows, cols);
		Position goal, start;
		boolean finished = false;
		start = generateRandomStartPosition(rows);
		do {
			goal = generateRandomGoalPosition(floors, rows, cols);
		} while (goal.equals(start) || !myMaze.validPos(goal));
		myMaze.setStartPosition(start);
		freeEnterPosition(start);
		myMaze.setGoalPosition(goal);
		myPos = new Position(start.z, start.y, start.x);
		finished = moveByX();
		if (!finished)
			finished = moveByY();
		if (!finished)
			finished = moveByZ();
		return myMaze;
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
	 * move from the start position to the goal position by z
	 * @return boolean if goal founded
	 */
	private boolean moveByZ() {
		int negativeOrPositiveSteps = -1;
		if (myPos.z == myMaze.getGoalPosition().z)
			return false;
		int steps = myPos.z - myMaze.getGoalPosition().z;
		if (steps < 0)
		{
			negativeOrPositiveSteps = 1;
			steps *= -1;
		}
		
		for (int i = 0; i < steps; i++)
		{
			myPos.z += negativeOrPositiveSteps;
			myMaze.setFree(myPos);
			if (myPos.equals(myMaze.getGoalPosition()))
				return false;
		}
		return false;		
	}

	/**
	 * move from the start position to the goal position by y
	 * @return boolean if goal founded
	 */
	private boolean moveByY() {
		int negativeOrPositiveSteps = -1;
		if (myPos.y == myMaze.getGoalPosition().y)
			return false;
		int steps = myPos.y - myMaze.getGoalPosition().y;
		if (steps < 0)
		{
			negativeOrPositiveSteps = 1;
			steps *= -1;
		}
		
		for (int i = 0; i < steps; i++)
		{
			myPos.y += negativeOrPositiveSteps;
			myMaze.setFree(myPos);
			if (myPos.equals(myMaze.getGoalPosition()))
				return true;
		}
		return false;
	}

	/**
	 * move from the start position to the goal position by x
	 * @return boolean if goal founded
	 */
	private boolean moveByX() {
		int negativeOrPositiveSteps = -1;
		if (myPos.x == myMaze.getGoalPosition().x)
			return false;
		int steps = myPos.x - myMaze.getGoalPosition().x;
		if (steps < 0)
		{
			negativeOrPositiveSteps = 1;
			steps *= -1;
		}
		
		for (int i = 0; i < steps; i++)
		{
			myPos.x += negativeOrPositiveSteps;
			myMaze.setFree(myPos);
			if (myPos.equals(myMaze.getGoalPosition()))
				return true;
		}
		return false;
	}

	/**
	 * Generate goal position randomly in the maze's matrix
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 * @return Position goal position
	 */
	private Position generateRandomGoalPosition(int floors, int rows, int cols) {
		Random rand = new Random();
		int zPos, yPos = 0, xPos = 0;
		zPos = rand.nextInt(floors);
		yPos = rand.nextInt(rows);
		xPos = rand.nextInt(cols);
		return new Position(zPos, yPos, xPos);
	}
	
	/**
	 * Get the number of the rows in the maze and randomize the start position
	 * the start position will be placed on the edge (example: start position presented by 'E')
	 * 1111111
	 * E000001
	 * 1111111
	 * 1111111
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
