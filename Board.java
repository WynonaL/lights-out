
import java.util.Random;

public class Board {
	//Wynona Lam 5/11: Implementing puzzle of Lights Out

	private boolean[][] board; //true if spot is filled, false if spot is clear
	private int N; //keeps track of filled spots

	/**
	 * Construct a puzzle board by beginning with a completely empty
	 * board and then making a number of non-repeating moves.
	 * 
	 * @param moves the number of moves to make when generating the board.
	 */
	public Board(int moves) {
		//throw new RuntimeException("Not implemented");
		//maybe keep track of the moves?
		N = 0;
		board = new boolean[5][5];
		Random rand = new Random();

		for(int i = 0; i < moves; i++) {
			int randomRow = rand.nextInt(5); 
			int randomCol = rand.nextInt(5); 
			move(randomRow, randomCol);
		}
	}

	//Empty constructor
	public Board() {
		N = 0;
		board = new boolean[5][5];
	}

	/**
	 * Construct a puzzle board using a 2D array of booleans to indicate
	 * which cells are filled and which are empty.
	 * 
	 * @param b a 5x5 boolean array where true cells indicate that the
	 * corresponding position in the puzzle board starts filled and
	 * false indicates the position starts cleared.
	 */
	public Board(boolean[][] b) {
		//throw new RuntimeException("Not implemented");
		//update N
		board = new boolean[5][5];

		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[i].length; j++) {
				board[i][j] = b[i][j];
				if(b[i][j]) {
					N++;
				}
			}
		}
	}

	public static Board copy(Board other) {
		Board newDup = new Board();

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				newDup.board[i][j] = other.board[i][j];
			}
		}
		return newDup;
	}


	/**
	 * Makes a move on the board at position (row, col) on the board.
	 * This flips the state of the cell at that position from filled to
	 * clear or from clear to filled.  It also flips the state of the cell
	 * above, below, to the left, and to the right, (if they exist).
	 * 
	 * @param row the row of the cell where the move is to be made
	 * @param col the column of the cell where the move is to be made
	 */
	public void move(int row, int col) {
		entryFlip(row, col);

		if(row == 0) {
			if(col == 0) {
				entryFlip(1,0);
				entryFlip(0,1);
			}else if(col == 4) {
				entryFlip(1,4);
				entryFlip(0,3);
			}else {
				entryFlip(row+1,col);
				entryFlip(row,col-1);
				entryFlip(row,col+1);
			}
		}else if(row == 4) {
			if(col == 0) {
				entryFlip(3,0);
				entryFlip(4,1);
			}else if(col == 4) {
				entryFlip(3,4);
				entryFlip(4,3);
			}else {
				entryFlip(row-1,col);
				entryFlip(row,col-1);
				entryFlip(row,col+1);
			}
		}else {
			if(col == 0) {
				entryFlip(row-1,col);
				entryFlip(row+1,col);
				entryFlip(row,col+1);
			}else if(col == 4) {
				entryFlip(row-1,col);
				entryFlip(row+1,col);	
				entryFlip(row,col-1);
			}else {
				entryFlip(row-1,col);
				entryFlip(row+1,col);
				entryFlip(row,col-1);
				entryFlip(row,col+1);
			}
		}
	}

	/**
	 * Returns the count of the number of cells currently filled on the board.
	 * 
	 * @return the count of the number of cells currently filled on the board.
	 */
	public int getFilledCount() {
		return N;
	}

	/**
	 * Returns {@code true} if a given cell of the board is filled and
	 * {@code false} otherwise.
	 * 
	 * @param row the row of the cell being querried
	 * @param col the column  of the cell being querried
	 * @return {@code true} if the cell is filled and {@code false} otherwise.
	 */
	public boolean isFilled(int row, int col) {
		//throw new RuntimeException("Not implemented");
		return board[row][col];
	}

	//returns whether the board is cleared; true if cleared, false if not
	public boolean isWon() {
		if(N == 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() { //displays current board in the same layout as demonstration 
		String s = "";

		s += "  0 1 2 3 4 \n";
		s += " +-+-+-+-+-+ \n";

		int rowNum = 0;

		for (int row = 0; row < board.length; row++){
			s += rowNum;
			rowNum++;
			s += "|";
			for (int col = 0; col < board[0].length; col++){

				if(board[row][col]) {
					s += "*";
				}else {
					s += " ";
				}
				s += "|";
			}
			System.out.println();
			s += "\n";
			s += " +-+-+-+-+-+ \n";
		}
		return s;
	}

	//Takes any entry and flips the value: false turns into true, true turns into false
	public void entryFlip(int row, int col) {
		if(board[row][col]) {
			board[row][col] = false;
			N--;
		}else {
			board[row][col] = true;
			N++;
		}
	}

	@Override
	public int hashCode() {
		//turns boolean array to represent binary number, turns into decimal
		int result = 0;

		for(boolean[] values: board){
			for(boolean bit: values){
				result <<= 1;
				if (bit) result += 1;
			}
		}

		return result;
		
	}

	@Override 
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that == this)
			return true;
		if (that.getClass() != this.getClass())
			return false;

		Board other = (Board)that;

		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(other.board[i][j] != this.board[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}


