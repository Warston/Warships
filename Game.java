import java.util.Scanner;

class Game{

	private Player player1 = new Player("Player1");
	private Player player2 = new Player("Player2");
	private Scanner kb = new Scanner (System.in);

	Game(){
		player1 = new Player("Player1");
		player2 = new Cpu("CPU");
		player1.fleetSetup();		
		player1.printDefenseBoard();
		System.out.println();
		player2.fleetSetup();
		System.out.println("CPU Board");
		player2.printDefenseBoard();

		gameOver();
	}


	public Player getPlayer1(){
		return player1;
	}

	public Player getPlayer2(){
		return player2;
	}


	void gameOver(){
		System.out.println("Game Over!");
		System.out.println("Press any key to continue");
		kb.nextLine();
		kb.nextLine();
		Warships.clearScreen();
	}


}
