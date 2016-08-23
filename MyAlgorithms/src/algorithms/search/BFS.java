package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * תשובות לשאלות מהנחיות הפרוייקט:
 * 1. היתרונות של כל שיטה: היתרון של BFS הוא היכולת למצוא את המסלול הטוב ביותר מההתחלה אל נקודת הסיום
 * היתרון של DFS הוא שהוא מפתח מעט מצבים בכדי להגיע לפתרון, מה שחוסך זמן ריצה
 * 2. בחרתי באופציה זו למימוש BFS לפי ההכוונה של המרצה במהלך ההרצאה
 */

/**
* BFS (Best-First Search) Algorithm
* extends from CommonSearcher<T>
* Using generic T to present different search problems
* 
* @param <T>
*/
public class BFS<T> extends CommonSearcher<T> {

	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	private Set<State<T>> closedList = new HashSet<State<T>>();
	
	/**
	 * The BFS algorithm search method
	 * @param Searchable<T> s
	 * @return Solution<T>
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		openList.add(startState);
		
		while (!openList.isEmpty()) {
			State<T> currState = openList.poll();
			closedList.add(currState);
			State<T> goalState = s.getGoalState();
			if (currState.equals(goalState)) {
				return backTrace(currState);
			}
			
			List<State<T>> neighbors = s.getAllPossibleStates(currState);
			
			for (State<T> neighbor : neighbors) {
				if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
					neighbor.setCameFrom(currState);
					neighbor.setCost(currState.getCost() + s.getMoveCost(currState, neighbor));
					openList.add(neighbor);
					this.evaluatedNodes++;
				}
				else {
					double newPathCost = currState.getCost() + s.getMoveCost(currState, neighbor);
					if (neighbor.getCost() > newPathCost) {
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(currState);
						
						if (!openList.contains(neighbor)) {
							openList.add(neighbor);
						} 
						else { // must notify the priority queue about the change of cost
							openList.remove(neighbor);
							openList.add(neighbor);
						}
					}
				}			
			}
		}
		return null;
	}

}
