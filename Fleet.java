class Fleet{

	private Ship battleship = new Ship("Battleship", 4);
	private Ship cruiser = new Ship("Cruiser", 3);
	private Ship destroyer = new Ship("Destroyer", 2);

	Fleet(){
		Ship battleship = new Ship("Battleship", 4);
		Ship cruiser = new Ship("Cruiser", 3);
		Ship destoyer = new Ship("Destroyer", 2);
	}


	public Ship getShip(String shipName){
		switch (shipName){
			case "Battleship":
				return battleship;
			case "Cruiser":
				return cruiser;
			case "Destroyer":
				return destroyer;
			default:
				return null;
		}
	}

}
