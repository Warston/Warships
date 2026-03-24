import java.util.Scanner;
import java.io.*;

//Warships is a game that involves placing a fleet of naval ships on a grid and attempting to destroy the opponents ships
//The game ends when all of one player's ships are destroyed
class Warships{
	public static void main(String[] args){
		

		clearScreen();
		Scanner kb = new Scanner(System.in);

		//Call functions to print main menu and take in user input
		int userSelection = 0;
		while(true){
			UserInterface.printMainMenu();
			try {
				userSelection = kb.nextInt();
			}
			catch(Exception e){
				clearScreen();
				System.out.println("Invalid input");
				System.out.println(e);
				kb.nextLine();
				continue;
			}
			menuSelection(userSelection);
		}


	}

	
	//Handles users input from menu
	static void menuSelection(int choice){
		clearScreen();
		Scanner kb = new Scanner(System.in);
		switch(choice){
			case 1:
				newGame();
				break;
			case 2:
				System.out.print("Enter file name to load: ");
				loadGame(kb.next());
				break;
			/*
			case 3:
				System.out.println("Feature not yet implemented");
				break;
			*/
			case 3:
				System.exit(0);
			default:
				System.out.println("Invalid Option");



		}
	}
	//Used throughout program to clear the terminal screen
	//Occasionally has an issue where the input ends up at the bottom of the terminal for some reason
	//Moving where you call clearScreen() can sometimes fix this issue
	public static void clearScreen(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	//Asks the user to choose a difficulty then starts a new game
	public static void newGame(){
		Scanner kb = new Scanner(System.in);
		int diffSelection = 1;
		while (true) {
			System.out.println("1(Easy) 2(Hard)");
			System.out.println("Enter Difficulty: ");
			try {
				diffSelection = kb.nextInt();
				if (diffSelection != 1 && diffSelection != 2) {
					System.out.println("Invalid input!");
					continue;
				} else
					break;
			}
			catch(Exception e){
				clearScreen();
				System.out.println("Invalid input");
				kb.nextLine();
				continue;
			}
		}

		Game gameInstance = new Game(diffSelection);
	
	}

	public static void loadGame(String fileName) {
		Game gameInstance = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			gameInstance = (Game) ois.readObject();
		}
		catch (FileNotFoundException e){
			System.out.println(e);
		}
		catch (IOException e){
			System.out.println(e);
		}
		catch (ClassNotFoundException e){
			System.out.println(e);
		}
		if (gameInstance != null)
			gameInstance.resumeGame();
	}




}
