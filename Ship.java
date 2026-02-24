class Ship{
	private int length = 0;
	private String name = "";
	private int position[] = {};
	private boolean horizontal = false;

	Ship(String newName, int newLength){
		name = newName;
		length = newLength;
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

	public boolean getHorizontal(){
		return horizontal;
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
}
