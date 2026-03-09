class Ship{
	private int length = 0;
	private String name = "";
	//position starts from the left most or top most coordinate depending on vert/horizontal
	private int position[] = {};
	private boolean vertical = false;
	private boolean destroyed = false;
	//A value of true means that section of the hull is destroyed
	private boolean hull[] = {};

	Ship(String newName, int newLength){
		name = newName;
		length = newLength;
		hull = new boolean[length];
	}

	//getters
	
	public int getLength(){
		return length;
	}

	public String getName(){
		return name;
	}

	public int[] getPosition(){
		return position;
	}

	public boolean getVertical(){
		return vertical;
	}

	public boolean getDestroyed(){
		return destroyed;
	}

	public boolean[] getHull(){
		return hull;
	}

	public boolean getHullAt(int index){
		return hull[index];
	}

	//setters
	
	public void setLength(int newLength){
		length = newLength;
	}

	public void setName(String newName){
		name = newName;
	}

	public void setPosition(int[] newPosition){
		position = newPosition;
	}

	public void setDestroyed(boolean value){
		destroyed = value;
	}

	public void setHull(boolean[] newHull){
		hull = newHull;
	}

	public void setHullAt(int index, boolean value){
		hull[index] = value;
	}

	//other methods
	
	public boolean checkShipDestroyed(){
		int count = 0;
		for (int i = 0; i < length; i++){
			if (hull[i] == true){
				count++;
			}
		}
		if (count == length){
			return true;
		}
		else {
			return false;
		}
	}
}
