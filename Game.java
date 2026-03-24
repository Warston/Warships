import java.util.Scanner;
import java.io.*;

class Game implements java.io.Serializable{

	private Player player1 = new Player("Player1");
	private Player player2 = new Player("Player2");
	private transient Scanner kb = new Scanner (System.in);

	//Left the no parameter constructor incase it is needed to start an easy difficulty game
	Game(){
		gameStart(1);
		gameLoop();
	}


	//Default method for creating a new game. takes in a specified difficulty
	Game(int difficulty){
		gameStart(difficulty);
		gameLoop();
	}

	public Player getPlayer1(){
		return player1;
	}

	public Player getPlayer2(){
		return player2;
	}

	//Starts the game creates new players
	//Asks the user if they want to place ships manually or automatically
	//calls appropriate fleet setup methods from the player class
	void gameStart(int difficulty){
		player1 = new Player("Player1");
		player2 = new Cpu("CPU", difficulty);
		Warships.clearScreen();
		while(true){
			int choice = 0;
			System.out.println("Auto or Manual Ship Placement?");
			System.out.println("1) Manual\n2) Auto");
			try {
				choice = kb.nextInt();
			} 
			catch (Exception e){
				System.out.println("Invalid Input");
				kb.nextLine();
				continue;
			}
			kb.nextLine();

			if (choice == 1)
				player1.fleetSetup();		
			if (choice == 2)
				player1.autoFleetSetup();
			if (choice != 1 && choice != 2){
				System.out.println("Invalid Option");
				continue;
			}
			else
				break;
		}
		player1.printDefenseBoard();
		System.out.println();
		player2.fleetSetup();
		System.out.println("Sortie underway! Select your target!");
	}

	//Main game loop. Prints out attack and defense boards and takes turns between players attack
	void gameLoop(){
		Scanner kb = new Scanner(System.in);
		boolean quit = false;
		int[] pos = {0,0};
		Warships.clearScreen();
		while(!quit){


				System.out.println("Attack Board");
				player1.printAttackBoard();
				System.out.println("Defense Board");
				player1.printDefenseBoard();
			switch (UserInterface.inGameAction()){
				case 1:
					Warships.clearScreen();
					//These statements can be uncommented to display enemy attack boards for debugging
					//System.out.println("*DEBUG* Attack CPU Board");
					//player2.printAttackBoard();
					//System.out.println("*DEBUG*Defense CPU Board");
					//player2.printDefenseBoard();
					System.out.println("Attack Board");
					player1.printAttackBoard();
					System.out.println("Defense Board");
					player1.printDefenseBoard();


					System.out.print("Enter coordinates: ");
					pos = UserInterface.posInput();
					//attempts to execute attack if the player input non duplicate coordinates
					if (!player1.attack(player2, pos)){
						Warships.clearScreen();
						System.out.println("Cannot strike the same coordinate twice!");
						continue;
					}
					
					// checks if player2's fleet is destroyed and ends the game if true
					if (player2.fleetDestroyed()){
						gameOver(player1.getName());
						break;
					}
					
					// cpu finds a target and attacks
					player2.attack(player1, pos);
					
					//checks if player1's fleet is destroyed and ends the game if true
					if (player1.fleetDestroyed()){
						gameOver(player2.getName());
						break;
					}
				
			

					break;
				case 2:
					System.out.println("Input filename: ");
					saveGame(kb.next());
					System.exit(0);
					break;
				case 3:
					quit = true;
					Warships.clearScreen();
					break;
				default:
					Warships.clearScreen();
					System.out.println("Invalid Choice");
					continue;

			
			}
		
		}
	}

	//Displays all boards at end of game
	//displays a game over message and waits for user input before continuing back to main menu
	void gameOver(String playerName){
		System.out.println("CPU's Attack Board");
		player2.printAttackBoard();
		System.out.println("CPU's Defense Board");
		player2.printDefenseBoard();
		System.out.println("Attack Board");
		player1.printAttackBoard();
		System.out.println("Defense Board");
		player1.printDefenseBoard();
		
		//Displays winner
		System.out.printf("Game Over! %s wins!\n", playerName);
		System.out.println("Press any key to continue");
		kb.nextLine();
		Warships.clearScreen();
	}

	void saveGame(String saveName){
		try {
			FileOutputStream fos = new FileOutputStream(saveName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this);
			oos.close();
		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public void resumeGame(){
		gameLoop();
	}




}
