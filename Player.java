import java.util.Scanner;

//Defines a player's objects like boards and fleet and the actions they can take
class Player{

	//Players have two boards both boards are not visible to the opponent
	//The defense board is where they place their ships and track hits on their ships
	//the defense board can tell the opponent if they have already attacked a target
	//
	//The attack board is a representation of the opponents defenseboard
	//it starts empty and tracks hits and misses as they come in
	private Board defenseBoard = new Board();
	private Board attackBoard = new Board();

	//each player has a name that can be input on creation of a player object
	private String name = "";

	//each player has a fleet which is made up of ships
	//the ships are defined in the fleet class and are standard for each player
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
		Warships.clearScreen();
		printDefenseBoard();
		System.out.println();
		System.out.println("Fleet Action!\nPosition your fleet!");

		for (Ship ship : fleet.getFleetList()){
			shipSetup(ship);
			Warships.clearScreen();
			printDefenseBoard();
		}
	}

	//Used to let the computer setup the defense board for the player
	void autoFleetSetup(){
		for (Ship ship : fleet.getFleetList()){
			autoShipSetup(ship);
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
				if (defenseBoard.getBoard()[pos[0]][pos[1]].getOccupied()){
					System.out.println("That spot is already occupied!");
					continue;
				}

			}
			catch(Exception e){
				System.out.println("Invalid Input!");
				continue;
			}

			//Prompts user to enter orientation of ship to be placed
			//only V or H are accepted in either upper or lower case
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
				continue;
			}
			ship.setPosition(pos);
			break;
		}
		
	}

	//Used for automatic ship placement if user chose auto
	void autoShipSetup(Ship ship){
		int pos[] = {0,0};
		String name = ship.getName();
		int length = ship.getLength();
		boolean vertical = false;
		while (true){
			pos[0] = (int) (Math.random() * (9 - length));
			pos[1] = (int) (Math.random() * (9 - length));
			if ((int) (Math.random() * 2) == 1)
				vertical = true;
			
			if (getDefenseBoard().placeShip(length, pos, ship, vertical) == false)
				continue;
			ship.setPosition(pos);
			break;
		}
	}
	
	//Executes an attack by a player to the opponent
	//Returns false if the target coordinates have already been fired at to continue; in the outer loop
	boolean attack(Player target, int[] pos){
		Node targetNode = target.getDefenseBoard().getBoard()[pos[0]][pos[1]];
		Node attackBoardNode = attackBoard.getBoard()[pos[0]][pos[1]];
		//if the node has already been hit then return false to obtain new coordinates
		if (targetNode.getHit())
			return false;
		
		//if the target node is occupied then a hit is succesful
		if (targetNode.getOccupied() == true){
			Warships.clearScreen();
			String targetName = targetNode.getOccupant().getName();
			System.out.printf("Strike at %c%d hit confirmed!\n", ColHash.intToChar(pos[1]), pos[0]);
			System.out.printf("Strike on %s length %d confirmed!\n", targetName, targetNode.getOccupant().getLength());
			targetNode.setContents("\u2613");
			targetNode.setHit(true);
			attackBoardNode.setContents("\u2611");
			targetNode.getOccupant().setHullAt(targetNode.getHullIndex(), true);
			//Checks if the ship that was hit is now destroyed
			if (targetNode.getOccupant().checkShipDestroyed()){
				targetNode.getOccupant().setDestroyed(true);
				System.out.printf("%s's %s has been destroyed!\n", target.getName(), targetName);
			}
		}
		else {
			//if the target node is not occupied it is a miss
			//misses are tracked on the attack board by changing the contents to an empty circle
			Warships.clearScreen();
			System.out.printf("Strike at %c%d missed!\n", ColHash.intToChar(pos[1]), pos[0]);
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
