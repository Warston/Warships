import java.util.List;
import java.util.ArrayList;

class Cpu extends Player {
	private int difficulty = 1;
	private List<CPUTargetTracking> hitTargets = new ArrayList<>();
	
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

	//executes an attack on the target player
	//the pos paramater is not used but is needed for polymorphism
	

	boolean attack(Player target, int[] pos){
		pos = new int[2];

		while (true) {
			pos = cpuTargeting();
			Node targetNode = target.getDefenseBoard().getBoard()[pos[0]][pos[1]];
			Node attackBoardNode = getAttackBoard().getBoard()[pos[0]][pos[1]];
			if (targetNode.getHit())
				continue;	

			if (targetNode.getOccupied() == true){
				String targetName = targetNode.getOccupant().getName();
				System.out.printf("Enemy has struck our %s at %d,%d!\n",targetName, pos[0], pos[1]);
				targetNode.setContents("\u2613");
				targetNode.setHit(true);
				attackBoardNode.setContents("\u2611");
				targetNode.getOccupant().setHullAt(targetNode.getHullIndex(), true);
				if (targetNode.getOccupant().checkShipDestroyed()){
					targetNode.getOccupant().setDestroyed(true);
					System.out.printf("%s's %s has been destroyed!\n", target.getName(), targetName);
				}
			}
			else {
				System.out.printf("Enemy's attack at %d,%d missed!\n", pos[0], pos[1]);
				attackBoardNode.setContents("\u25ef");
				targetNode.setHit(true);

			}
			return true;
		}
	}
	
	//Basic targeting that randomly selects an unhit node to strike

	public int[] cpuTargeting(){
		int pos[] = {0,0};
		pos[0] = (int) (Math.random() * (9));
		pos[1] = (int) (Math.random() * (9));
		return pos;

		
	}

	//Advanced targeting that will attempt to destroy a ship that is struck

	public int[] advancedCpuTargeting(){
		int pos[] = {0,0};
		if 

		return pos;

	}

}





