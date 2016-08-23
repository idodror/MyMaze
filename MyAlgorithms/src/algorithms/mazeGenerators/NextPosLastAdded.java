package algorithms.mazeGenerators;

import java.util.List;

/**
 * This class represent the way of the Growing Tree algorithm to choose the next move by the last one added to the list
 * implements from NextPosChooser
 * (Strategy Pattern)
 */
public class NextPosLastAdded implements NextPosChooser {

	/**
	 * choose the next move by the last one added to the list
	 * @param possibleMoves List<Position> of the possible moves
	 * @return Position of the next move
	 */
	@Override
	public Position chooseMyNextPos(List<Position> possibleMoves) {
		return possibleMoves.get(possibleMoves.size()-1);
	}

}
