import java.util.List;
import java.util.ArrayList;

class Fleet{

	private List<Ship> fleet = new ArrayList<>();

	Fleet(){

		fleet.add(new Ship("Battleship", 4));
		fleet.add(new Ship("Cruiser", 3));
		fleet.add(new Ship("Destroyer", 2));
	}


	public Ship getShip(String shipName){
		for (Ship s : fleet){
			if (s.getName().equals(shipName)){
				return s;
			}
		}
		System.out.println("Error! Ship not found!");
		return null;

	}

	
	public List<Ship> getFleetList(){
		return fleet;
	}
}
