package backtracking;
import javax.swing.JOptionPane;

public class NQueenSolver {

	public int solutionCounter = 0;
	
	public int[][] board;
	
//	public int[][] board = {
//	{0,0,0,0,0,0,0,0},
//	{0,0,0,0,0,0,0,0},
//	{0,0,0,0,0,1,0,0},
//	{0,0,0,0,0,0,0,0},
//	{0,0,0,0,0,0,1,0},
//	{0,0,0,0,0,0,0,0},
//	{0,0,0,0,0,0,0,0},
//	{0,0,0,0,0,0,0,0}
//};
	
	public int numOfQueens;
	
	public int queensCounter = 0;
	
	public NQueenSolver(int numOfQueens, int x, int y){
		this.numOfQueens = numOfQueens;
		buildBoard(x, y);
	}
	
	public boolean solve(int a){
		for(int i=a; i<board.length; i++){
			for(int j=0; j<board[0].length; j++){
				if(board[i][j] == 0){
					if(isPossible(i, j)){
						board[i][j] = 1;
						queensCounter++;
						if(solve(i+1)){
							return true;
						}else{
							board[i][j] = 0;
							queensCounter--;
						}
					}
				}
			}
		}
		if(numOfQueens != queensCounter){
			return false;
		}
		
		
		

		// Display Solution
		solutionCounter++;
		System.out.println("Solution #"+solutionCounter+":\n");
		printBoard();

		// Search for further solution
		int reply = JOptionPane.showConfirmDialog(null, "Search for further solutions?", "N Queens Solver", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			System.out.println("Searching for further solutions...");
			return false;
		}

		return true;
	}
	
	public boolean isPossible(int row, int col){
		// Check for row
		for(int j=0; j<board[row].length; j++){
			if(board[row][j] != 0){
				return false;
			}
		}
		
		// Check for column
		for(int i=0; i<board.length; i++){
			if(board[i][col] != 0){
				return false;
			}
		}
		
		// Check for diagonal "\"
		int minVal = Math.min(row, col);
		int startRow = row - minVal;
		int startCol = col - minVal;
		int numOfSteps = Math.max(startRow, startCol);
		
		for(int n=0; n<board.length-numOfSteps; n++){
			if(board[startRow+n][startCol+n] != 0){
				return false;
			}
		}
		
		// Check for diagonal "/"
		int distToUpperBoarder = row;
		int distToRightBoarder = board[0].length - col - 1;
		minVal = Math.min(distToUpperBoarder, distToRightBoarder);
		startRow = row - minVal;
		startCol = col + minVal;
		int distToLeftBoarder = startCol;
		int distToBottomBoarder = board.length - startRow - 1;
		numOfSteps = Math.min(distToLeftBoarder, distToBottomBoarder);
		
		for(int n=0; n<=numOfSteps; n++){
			if(board[startRow+n][startCol-n] != 0){
				return false;
			}
		}
		
		return true;
	}
	
	public void buildBoard(int x, int y){
		board = new int[x][y];
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				board[i][j] = 0;
			}
		}
	}
	
	public void printBoard(){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[0].length; j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		int nQ = 8;
		int dimX = 8;
		int dimY = 8;
		NQueenSolver solver = new NQueenSolver(nQ, dimX, dimY);
		System.out.println("Solving the problem of putting "+nQ+" queens onto a "+dimX+" x "+dimY+" chessboard, without attacking each other.");

		if(!solver.solve(0)){
			System.out.println("No solution could be found!");		
		}
	}

}
