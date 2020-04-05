package backtracking;
import javax.swing.*;

public class Sudoku {
	
	public int solutionCounter = 0;
	
// Multiple Solutions:	
	
//	public int[][] puzzle = {
//			{0,0,0,  0,0,0,  0,0,0},
//			{0,0,0,  0,0,0,  0,0,0},
//			{0,0,0,  0,0,0,  0,0,0},
//			
//			{0,0,0,  0,0,0,  0,0,0},
//			{0,0,0,  0,0,0,  0,0,0},
//			{0,0,0,  0,0,0,  0,0,0},
//			
//			{0,0,0,  0,0,0,  0,0,0},
//			{0,0,0,  0,0,0,  0,0,0},
//			{0,0,0,  0,0,0,  0,0,0}
//	};
	
//	public int[][] puzzle = {
//			{0,0,0,  5,0,0,  0,0,6},
//			{0,8,0,  7,0,0,  0,0,0},
//			{7,0,0,  0,4,2,  0,8,5},
//			
//			{0,0,0,  6,0,0,  5,0,0},
//			{0,9,4,  0,0,0,  8,7,0},
//			{0,0,5,  0,0,8,  0,0,0},
//			
//			{1,2,0,  4,0,0,  0,0,8},
//			{0,0,0,  0,0,9,  0,3,0},
//			{9,0,0,  0,0,1,  0,0,0}
//	};
	
// Only one solution:	
	
	public int[][] puzzle = {
			{0,0,0,  5,0,0,  0,0,6},
			{0,8,0,  7,0,0,  0,0,0},
			{7,0,0,  0,4,2,  0,8,5},
			
			{0,0,0,  6,0,0,  5,0,0},
			{0,9,4,  0,0,0,  8,7,0},
			{0,0,5,  0,0,8,  0,0,0},
			
			{1,2,0,  4,5,0,  0,0,8},
			{0,0,0,  0,0,9,  0,3,0},
			{9,0,0,  0,0,1,  0,0,0}
	};	

//	public int[][] puzzle = {
//			{6,0,0,  8,2,9,  0,0,1},
//			{0,0,8,  0,4,0,  9,0,0},
//			{0,0,0,  1,0,5,  0,0,0},
//
//			{0,0,7,  4,0,2,  1,0,0},
//			{3,0,5,  0,0,0,  4,0,8},
//			{0,0,1,  9,0,8,  5,0,0},
//			
//			{0,0,0,  3,0,7,  0,0,0},
//			{0,0,9,  0,6,0,  8,0,0},
//			{1,0,0,  2,8,4,  0,0,6}
//	};


	/**
	 * Solves a sudoku puzzle with a recursive backtracking algorithm.
	 * @return True, if it is possible to solve the puzzle. False, if not.
	 */
	public boolean solvePuzzle(){	
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(puzzle[i][j]==0){
					for(int n=1; n<10; n++){
						if(isPossible(i, j, n)){
							puzzle[i][j] = n;
							if(solvePuzzle()){
								return true;
							}else{
								puzzle[i][j] = 0;
							}
						}
					}
					return false;
				}
			}
		}

		// Display Solution
		solutionCounter++;
		System.out.println("Solution #"+solutionCounter+":\n");
		printPuzzle();
		
		// Search for further solution
		int reply = JOptionPane.showConfirmDialog(null, "Search for further solutions?", "Sudoku Solver", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			System.out.println("Searching for further solutions...");
			return false;
		}
		return true;
	}


	/**
	 * Method that checks if it is possible to fill in a number in a specific column and row of the puzzle.
	 * @param row - Row where the number will be placed.
	 * @param col - Column where the number will be placed.
	 * @param number - The number that is going to be entered.
	 * @param puzzle - The puzzle that is going to be checked.
	 * @return True if it is possible. False if not.
	 */
	public boolean isPossible(int row, int col, int number){
		// Check for row
		for(int j=0; j<9; j++){
			if(puzzle[row][j]==number){
				return false;
			}
		}
		// Check for column
		for(int i=0; i<9; i++){
			if(puzzle[i][col]==number){
				return false;
			}
		}
		// Check for sector
		int startRow = (row/3)*3;
		int startCol = (col/3)*3;

		for(int i=startRow; i<startRow+3; i++){
			for(int j=startCol; j<startCol+3; j++){
				if(puzzle[i][j]==number){
					return false;
				}
			}
		}	
		// The same number was not found so it is possible
		return true;
	}

	/**
	 * Prints out the sudoku puzzle in human readable form.
	 * @param puzzle - The sudoku puzzle, which is going to be printed.
	 */
	public void printPuzzle(){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(puzzle[i][j]==0){
					System.out.print("   ");
				}else{
					System.out.print(puzzle[i][j]+"  ");
				}
				if(j==2 || j==5){
					System.out.print("|  ");
				}
				if(j==8){
					if(i==2 || i==5){
						System.out.println("\n"+"---------|-----------|---------");
					}else if(i<8){
						System.out.println("\n"+"         |           |         ");
					}
				}
			}
		}
		System.out.println("\n"+"\n");
	}
	
	
	public static void main(String[] args) {

		Sudoku sudoku = new Sudoku();		
		sudoku.printPuzzle();		
		if(sudoku.solvePuzzle()==false){
			System.out.println("No solution found!");
		}
		
	}
}


