package algorithms.search;

/**
 * This class represent a searcher of a searchable problem
 * @param T
 */

public interface Searcher<T> {
	/**
	 * search method
	 * gets a search problem and return the solution (if exist)
	 * @param s Searchable T, search problem
	 * @return Solution T
	 */
    public Solution<T> search(Searchable<T> s);
    
    /**
     * Get how many nodes were evaluated by the algorithm
     * @return int (number of nodes)
     */
    public int getNumberOfNodesEvaluated();
}
