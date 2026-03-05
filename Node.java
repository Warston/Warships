class Node{
	private int[] position = {0,0};
	private boolean occupied = false;
	private boolean hit = false;
	private String contents = " ";
	private String occupant = "";

	Node(int[] pos){
		position = pos;
		occupied = false;
		hit = false;
		contents = " ";
	}


	public String getContents(){
		return contents;
	}

	public int[] getPosition(){
		return position;
	}

	public boolean getOccupied(){
		return occupied;
	}

	public boolean getHit(){
		return hit;
	}

	public String getOccupant(){
		return occupant;
	}

	public void setContents(String newContents){
		contents = newContents;
	}

	public void setPosition (int[] newPosition){
		position = newPosition;
	}

	public void setOccupied(boolean newOccupied){
		occupied = newOccupied;
	}

	public void setHit(boolean newHit){
		hit = newHit;
	}

	public void setOccupant(String ship){
		occupant = ship;
	}

	

}
