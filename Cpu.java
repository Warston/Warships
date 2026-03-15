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
			if (hitTargets.size() == 0) {
				pos = cpuTargeting();
			} else {
				pos = advancedCpuTargeting(target);
			}
			Node targetNode = target.getDefenseBoard().getBoard()[pos[0]][pos[1]];
			Node attackBoardNode = getAttackBoard().getBoard()[pos[0]][pos[1]];
			if (targetNode.getHit())
				continue;	

			if (targetNode.getOccupied() == true){
				boolean isNewTarget = true;
				String targetName = targetNode.getOccupant().getName();

				System.out.printf("Enemy has struck our %s at %d,%d!\n",targetName, pos[0], pos[1]);

				targetNode.setContents("\u2613");
				targetNode.setHit(true);
				attackBoardNode.setContents("\u2611");
				targetNode.getOccupant().setHullAt(targetNode.getHullIndex(), true);
				for (CPUTargetTracking trackedTarget : hitTargets) {
					if (trackedTarget.getShip() == targetNode.getOccupant()) {
						isNewTarget = false;
						trackedTarget.getHits().add(pos);
					}
				}

				if (isNewTarget)
					hitTargets.add(new CPUTargetTracking(targetNode.getOccupant(), pos));

				if (targetNode.getOccupant().checkShipDestroyed()){
					hitTargets.remove(0);
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

	public int[] advancedCpuTargeting(Player targetPlayer){
		int pos[] = {0,0};
		Node[][] enemyDefenseBoard = targetPlayer.getDefenseBoard().getBoard();
		CPUTargetTracking huntedTarget = hitTargets.get(0);
		int[] latestHit = huntedTarget.getHits().get(huntedTarget.getHits().size() - 1);
		if (hitTargets.size() == 0)
			System.out.println("Something went wrong!");

		//search right of latest hit
		if (latestHit[1] + 1 > 9 || huntedTarget.getVertical() == true) {
		} else if (enemyDefenseBoard[latestHit[0]][latestHit[1] + 1].getHit() == false){
			pos[0] = latestHit[0]; 
			pos[1] = latestHit[1] + 1;
			return pos;
		}

		//search below latest hit
		if (latestHit[0] + 1 > 9 || huntedTarget.getHorizontal() == true) {
		} else if (enemyDefenseBoard[latestHit[0] + 1][latestHit[1]].getHit() == false){
			pos[0] = latestHit[0] + 1; 
			pos[1] = latestHit[1];
			return pos;
		}

		//search to left of latest hit
		if (latestHit[1] - 1 < 0 || huntedTarget.getVertical() == true) {
		} else if (enemyDefenseBoard[latestHit[0]][latestHit[1] - 1].getHit() == false){
			pos[0] = latestHit[0]; 
			pos[1] = latestHit[1] - 1;
			return pos;
		}

		//search above latest hit
		if (latestHit[0] - 1 < 0 || huntedTarget.getVertical() == true){
		} else if (enemyDefenseBoard[latestHit[0] - 1][latestHit[1]].getHit() == false){
			pos[0] = latestHit[0] - 1; 
			pos[1] = latestHit[1];
			return pos;
		}

		return pos;

	}

}





