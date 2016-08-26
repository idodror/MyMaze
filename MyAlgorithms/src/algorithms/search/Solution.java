package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the solution of a search problem
 * it holds a list of states which will be the solution of the searchable problem
 * toString to print the solution
 * 
 * @param T generic state value
 */

public class Solution<T> {
	
	private List<State<T>> states;
	
	/**
	 * Constructor
	 */
	public Solution() {
		this.states = new ArrayList<State<T>>();
	}

	/**
	 * @return List StateT, a list of the solution's states
	 */
	public List<State<T>> getStates() {
		return states;
	}
	
	/**
	 * Sets the states list of the solution
	 * @param states List<State<T>>
	 */
	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	/**
	 * toString override
	 * Example of the solution string: {1, 1, 0} {1, 1, 1} {1, 2, 1}...
	 * @return String, the solution
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}
