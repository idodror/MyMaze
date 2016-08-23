package algorithms.mazeGenerators;

import java.util.List;

/**
 * This interface represent the way of the Growing Tree algorithm to choose the next move
 * (Strategy Pattern)
 */
public interface NextPosChooser {
	/**
	 * Get a list of possible positions and choose one of them
	 * @param possiblePositions List<Position>
	 * @return Position The next move to do
	 */
	public Position chooseMyNextPos(List<Position> possiblePositions);
}
