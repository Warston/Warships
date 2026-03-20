import java.util.HashMap;
//Defines boards
//Boards are a collection of nodes that make up the boards the players control
class Board{
	//A board is a 2 dimensional array of Nodes
	private Node[][] board; 

	//Constructor creates a new board and intializes all nodes to default values
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

	//getters
	
	public Node[][] getBoard(){
		return board;
	}

	//setters

	public void setBoard(Node[][] newBoard){
		board = newBoard;
	}

	//other methods

	//prints out the board to the terminal
	//the columns are specified manually
	public void printBoard(){
		int row = 0;
		System.out.print("   ");
		for (char col = 'A'; col <= 'J'; col++){
			System.out.print(col + "  ");
		}
		System.out.println();
		for (Node[] x : board) {
			System.out.print(row + " ");
			row++;
			for (Node y : x) {
				System.out.printf("[%s]", y.getContents());
			}
			System.out.println();

		}
	}

	//Used to place a ship on the board during fleet setup stage
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

		//Messy code
		//uses vert and horiz to cancel out certain values
		//this lets ships be placed vertically or horizontally
		if ((pos[1] * horiz) + (pos[0] * vert) + length > 10 || (pos[0] * horiz) + (pos[1] * vert) > 9){
			System.out.println("Cannot place out of bounds!");
			return false;
		}
		for(int i = 0; i < length; i++){
			if (board[pos[0] + (vert * i)][pos[1] + (horiz * i)].getOccupied() == true)
				collision = true;
		}
		if (collision == true){
			System.out.println("Cannot collide with other ships!");
			return false;
		}
		
		String contentSymbol = "\u25cf";

		for(int i = 0; i < length; i++){
			if (vertical == true) {
				if (i == 0)
					contentSymbol = "\u25b2";

				if (i > 0 && i < length - 1)
					contentSymbol = "\u25a0";

				if (i == length - 1)	
					contentSymbol = "\u25bc";				
			}

			if (vertical == false) {
				if (i == 0)
					contentSymbol = "\u25c0";

				if (i > 0 && i < length - 1)
					contentSymbol = "\u25a0";

				if (i == length - 1)
					contentSymbol = "\u25b6";				
			}

			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setContents(contentSymbol);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setOccupied(true);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setOccupant(ship);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].setHullIndex(i);
			board[pos[0] + (vert * i)][pos[1] + (horiz * i)].getOccupant().setHullAt(i, false);
		}
		return true;
	
	}
}
