import java.util.HashMap;

class ColHash{

		static HashMap<Character, Integer> colMap = new HashMap<>();
		static HashMap<Integer, Character> intToCharMap = new HashMap<>();

		static{
			colMap.put('a', 0);
			colMap.put('b', 1);
			colMap.put('c', 2);
			colMap.put('d', 3);
			colMap.put('e', 4);
			colMap.put('f', 5);
			colMap.put('g', 6);
			colMap.put('h', 7);
			colMap.put('i', 8);
			colMap.put('j', 9);

			colMap.put('A', 0);
			colMap.put('B', 1);
			colMap.put('C', 2);
			colMap.put('D', 3);
			colMap.put('E', 4);
			colMap.put('F', 5);
			colMap.put('G', 6);
			colMap.put('H', 7);
			colMap.put('I', 8);
			colMap.put('J', 9);

			colMap.put('0', 0);
			colMap.put('1', 1);
			colMap.put('2', 2);
			colMap.put('3', 3);
			colMap.put('4', 4);
			colMap.put('5', 5);
			colMap.put('6', 6);
			colMap.put('7', 7);
			colMap.put('8', 8);
			colMap.put('9', 9);

			intToCharMap.put(0, 'A');
			intToCharMap.put(1, 'B');
			intToCharMap.put(2, 'C');
			intToCharMap.put(3, 'D');
			intToCharMap.put(4, 'E');
			intToCharMap.put(5, 'F');
			intToCharMap.put(6, 'G');
			intToCharMap.put(7, 'H');
			intToCharMap.put(8, 'I');
			intToCharMap.put(9, 'J');
		}

		public static int getHash(char col){
			return colMap.get(col);
		}

		public static int intToChar(int num){
			return intToCharMap.get(num);
		}



}
