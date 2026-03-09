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

	//Sets up the player's fleet at the beginning of the game	
	void fleetSetup(){
		printDefenseBoard();
		System.out.println();
		System.out.println("Fleet Action!\nPosition your fleet!");

		for (Ship ship : fleet.getFleetList()){
			shipSetup(ship);
			Warships.clearScreen();
			printDefenseBoard();
		}
	}

	//Sets a ship on the board
	//Checks for collision with other ships and out of bounds placements
	void shipSetup(Ship ship){
		int pos[] = {0,0};
		String name = ship.getName();
		int length = ship.getLength();
		boolean vertical = false; 
		char lineInput = ' ';
		while (true){
			System.out.printf("Input %s Coordinate (Length %d)", name, length);
			try{
				pos = UserInterface.posInput(kb);
			}
			catch(Exception e){
				System.out.println("Invalid Input!");
				continue;
			}
			System.out.printf("Enter H or V for horizontal or vertical placement.");
			try{
				lineInput = UserInterface.orientationInput();
				switch (lineInput) {
					case 'v':
					case 'V':
						vertical  = true;
						break;
					case 'h':
					case 'H':
						vertical = false;
						break;
					default:
						throw new NullPointerException("Invalid Input!");
				}
				vertical = (lineInput == 'v' || lineInput == 'V') ? true : false; 
			}
			catch(Exception e){
				System.out.println("Invalid Input!");
				continue;
			}
			if (getDefenseBoard().placeShip(length, pos, ship, vertical) == false){
				System.out.println("Cannot collide with other ships!");
				continue;
			}
			ship.setPosition(pos);
			break;
		}
		
	}

	//Executes an attack by a player to the opponent
	//Returns false if the target coordinates have already been fired at to continue; in the outer loop
	boolean attack(Player target, int[] pos){
		Node targetNode = target.getDefenseBoard().getBoard()[pos[0]][pos[1]];
		Node attackBoardNode = attackBoard.getBoard()[pos[0]][pos[1]];
		if (targetNode.getHit())
			return false;
		
		if (targetNode.getOccupied() == true){
			String targetName = targetNode.getOccupant().getName();
			System.out.printf("Strike at %d,%d hit confirmed!\n", pos[0], pos[1]);
			System.out.printf("Strike on %s confirmed!\n", targetName);
			targetNode.setContents("\u2613");
			targetNode.setHit(true);
			attackBoardNode.setContents("\u2611");
			targetNode.getOccupant().setHullAt(targetNode.getHullIndex(), true);
			if (targetNode.getOccupant().checkShipDestroyed()){
				targetNode.getOccupant().setDestroyed(true);
				System.out.printf("%s's %s has been destroyed!\n", target.getName(), targetName);
			}
		}
		else {
			System.out.printf("Strike at %d,%d missed!\n", pos[0], pos[1]);
			attackBoardNode.setContents("\u25ef");
			targetNode.setHit(true);

		}
		return true;
	}

	boolean fleetDestroyed(){
		boolean value = true;
		for (Ship ship : fleet.getFleetList()){
			if (!ship.getDestroyed()){
				value = false;
			}

		}
		if (value){
			return true;
		}
		else {
			return false;
		}
	}

}
