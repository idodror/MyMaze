package algorithms.search;

/**
 * This class represent a generic state in a search problem
 * Uses T as your state (like Position class)
 * implements Comparable to override equals to compare 2 generic T states
 *
 * @param T generic State value
 */

public class State<T> implements Comparable<State<T>> {
	private State<T> cameFrom;
	private double cost;
	private T value;
	private String key;
	private boolean visited;

	/**
	 * Constructor
	 * @param value T 
	 * @param cameFrom State T
	 */
	public State(T value, State<T> cameFrom) {
		this.value = value;
		this.visited = false;
		this.cost = 1;
		this.cameFrom = cameFrom;
	}
	
	/**
	 * Constructor
	 * @param key String
	 */
	public State(String key) {
		this.key = key;
	}
	
	/**
	 * returns the status of the State (visited or not)
	 * @return boolean true if visited, false if not
	 */
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	/**
	 * Check if the State's values are equal (T)
	 * @return boolean true if equals, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		State<T> s = (State<T>)obj;
		return s.value.equals(this.value);
	}
	
	/**
	 * return bigger than 0 if this bigger than s
	 * smaller than 0 if this smaller than s
     * = 0 if this == s
     * @param s State T
     * @return int the compare cost
	 */
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	
	}	
}
