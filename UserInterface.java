import java.util.Scanner;

class UserInterface{

	public static void printMainMenu(){
		printMenuArt();

		System.out.println("Welcome to Warships!");
		System.out.println("1) New Game");
		System.out.println("2) Continue Game In Progress");
		System.out.println("3) Multiplayer");
		System.out.println("4) Exit Game");
	}

	static void printMenuArt() {
		String art = """
			             |__
                                     |\\/
                                     ---
                                     / | [
                              !      | |||
                            _/|     _/|-++'
                        +  +--|    |--|--|_ |-
                     { /|__|  |/\\__|  |--- |||__/
                    +---------------___[}-_===_.'____                 /\\
                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _
 __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7
|                                                                     BB-61/
 \\_________________________________________________________________________|
  Art Credit: Matthew Bace
""";

		System.out.println(art);
	}

	public static int[] posInput(Scanner kb){
		String lineInput = "";
		kb.nextLine();

		if (lineInput.length() > 2){
			System.out.println("Too many characters!");
			continue;
		}


	}

	

}
