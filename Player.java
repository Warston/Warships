class Player{
	private Board defenseBoard = new Board();
	private Board attackBoard = new Board();
	private String name = "";
	private Fleet fleet = new Fleet();


	Player(String newName){
		defenseBoard = new Board();
		attackBoard = new Board();
		name = newName;
		fleet = new Fleet();

	}

	//getters
	
	public Board getDefenseBoard(){
		return defenseBoard;
	}

	public Board getAttackBoard(){
		return attackBoard;
	}

	public String getName(){
		return name;
	}

	public Fleet getFleet(){
		return fleet;
	}

	//setters
	
	public void setDefenseBoard(Board newBoard){
		defenseBoard = newBoard;
	}

	public void setAttackBoard(Board newBoard){
		attackBoard = newBoard;
	}

	public void setName (String newName){
		name = newName;
	}

	//other methods
	
	public void printDefenseBoard(){
		defenseBoard.printBoard();
	}

	public void printAttackBoard(){
		attackBoard.printBoard();
	}

}
