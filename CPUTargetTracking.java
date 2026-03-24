import java.util.List;
import java.util.ArrayList;

//This class is used for the cpu to track a ship that it has hit
class CPUTargetTracking implements java.io.Serializable {

	private Ship ship = null;
	private String shipName = " ";
	private List<int []>  hits = new ArrayList<>();
	private boolean vertical = false;
	private boolean horizontal = false;
	//atEndOfShip is needed to tell the cpu it has attacked past the end of a ship
	private boolean atEndOfShip = false;
	
	CPUTargetTracking(Ship shipHit, int[] hitPos){

		ship = shipHit;
		hits.add(hitPos);
		shipName = ship.getName();
	}





	//getters
	
	public Ship getShip(){
		return ship;
	}

	public String getShipName(){
		return shipName;
	}

	public List<int[]> getHits(){
		return hits;
	}

	public boolean getVertical(){
		return vertical;
	}

	public boolean getHorizontal(){
		return horizontal;
	}

	public boolean getEndOfShip(){
		return atEndOfShip;
	}
	//setters
	
	public void setShip(Ship newShip){
		ship = newShip;
	}

	public void setHits(List<int[]> newHits){
		hits = newHits;
	}

	public void setVertical(boolean vert){
		vertical = vert;
	}

	public void setHorizontal(boolean horiz){
		horizontal = horiz;
	}

	public void setEndOfShip(boolean eos){
		atEndOfShip = eos;
	}
	//other methods
	
	public void addHit (int[] newHits){
		hits.add(newHits);
	}


}
