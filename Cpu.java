class Cpu extends Player {
	private int difficulty = 1;
	
	Cpu(String newName){
		super(newName);
		difficulty = 1;
	}

	void fleetSetup(){
		shipSetup("Battleship", 4); 
		shipSetup("Cruiser", 3); 
		shipSetup("Destroyer", 2); 

	}

	void shipSetup(String name, int length){
		int pos[] = {0,0};
		while (true){
			pos[0] = (int) (Math.random() * (9 - length));
			pos[1] = (int) (Math.random() * (9 - length));
			
			if (getDefenseBoard().placeShip(length,pos) == false)
				continue;
			getFleet().getShip(name).setPosition(pos);
			break;
		}
	}


}





