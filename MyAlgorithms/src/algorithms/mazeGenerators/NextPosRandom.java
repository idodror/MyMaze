package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;

/**
 * This class represent the way of the Growing Tree algorithm to choose the next move randomlly
 * implements from NextPosChooser
 * (Strategy Pattern)
 */
public class NextPosRandom implements NextPosChooser {

	/**
	 * choose the next move randomly from the list of the possible moves
	 * @param possibleMoves List<Position> of the possible moves
	 * @return Position of the next move
	 */
	@Override
	public Position chooseMyNextPos(List<Position> possibleMoves) {
		Random rand = new Random();
		return possibleMoves.get(rand.nextInt(possibleMoves.size()));
	}
	
}
