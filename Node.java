class Node{
	private int[] position = {0,0};
	private boolean occupied = false;
	private boolean hit = false;
	private String contents = " ";

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

	

}
