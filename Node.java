class Node{
	private int[] position = {0,0};
	private boolean occupied = false;
	private boolean hit = false;
	private String contents = " ";
	private Ship occupant = null;
	private int hullIndex = 0;

	Node(int[] pos){
		position = pos;
		occupied = false;
		hit = false;
		contents = " ";
		occupant = null;
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

	public Ship getOccupant(){
		return occupant;
	}

	public int getHullIndex(){
		return hullIndex;
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

	public void setOccupant(Ship ship){
		occupant = ship;
	}

	public void setHullIndex(int index){
		hullIndex = index;
	}
	

	

}
