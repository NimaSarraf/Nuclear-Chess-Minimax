import java.util.Scanner;

public class Master {
	int board[][] = new int[9][7];
	int maxDepth = 5;
	public static final int pawnMax = 1;
	public static final int rookMax = 2;
	public static final int knightMax = 3;
	public static final int bishopMax = 4;
	public static final int kingMax = 5;
	
	public static final int pawnMin = -1;
	public static final int rookMin = -2;
	public static final int knightMin = -3;
	public static final int bishopMin = -4;
	public static final int kingMin = -5;
	

	int winMax = 80000;
	int winMin = -80000;
	

	int [] evalValues = {-5000, -300, -300, -500, -100, 0, 100, 500, 300, 300, 5000};
	
	final int startState [][] = {
			{0, knightMax, rookMax, kingMax, rookMax, knightMax, 0},
			{0, 0, 0, bishopMax, 0, 0, 0},
			{0, 0, 0, bishopMax, 0, 0, 0},
			{pawnMax, 0, pawnMax, 0, pawnMax, 0, pawnMax},
			{0, 0, 0, 0, 0, 0, 0},
			{pawnMin, 0, pawnMin, 0, pawnMin, 0, pawnMin},
			{0, 0, 0, bishopMin, 0, 0, 0},
			{0, 0, 0, bishopMin, 0, 0, 0},
			{0, knightMin, rookMin, kingMin, rookMin, knightMin, 0}
			};
	
	public static void main(String[] args) {new Master();}
	//Initialize the game
	public Master() {
		setup();
		System.out.print("Would you like to go 1st or 2nd? (1 for first, 2 for second) ");
		Scanner kb = new Scanner(System.in);
		int first = kb.nextInt();
		printBoard();
		
		if(first == 1) {
			for(;;) {
				getAmove();
				checkGame();
				minimax();
				checkGame();
			}
		}else {
			for(;;) {
				minimax();
				checkGame();
				getAmove();
				checkGame();
			}
		}
	}
	private int checkWinner() {
		boolean cpuKing = false, playerKing = false;
		for (int i = 0; i < 9; i++){
			for(int j=0; j<7; j++) {
				if (board[i][j] == kingMin) {
					playerKing = true;
				}
				if(board[i][j] == kingMax) {
					cpuKing = true;
				}
			}
		}
		if(cpuKing && !playerKing) {
			return 99999;
		}
		else if(playerKing && !cpuKing) {
			return -99999;
		}
		return 0;
	}
	
	private void checkGame() {
		if(checkWinner() == 99999) {
			System.out.println("No one can beat me.");
			System.exit(0);
		}
		else if(checkWinner() == -99999) {
			System.out.println("You got lucky this time. You win");
			System.exit(0);
		}
	}
	
	//print the current board
	public void printBoard() {
		char piece = 0;
		int count = 9;
		System.out.println("    A  B  C  D  E  F  G" + "    (Computer)");
		System.out.println("   ---------------------");
		for (int i = 0; i < 9; i++){
			System.out.print(" " + count + "|");
			for(int j=0; j<7; j++) {
				switch(board[i][j]) {
				case 0: piece = '-'; break;
				case knightMax: piece = 'N'; break;
				case rookMax: piece = 'R'; break;
				case kingMax: piece = 'K'; break;
				case bishopMax: piece = 'B'; break;
				case pawnMax: piece = 'P'; break;
				case knightMin: piece = 'n'; break;
				case rookMin: piece = 'r'; break;
				case kingMin: piece = 'k'; break;
				case bishopMin: piece = 'b'; break;
				case pawnMin: piece = 'p'; break;
				}
				System.out.print(" " + piece + " ");
			}
			System.out.println("|" + count);
			count--;
		}
		System.out.println("   ---------------------");
		System.out.println("    A  B  C  D  E  F  G" + "     (Player)");
	}
	
