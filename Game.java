import java.util.Scanner;

class Game{

	private Player player1 = new Player("Player1");
	private Player player2 = new Player("Player2");
	private Scanner kb = new Scanner (System.in);

	Game(){
		fleetSetup();		
		player1.printDefenseBoard();

	}


	public Player getPlayer1(){
		return player1;
	}

	public Player getPlayer2(){
		return player2;
	}

	void fleetSetup(){
		int pos[] = {0,0};
		player1.printDefenseBoard();
		System.out.println();
		System.out.println("Fleet Action!\nPosition your fleet!");
		System.out.println("Input Battleship Coordinate");
		pos[0] = kb.nextInt();
		pos[1] = kb.nextInt();
		player1.getFleet().getShip("Battleship").setPosition(pos);
		player1.getDefenseBoard().placeShip(4,pos);

	}


}
