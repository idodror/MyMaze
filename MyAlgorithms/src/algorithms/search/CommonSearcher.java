package algorithms.search;

import java.util.List;

/**
* Base class of the interface Searcher T (abstract class)
* implements from Searcher T
* Using generic T to present different search problems
* 
* @param T generic
*/

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	protected int evaluatedNodes = 0;
	
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	/**
	 * This method gets the State T goalState of the search problem and backTrace it to the start
	 * @param goalState State T, the goal state of the maze
	 * @return Solution T
	 */
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		while (currState != null) {		
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		return sol;
	}
}
