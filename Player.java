import java.util.Scanner;

class Player{
	private Board defenseBoard = new Board();
	private Board attackBoard = new Board();
	private String name = "";
	private Fleet fleet = new Fleet();
	private Scanner kb = new Scanner (System.in);


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

	
	void fleetSetup(){
		printDefenseBoard();
		System.out.println();
		System.out.println("Fleet Action!\nPosition your fleet!");

		shipSetup("Battleship", 4); 
		Warships.clearScreen();
		printDefenseBoard();
		shipSetup("Cruiser", 3); 
		Warships.clearScreen();
		printDefenseBoard();
		shipSetup("Destroyer", 2); 
		Warships.clearScreen();

	}

	void shipSetup(String name, int length){
		int pos[] = {0,0};
		while (true){
			System.out.printf("Input %s Coordinate (Length %d)", name, length);
			try{
				pos[0] = kb.nextInt();
				pos[1] = kb.nextInt();
			}
			catch(Exception e){
				System.out.println("Invalid Input!");
				kb.nextLine();
				continue;
			}
			if (getDefenseBoard().placeShip(length,pos) == false){
				System.out.println("Cannot collide with other ships!");
				continue;
			}
			fleet.getShip(name).setPosition(pos);
			break;
		}
		
	}

}
