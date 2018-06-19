public class ComputerMoves {
	int[][] board;
	int[][] computerMoves = new int[100][4];
	int count = 0;
	
	
	public static final int pawnMax = 1;
	public static final int rookMax = 2;
	public static final int knightMax = 3;
	public static final int bishopMax = 4;
	public static final int kingMax = 5;
	
	
	public ComputerMoves(int[][]b) {
		board = b;
		for (int i = 0; i < 9; i++){
			for(int j = 0; j < 7; j++) {
				if(board[i][j] > 0) {
					switch(board[i][j]) {
					case knightMax: 
						playerKnightMoves(i, j, kingMax);
						break;
					case bishopMax: 
						playerBishopMoves(i, j, kingMax);
						break;
					case pawnMax: 
						playerPawnMoves(i, j, kingMax);
						break;
					case rookMax: 
						playerRookMoves(i, j, kingMax);
						break;
					}
				}
			}
		}
	}
	public boolean moveInBounds(int r, int c) {
		return c >= 0 && c < 7 && r >= 0 && r < 9;
	}
	
	public int[][] getMoves(){
		return computerMoves;
	}
	public int getCount() {
		return count;
	}
	//move the Knight
	private void playerKnightMoves(int r, int c, int king) {
		//(+2, +1) (+2, -1) (-2, +1) (-2, -1)(+1, +2) (+1, -2) (-1, +2) (-1, -2)

		//(+2, +1)
		int to_r = r + 2, to_c = c + 1;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(+2, -1)
		to_c = c - 1;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(-2, +1)
		to_r = r - 2; to_c = c + 1;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] < 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(-2, -1)
		to_r = r - 2; to_c = c - 1;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] < 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(+1, +2)
		to_r = r + 1; to_c = c + 2;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(+1, -2)
		to_r = r + 1; to_c = c - 2;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(-1, +2)
		to_r = r - 1; to_c = c + 2;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] < 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
		//(-1, -2)
		to_r = r - 1; to_c = c - 2;
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] < 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = to_c;
			count++;
		}
	}
	public void playerBishopMoves(int r, int c, int king){
		//(+1, +1), (+1, -1), (-1, +1), (-1, -1)
		int to_r = r + 1, to_c = c + 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] <= 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
			}
			if(checkPosition != 0) {
				break;
			}
			else {
				to_c++;
				to_r++;
			}
		}
		//(-1, +1)
		to_r = r - 1; to_c = c + 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
				break;
			}
			else {
				to_r--;
				to_c++;
			}
		}
		//(-1, -1)
		to_c = c - 1; to_r = r - 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
				break;
			}
			else {
				to_c--;
				to_r--;
			}
		}
		//(+1, -1)
		to_r = r + 1; to_c = c - 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] <= 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
				break;
			}
			
			else {
				to_c--;
				to_r++;
			}
		}
		
	}
	public void playerRookMoves(int r, int c, int king) {
		//(+1, 0) (0, +1) (-1, 0) (0, -1)
		
		//(+1, 0)
		int to_r = r + 1, to_c = c;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] <= 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
			}
			if(checkPosition != 0) {
				break;
			}
			else {
				to_r--;
			}
		}
		//(-1, 0)
		to_r = r - 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
				break;
			}
			if(checkPosition != 0) {
				break;
			}
			else {
				to_r++;
			}
		}

		//(0, +1)
		to_r = r; to_c = c + 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
				break;
			}
			if(checkPosition != 0) {
				break;
			}
			else {
				to_c++;
			}
		}
		//(0, -1)
		to_c = c - 1;
		while(moveInBounds(to_r, to_c)) {
			int checkPosition = board[to_r][to_c];
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
				break;
			}
			if(checkPosition != 0) {
				break;
			}
			else {
				to_c--;
			}
		}
	}
	
	public void playerPawnMoves(int r, int c, int king) {
		int to_r = r + 1, to_c = c + 1;
		
		//(+1, 0)
		if(moveInBounds(to_r, c) && board[to_r][c] == 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = c;
			count++;
		}
		//Moving right diagonal to capture a piece
		if(moveInBounds(to_r, to_c)) {
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
			}
		}
		to_c = c - 1;
		//(Moving left diagonal to capture a piece)
		if(moveInBounds(to_r, to_c)) {
			if(board[to_r][to_c] < 0) {
				computerMoves[count][0] = r;
				computerMoves[count][1] = c;
				computerMoves[count][2] = to_r;
				computerMoves[count][3] = to_c;
				count++;
			}
		}
	}
	
	
	public void playerKingMove(int r, int c) {
		int to_r = r + 1, to_c = c + 1;
		//(+1, 0)
		if(moveInBounds(to_r, c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = c;
			count++;
		}
		//(+1, +1)
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = c+1;
			count++;
		}
		to_c = c - 1;
		//(+1, -1)
		if(moveInBounds(to_r, to_c) && board[to_r][to_c] <= 0) {
			computerMoves[count][0] = r;
			computerMoves[count][1] = c;
			computerMoves[count][2] = to_r;
			computerMoves[count][3] = c-1;
			count++;
		}
	}
}