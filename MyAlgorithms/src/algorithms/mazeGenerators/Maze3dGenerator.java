package algorithms.mazeGenerators;

/**
 * This interface sets the methods needs to be in the maze 3d algorithms which genrate new maze
 */

public interface Maze3dGenerator {
	/**
	 * Generate the maze by specific algorithm
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 * @return Maze3d
	 */
	public Maze3d generate(int floors, int rows, int cols);
	
	/**
	 * Measure the algorithm's build time
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 * @return String
	 */
	public String measureAlgorithmTime(int floors, int rows, int cols);
}
