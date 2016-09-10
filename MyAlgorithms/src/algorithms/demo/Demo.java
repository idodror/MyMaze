package algorithms.demo;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

/**
 * This class run a demo of the project
 */
public class Demo {
	
	/**
	 * This method create an Object adapter of maze
	 * creates 3d maze by maze generator
	 * print it
	 * solve it in each one of the algorithms (BFS and DFS)
	 * print to screen the number of evaluated nodes for each algorithm
	 */
	public static void run() {
		// parameters - the size of the maze
		Searchable<Maze3d> maze = new Maze3dDomain<>(50, 100, 100);
		
		System.out.println(maze);
		Searcher<Maze3d> bfs = new BFS<Maze3d>();
		Searcher<Maze3d> dfs = new DFS<Maze3d>();
		
		Solution<Maze3d> sol = bfs.search(maze);
		System.out.println("BFS: " + bfs.getNumberOfNodesEvaluated() + " evaluated nodes");
		System.out.println(sol);
		
		sol = dfs.search(maze);
		System.out.println("DFS: " + dfs.getNumberOfNodesEvaluated() + " evaluated nodes");
		System.out.println(sol);
	}
}
