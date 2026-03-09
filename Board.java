import java.util.HashMap;

class Board{
	private Node[][] board; 

	Board(){
		int posX = 0;
		int posY = 0;
		board = new Node[10][10];
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y ++) {
				board[x][y] = new Node(new int[] {posX, posY});
				posY++;
			}
			posX++;
			posY = 0;
			System.out.println();

		}
	}

	public Node[][] getBoard(){
		return board;
	}

	public void setBoard(Node[][] newBoard){
		board = newBoard;
	}

	public void printBoard(){
		int row = 0;
		System.out.print("   ");
		for (char col = 'A'; col <= 'J'; col++){
			System.out.print(col + "   ");
		}
		System.out.println();
		for (Node[] x : board) {
			System.out.print(row + " ");
			row++;
			for (Node y : x) {
				System.out.printf("[%s] ", y.getContents());
			}
			System.out.println();

		}
	}

	public boolean placeShip(int length, int[] pos,Ship ship, boolean vertical){
		//check for collision
		boolean collision = false;
		int vert = 0;
		int horiz = 0;

		if (vertical){
			vert = 1;
			horiz = 0;
		}
		else {
			vert = 0;
			horiz = 1;
		}
		if ((pos[1] * horiz) + (pos[0] * vert) + length > 10 || (pos[0] * horiz) + (pos[1] * vert) > 9){
			System.out.println("Cannot place out of bounds!");
			return false;
		}
		for(int i = 0; i < length; i++){
			if (board[pos[0] + (vert * i)][pos[1] + (horiz * i)].getOccupied() == true)
				collision = true;
		}
		if (collision == true){
			return false;
		}
		

		for(int i = 0; i < length; i++){
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setContents("\u25cf");
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setOccupied(true);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setOccupant(ship);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setHullIndex(i);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].getOccupant().setHullAt(i, false);
		}
		return true;
	
	}
}
