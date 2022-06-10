
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	public static void main(String[] args) {
		
		Board aBoard = new Board(5);
		ArrayList<String> previousMoves = new ArrayList<String>(); //keep track of previous moves 
		
		boolean gamePlay = true;
		Scanner in = new Scanner(System.in);
		
		while(gamePlay) {
			//display visual of board
			System.out.println(aBoard.toString());
			
			int numFilled = aBoard.getFilledCount();
			System.out.println(numFilled + " Filled Spaces Remain ");

			System.out.println("Enter 2 numbers (0-4) seperated by a space, for the row and column of your move: ");
			String move = in.nextLine();
			
			//check to see if move is in arrayList: if so gamePlay = false
			if(previousMoves.contains(move)) {
				System.out.println("YOU LOSE!!!");
				gamePlay = false;
			}else {
				previousMoves.add(move);
			}
			
			//split move string with space token
			//place into array
			String[] moveCoords = move.split(" ");
	
			//first spot in array is row value, second spot (index 1) is column value
			int row = Integer.valueOf(moveCoords[0]);
			int col = Integer.valueOf(moveCoords[1]);
			
			//call move with values in array
			aBoard.move(row, col);
			
			//check if game is won: if so gamePlay = false
			if(aBoard.isWon()) {
				System.out.println("YOU WIN!!!");
				gamePlay = false;
			}
		}
		
		in.close();
	}
}
