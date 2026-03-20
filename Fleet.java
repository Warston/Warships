import java.util.List;
import java.util.ArrayList;

//A fleet contains the list of ships a player controls
class Fleet{

	private List<Ship> fleet = new ArrayList<>();

	Fleet(){

		//Standard list of ships
		//add ships here if more are needed
		fleet.add(new Ship("Carrier", 5));
		fleet.add(new Ship("Battleship", 4));
		fleet.add(new Ship("Cruiser", 3));
		fleet.add(new Ship("Submarine", 3));
		fleet.add(new Ship("Destroyer", 2));
	}


	//Can be used to search for a ship in the fleet by name
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
