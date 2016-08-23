package algorithms.mazeGenerators;

/**
 * This base class implements the Maze3dGenerator interface
 * it only define the measureAlgorithmTime method
 */
public abstract class Maze3dGeneratorBase implements Maze3dGenerator {
	
	protected Maze3d myMaze;
	
	/**
	 * Measure the algorithm's build time by checking the System time
	 * @param floors int, number of floors in the maze's matrix
	 * @param rows int, number of rows in the maze's matrix
	 * @param cols int, number of columns in the maze's matrix
	 * @return String
	 */
	@Override
	public String measureAlgorithmTime(int floors, int rows, int cols) {
		long time = System.currentTimeMillis();
		this.generate(floors, rows, cols);
		time = System.currentTimeMillis() - time;
		return String.valueOf(time);
	}
}
