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
	
	public Solution() {
		this.states = new ArrayList<State<T>>();
	}

	public List<State<T>> getStates() {
		return states;
	}
	
	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}
