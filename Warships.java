import java.util.Scanner;

class Warships{
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		/*
		player1.printDefenseBoard();

		System.out.println();
		player1.getDefenseBoard().getBoard()[0][0].setContents("\u3042");

		player1.printDefenseBoard();
		*/

		int userSelection = 0;
		while(true){
			UserInterface.printMainMenu();
			userSelection = kb.nextInt();
			menuSelection(userSelection);
		}


	}

	static void menuSelection(int choice){
		clearScreen();
		switch(choice){
			case 1:
				Game gameInstance = new Game();
				break;
			case 2:
			case 3:
				System.out.println("Feature not yet implemented");
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Invalid Option");



		}
	}
	public static void clearScreen(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
