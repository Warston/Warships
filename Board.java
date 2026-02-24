class Board{
	private Node[][] board; 

	Board(){
		int posX = 0;
		int posY = 0;
		board = new Node[10][10];
		for (Node[] x : board) {
			for (Node y : x) {
				y = new Node(new int[] {posX, posY});
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
		for (Node[] x : board) {
			for (Node y : x) {
				System.out.printf("[%s] ", y.getContents());
			}
			System.out.println();

		}
	}
}
