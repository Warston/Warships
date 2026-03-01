import java.util.Scanner;

class Game{

	private Player player1 = new Player("Player1");
	private Player player2 = new Player("Player2");
	private Scanner kb = new Scanner (System.in);

	Game(){
		gameStart();
		gameLoop();

		gameOver();
	}


	public Player getPlayer1(){
		return player1;
	}

	public Player getPlayer2(){
		return player2;
	}

	void gameStart(){
		player1 = new Player("Player1");
		player2 = new Cpu("CPU");
		player1.fleetSetup();		
		player1.printDefenseBoard();
		System.out.println();
		player2.fleetSetup();
		System.out.println("Sortie underway! Select your target!");
	}

	void gameLoop(){
		int[] pos = {0,0};
		for(int i = 1; i <= 3; i++){
			System.out.print("Enter coordinates: ");
			pos[0] = kb.nextInt();
			pos[1] = kb.nextInt();
			player1.attack(player2, pos);		
			System.out.println("*DEBUG*CPU Board");
			player2.printDefenseBoard();
			System.out.println("*DEBUG*Attack Board");
			player1.printAttackBoard();
		}
		
	}

	void gameOver(){
		System.out.println("Game Over!");
		System.out.println("Press any key to continue");
		kb.nextLine();
		kb.nextLine();
		Warships.clearScreen();
	}


}
