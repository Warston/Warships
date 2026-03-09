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
		while(true){
			System.out.print("Enter coordinates: ");
			pos = UserInterface.posInput(kb);
			//attempts to execute attack if the player input non duplicate coordinates
			if (!player1.attack(player2, pos)){
				System.out.println("Cannot strike the same coordinate twice!");
				continue;
			}
			
			if (player2.fleetDestroyed()){
				System.out.println("Game Over! Player 1 Wins!\n");
				break;
			}
			
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
		Warships.clearScreen();
	}

	boolean checkFleetDeath(Fleet playerFleet){
		return false;

	}


}
