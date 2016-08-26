package algorithms.search;

import java.util.ArrayList;
import java.util.List;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;

/**
 * Object adapter which adapt Maze3d to a search problem
 * implements from Searchable T
 * @param T
 */

public class Maze3dDomain<T> implements Searchable<T> {

	private Maze3d maze3d;
	
	/**
	 * Constructor
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 */
	public Maze3dDomain(int floors, int rows, int cols) {
		Maze3dGenerator gen = new GrowingTreeGenerator();
		this.maze3d = gen.generate(floors, rows, cols);
	}
	
	/**
	 * @return Maze3d, the maze object
	 */
	public Maze3d getMaze3d() {
		return maze3d;
	}

	@Override
	public State<T> getStartState() {
		State<T> startState = new State<T>((T)this.maze3d.getStartPosition(), null);
		return startState;
	}
	
	@Override
	public State<T> getGoalState() {
		State<T> goalState = new State<T>((T) this.maze3d.getGoalPosition(), null);
		return goalState;
	}

	@Override
	public List<State<T>> getAllPossibleStates(State<T> s) {
		Position pos;
		String[] possibleMovesAsStrings = this.maze3d.getPossibleMoves((Position)s.getValue());
		List<State<T>> possibleStates = new ArrayList<State<T>>();
		for (int i = 0; i < possibleMovesAsStrings.length; i++) {
			pos = convertStringToPosition(possibleMovesAsStrings[i]);
			if (!maze3d.isWall(pos))
				possibleStates.add(new State<T>((T)pos, s));
		}
		return possibleStates;
	}

	@Override
	public double getMoveCost(State<T> currState, State<T> neighbor) {
		return currState.getCost() + neighbor.getCost();
	}
	
	/**
	 * Gets a Position in the maze presented by string and convert it to Position Object
	 * @param strPos String
	 * @return Position
	 */
	private Position convertStringToPosition(String strPos) {
		Position pos;
		strPos = strPos.substring(1, strPos.length()-1);
		String[] strInt = strPos.split(", ");
		pos = new Position(Integer.parseInt(strInt[0]), Integer.parseInt(strInt[1]), Integer.parseInt(strInt[2]));
		return pos;
	}
	
	/**
	 * toString which calls the Maze3d Object toString
	 */
	@Override
	public String toString() {
		return this.maze3d.toString();
	}

}
