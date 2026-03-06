import java.util.Scanner;

class UserInterface{

	public static void printMainMenu(){
		printMenuArt();

		System.out.println("Welcome to Warships!");
		System.out.println("1) New Game");
		System.out.println("2) Continue Game In Progress");
		System.out.println("3) Multiplayer");
		System.out.println("4) Exit Game");
		System.out.println("5) Debug");

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

	//This function takes in user coordinate inputs
	//accepts either 2 integers or a single character and an integer
	//if two integers are used it uses the first as the row coordinate and the second as the column
	//if a character is used it is automatically used as the column coordinate as shown by the game board
	public static int[] posInput(Scanner kb){
		while (true){
			String lineInput = "";
			int int1;
			int int2;
			lineInput = kb.nextLine();

			if (lineInput.length() > 2){
				System.out.println("Too many characters!");
				continue;
			}
			if (lineInput.length() < 2){
				System.out.println("Too few chracters!");
				continue;
			}
			char char1 = lineInput.charAt(0);
			char char2 = lineInput.charAt(1);
			char temp = ' ';

			if (alphaCheck(char1) && alphaCheck(char2)){
				System.out.println("Can't input two letters!");
				continue;
			}

			if (alphaCheck(char1)){
				temp = char1;
				char1 = char2;
				char2 = temp;
			}

			try {
				int1 = ColHash.getHash(char1);
				int2 = ColHash.getHash(char2);
			}
			catch (Exception e){
				System.out.println("Invalid Character!");
				continue;
			}	

			int[] posReturn =  {int1, int2};
			return posReturn;
		}


	}

	//This function is used to check if a character is in the alphabet
	public static boolean alphaCheck(char char1){
		if (char1 < '\u0041')
			return false;
		if (char1 > '\u007a')
			return false;
		if (char1 > '\u005a' && char1 < '\u0061')
			return false;

		return true;
	
	}
	//This function checks if a character is a numeral
	public static boolean numCheck(char char1){
		if (char1 < '\u0030')
			return false;
		if (char1 > '\u0039')
			return false;

		return true;
	
	}

	

}
