//The node class defines the spaces on each board
class Node{
	//Position is its location on the board for easy reference by other classes
	private int[] position = {0,0};
	
	//Occupied is set to true if part of a ship is located at this node
	private boolean occupied = false;

	//hit is set to true if this node has been attacked whether it was a hit or miss
	private boolean hit = false;

	//contents is what is displayed to the user. by default it is a space to display an empty node
	private String contents = " ";
	
	//Occupant stores the ship that is currently occupying the node
	//used to easily tell other classes what ship is at this location
	private Ship occupant = null;

	//Hull index tracks which section of hull of the occupying ship is in this node
	//this is used to check if the ship is destroyed
	private int hullIndex = 0;

	Node(int[] pos){
		position = pos;
		occupied = false;
		hit = false;
		contents = " ";
		occupant = null;
	}

	//getters

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

	//setters
	
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
