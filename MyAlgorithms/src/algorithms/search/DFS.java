package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
* DFS (Depth-First Search) Algorithm
* extends from CommonSearcher T
* Using generic T to present different search problems
* 
* @param T
*/

public class DFS<T> extends CommonSearcher<T> {
	
	private Searchable<T> searchable;
	private boolean goalStateFounded = false;
	private List<State<T>> visited = new ArrayList<State<T>>();
	private Solution<T> solution = new Solution<T>();
	
	/**
	 * The DFS algorithm search method
	 * @param s Searchable T, The searchable problem
	 * @return Solution T
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		this.searchable = s;
		State<T> startState = s.getStartState();
		startState.setVisited(true);
		startDFS(startState);
		return this.solution;
	}
	
	/**
	 * DFS Algorithm (private method)
	 * using recursion to get to the solution of the search problem
	 * @param currState State T
	 */
	private void startDFS(State<T> currState) {
		if (currState.equals(this.searchable.getGoalState())) {
			this.solution = backTrace(currState);
			this.goalStateFounded = true;
		}
		
		List<State<T>> neighbors = this.searchable.getAllPossibleStates(currState);
		
		for (State<T> s : neighbors) {
			if (!s.isVisited() && !this.visited.contains(s) && !this.goalStateFounded) {
				this.visited.add(s);
				s.setVisited(true);
				this.evaluatedNodes++;
				startDFS(s);
			}
		}
	}
}
