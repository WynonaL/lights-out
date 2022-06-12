
//Wynona Lam 6/3/22: Using BFS to find shortest series of moves to solve game board.

import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;
import java.util.HashMap;

public class Solver {


	/**
	 * Construct a solver object.  How much work this constructor does is completely up to you.
	 * It is here in case you want to initialize some fields or do some pre-processing before
	 * being asked to solve a specific puzzle.  This constructor will be called once to construct
	 * your Solver object and then the solve method will be called multiple times on that
	 * Solver object.
	 */
	public Solver() {
		//run bfs  here on a empty or full graph 
		//Board start = new Board(b);
	}	

	/**
	 * Solve a given puzzle
	 * 
	 * @param b a 5-by-5 array of booleans representing the starting configuration of the puzzle.
	 * Filled in cells have value {@code true} and empty cells have value {@code false} just like
	 * the corresponding constructor of the {@code Board} class.
	 * @return an array {@code int} describing a shortest solution to the puzzle.  The row for move
	 * i is in slot 2i, and the column for move i is in slot 2i+1.  In other words, the first two
	 * slots are the row and column of the first move, the second two slots are the row and column
	 * of the second move, and so on.  An array of size 0 should be returned if the input array 
	 * represents a board that is already completely clear, and null should be returned if there
	 * is no solution to the given input board.
	 */
	 int[] solve(boolean[][] b) {
		//change so all you do backtrack to you solution here
		HashMap<Board, Board> edgeTo = new HashMap<Board, Board>();
		HashMap<Board, Boolean> marked = new HashMap<Board, Boolean>();
		HashMap<Board, String> edgeName = new HashMap<Board, String>();

		boolean[][] goalSolution = new boolean[5][5]; 
		Board solution = new Board(goalSolution);

		Board start = new Board(b);

		if(start.getFilledCount() == 0) {
			int[] ans = new int[0];
			return ans;
		}

		Queue<Board> q = new Queue<Board>();

		marked.put(start, true);
		q.enqueue(start);
		boolean breakOut = false;

		while (!q.isEmpty()) {
			Board v = q.dequeue();
			Board vDuplicate2 = Board.copy(v);

			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					vDuplicate2.move(i, j);
					if(!marked.containsKey(vDuplicate2)) {
						edgeName.put(vDuplicate2, i+ " " +j); 
						edgeTo.put(vDuplicate2, v);
						marked.put(vDuplicate2, true);
						if(vDuplicate2.equals(solution)) {
							breakOut = true;
						}
						q.enqueue(vDuplicate2);
					}
					if(breakOut) {
						break;
					}
					vDuplicate2 = Board.copy(v);
				}
				if(breakOut) {
					break;
				}
			}
			if(breakOut) {
				break;
			}
			vDuplicate2 = Board.copy(v);
		}

		ArrayList<String> answerCoords = new ArrayList<String>();

		// Lets just assume the graph and all the hashmaps have correctly been updated
		// Now backtrack to the answer using edgeTo
		if(edgeTo.containsKey(solution)){ 
			answerCoords.add(edgeName.get(solution));
			//solution is a empty board
			while(solution != start) {
				solution = edgeTo.get(solution); //repeat this move backtrack 
				if(solution != start) {
					answerCoords.add(edgeName.get(solution));
				}
			}

			int[] answer = new int[answerCoords.size()*2];
			int index = 0;

			for(int i = 0; i < answerCoords.size(); i++) {
				//split the string
				if(answerCoords.get(i) != null) {
					String[] moveCoords = answerCoords.get(i).split(" ");

					//first spot in array is row value, second spot (index 1) is column value
					int x = Integer.valueOf(moveCoords[0]);
					int y = Integer.valueOf(moveCoords[1]);

					answer[index] = x; 
					answer[index+1] = y;
					index+=2;
				}
			}
			
			return answer;
			
		}else {
			return null;
		}
	}
}