	//Set the board to the start state
	private void setup() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<7; j++) {
				board[i][j] = startState[i][j];
			}
		}
	}
	
	//Get a move from the player
	private void getAmove() {
		PlayerMoves playerMoves = new PlayerMoves(board);
		int[] currentMoves;
		int moves[][] = playerMoves.getMoves();
		int count = playerMoves.getCount();
		if(count == 0) {
			System.out.println("You have no more moves, I win.");
			System.exit(0);
		}
		System.out.println("Please enter your move (Use lower case): ");
		Scanner keyboard = new Scanner(System.in);
		String humanMove;
		humanMove = keyboard.nextLine();
		currentMoves = moveConverter(humanMove);
		
		for(int i = 0; i < count; i++) {
			if((moves[i][0] == currentMoves[0] && moves[i][1] == currentMoves[1] && moves[i][2] == currentMoves[2] && moves[i][3] == currentMoves[3])) {
				board[currentMoves[2]][currentMoves[3]] = board[currentMoves[0]][currentMoves[1]];
				board[currentMoves[0]][currentMoves[1]] = 0;
				printBoard();
				return;
			}
		}
		printBoard();
		System.out.println("You have entered an illegal move, please try again.");
		getAmove();
		
	}
	
	//Convert the move from game coordinates to array coordinates.
	public int[] moveConverter(String move) {
		char a, b, c, d;
		b = move.charAt(0);
		a = move.charAt(1);
		d = move.charAt(2);
		c = move.charAt(3);
		char [] temp = {a, b, c, d};
		int [] movePos = {0, 0, 0, 0};
		for(int i=0; i<4; i++) {
			switch(temp[i]) {
				case 'a': movePos[i] = 0; break;
				case 'b': movePos[i] = 1; break;
				case 'c': movePos[i] = 2; break;
				case 'd': movePos[i] = 3; break;
				case 'e': movePos[i] = 4; break;
				case 'f': movePos[i] = 5; break;
				case 'g': movePos[i] = 6; break;
				case '1': movePos[i] = 8; break;
				case '2': movePos[i] = 7; break;
				case '3': movePos[i] = 6; break;
				case '4': movePos[i] = 5; break;
				case '5': movePos[i] = 4; break;
				case '6': movePos[i] = 3; break;
				case '7': movePos[i] = 2; break;
				case '8': movePos[i] = 1; break;
				case '9': movePos[i] = 0; break;
				
			} 
		}
		return movePos;
	}
	//computer decision
	private void minimax() {
		ComputerMoves computerMoves = new ComputerMoves(board);
		int[][]moves = computerMoves.getMoves();
		int count = computerMoves.getCount();
		int currScore;
		int depth = 0;
		int bestMin = -10000;
		int bestScore = bestMin;
		int []bestMove;
		int []currBestMove;
		int [] pieceTaken;
		int currDepth = 0;
		
		
		if(count == 0) {
			System.out.println("I am out of moves...you win");
			System.exit(0);
		} else {
			currBestMove = moves[0];
			bestMove = currBestMove;
			//Check if we can take the king in the first move
			for(int i = 0; i < count; i++) {
				if(board[moves[i][2]][moves[i][3]] == kingMin) {
					currScore = winMax;
					bestMove = moves[i];
					break;
				}
			}
				while(true) {
					for(int i = 0; i < count; i++) {
						pieceTaken = makeMove(moves[i]);
						currScore = min(depth + 1);
						if(currScore > bestScore) {
							bestScore = currScore;
							currBestMove = moves[i];
						}
						retractMove(moves[i], pieceTaken);
					}
					if(currDepth >= maxDepth) {
						break;
					}
					else {
						bestMove = currBestMove;
						currDepth++;
					}
				}
			
			makeMove(bestMove);
			System.out.println();
			System.out.println("I have made my move, your turn.");
			printBoard();		
		}
			
	}
	
	
	
		private int[] makeMove(int[]move) {
			int [] pieceTaken = new int [3];
			int movingVal = board[move[0]][move[1]];
			pieceTaken[0] = board[move[2]][move[3]];
			board[move[0]][move[1]] = 0;
			board[move[2]][move[3]] = movingVal;
			return pieceTaken;
		}

		
		
		private int min(int depth) {
			PlayerMoves playerMoves = new PlayerMoves(board);
			int moves[][] = playerMoves.getMoves();
			int count = playerMoves.getCount();
			int [] pieceTaken;
			if(count == 0) {
				evalCount++;
				return winMax - depth;
			}
			if(depth == maxDepth) {
				return evaluate();
			}
			int bestMax = 100000;
			int bestScore = bestMax;
			int curScore;
			for(int i = 0; i < count; i++) {
				pieceTaken = makeMove(moves[i]);
				//check if able to take king
				if(pieceTaken[0] == kingMax) {
					curScore = winMin + depth;
				}
				//else call max
				else {
					curScore = max(depth + 1);
				}
				retractMove(moves[i], pieceTaken);
				if(curScore < bestScore) {
					bestScore = curScore;
				}

			}
			return bestScore;
		}
		
		
		private int max(int depth) {
			ComputerMoves computerMoves = new ComputerMoves(board);
			int moves[][] = computerMoves.getMoves();
			int count = computerMoves.getCount();
			int [] pieceTaken;
			if(count == 0) {
				evalCount++;
				return winMax + depth;
			}
			if(depth == maxDepth) {
				return evaluate();
			}
			int bestMin = -100000;
			int bestScore = bestMin;
			int currScore;
			for(int i = 0; i < count; i++) {
				pieceTaken = makeMove(moves[i]);
				//check if able to take king
				if(pieceTaken[0] == kingMin) {
					currScore = winMax - depth;
				}
				else {
					currScore = min(depth + 1);
				}
				retractMove(moves[i], pieceTaken);
				if(currScore > bestScore) {
					bestScore = currScore;
				}
			}
			

			return bestScore;
		}
		//Count the score of all the pieces on the board
		private int evaluate() {
			int eval = 0;
			int curVal;
			for(int i = 8; i >= 0; i--) {
				for(int j = 0; j < 7; j++) {
					curVal = board[i][j];
					if(curVal != 0) {
						eval += evalValues[curVal + 5];
					}
				}
			}
			return eval;
		}
		
	
	//Retract temporary move.	
	private void retractMove(int [] move, int [] pieceTaken) {
		int piece = board[move[2]][move[3]];
		board[move[0]][move[1]] = piece;
		board[move[2]][move[3]] = pieceTaken[0];
	}
}
