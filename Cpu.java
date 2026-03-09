class Cpu extends Player {
	private int difficulty = 1;
	
	Cpu(String newName){
		super(newName);
		difficulty = 1;
	}

	void fleetSetup(){
		for (Ship ship : getFleet().getFleetList()){
			shipSetup(ship);
		}

	}

	void shipSetup(Ship ship){
		int pos[] = {0,0};
		String name = ship.getName();
		int length = ship.getLength();
		boolean vertical = false;
		while (true){
			pos[0] = (int) (Math.random() * (9 - length));
			pos[1] = (int) (Math.random() * (9 - length));
			if ((int) (Math.random() * 2) == 1)
				vertical = true;
			
			if (getDefenseBoard().placeShip(length, pos, ship, vertical) == false)
				continue;
			getFleet().getShip(name).setPosition(pos);
			break;
		}
	}


}





