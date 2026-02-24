class Player{
	private Board defenseBoard = new Board();
	private Board attackBoard = new Board();
	private String name = "";


	Player(String newName){
		defenseBoard = new Board();
		attackBoard = new Board();
		name = newName;
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


}
