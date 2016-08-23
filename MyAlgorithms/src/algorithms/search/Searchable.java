package algorithms.search;

import java.util.List;

/**
 * Generic Searchable problem interface
 * to create search problem from a problem (like maze)
 * using State T object to present start/goal state, possible states and cost to move
 *
 * @param T
 */

public interface Searchable<T> {
	public State<T> getStartState();
	public State<T> getGoalState();
	
	/**
	 * Get all the possible states from a State T
	 * @param s State T
	 * @return List<State<T>>
	 */
	public List<State<T>> getAllPossibleStates(State<T> s);
	
	/**
	 * Gets two states and calculate the cost of the move from one to the other
	 * @param currState State T
	 * @param neighbor State T
	 * @return double (the cost of the move)
	 */
	public double getMoveCost(State<T> currState, State<T> neighbor);
}
