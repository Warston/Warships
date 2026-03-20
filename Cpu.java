import java.util.List;
import java.util.ArrayList;

class Cpu extends Player {
	private int difficulty = 1;
	private List<CPUTargetTracking> hitTargets = new ArrayList<>();
	
	Cpu(String newName, int diffSelection){
		super(newName);
		difficulty = diffSelection;
	}

	void fleetSetup(){
		for (Ship ship : getFleet().getFleetList()){
			shipSetup(ship);
		}

	}

	//This ship set up automatically places ships for the cpu
	//it checks for collision and out of bounds
	//this is a brute force method that can potentially continually place ships for many cpu cycles
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
	//Uses either basic or advanced targeting depending on difficulty and if cpu has hit a ship	

	boolean attack(Player target, int[] pos){
		pos = new int[2];

		while (true) {
			if (hitTargets.size() == 0 || difficulty == 1) {
				pos = cpuTargeting();
			} else {
				//If the cpu has hit a ship and is on hard difficulty it will used advanced targeting
				pos = advancedCpuTargeting(target);
			}

			//Obtains target node that will be attacked on the opponents board
			//Obtains corresponding node on attack board for tracking
			Node targetNode = target.getDefenseBoard().getBoard()[pos[0]][pos[1]];
			Node attackBoardNode = getAttackBoard().getBoard()[pos[0]][pos[1]];
			//If the target node has already been hit move back to the start of the loop
			//This may result in wasted cpu cycles as it can continually target hit nodes especially late game
			if (targetNode.getHit())
				continue;	

			//If the targeted node is occupied by a ship it displays a message to the user
			//also sets appropriate nodes to state
			if (targetNode.getOccupied() == true){
				boolean isNewTarget = true;
				String targetName = targetNode.getOccupant().getName();

				System.out.printf("Enemy has struck our %s at %c%d!\n",targetName, ColHash.intToChar(pos[1]), pos[0]);

				targetNode.setContents("\u2613");
				targetNode.setHit(true);
				attackBoardNode.setContents("\u2611");
				targetNode.getOccupant().setHullAt(targetNode.getHullIndex(), true);
				//Checks if the ship that was hit was already in hit target list
				for (CPUTargetTracking trackedTarget : hitTargets) {
					if (trackedTarget.getShip() == targetNode.getOccupant()) {
						isNewTarget = false;
						trackedTarget.getHits().add(pos);
						//If target is already hit, it compares the first hit to the next one
						//to determine orientation
						if (trackedTarget.getHits().get(0)[0] == trackedTarget.getHits().get(1)[0]){
							trackedTarget.setHorizontal(true);
							//System.out.println("*DEBUG* Horizontal True!");
						}
						else {
							trackedTarget.setVertical(true);
							//System.out.println("*DEBUG* Vertical True!");
						}

					}
				}

				//If the attack is on a new target, add the new ship to the target tracking list
				if (isNewTarget)
					hitTargets.add(new CPUTargetTracking(targetNode.getOccupant(), pos));

				//Checks if the ship was destroyed and displays a message
				//Sets the ships destroyed status to true
				if (targetNode.getOccupant().checkShipDestroyed()){
					hitTargets.remove(0);
					targetNode.getOccupant().setDestroyed(true);
					System.out.printf("%s's %s has been destroyed!\n", target.getName(), targetName);
				}
			}
			//If Attack missed display message and set attack board to display a missed hit
			else {
				System.out.printf("Enemy's attack at %c%d missed!\n", ColHash.intToChar(pos[1]), pos[0]);
				attackBoardNode.setContents("\u25ef");
				targetNode.setHit(true);
				if (hitTargets.size() == 0){
				} else if (hitTargets.get(0).getHits().size() == 0){
				} else {
					hitTargets.get(0).setEndOfShip(true);
				}

			}
			//If attack is succesful (hit or miss) then return true
			return true;
		}
	}
	
	//Basic targeting that randomly selects an unhit node to strike

	public int[] cpuTargeting(){
		int pos[] = {0,0};
		pos[0] = (int) (Math.random() * (10));
		pos[1] = (int) (Math.random() * (10));
		return pos;

		
	}

	//Advanced targeting that will attempt to destroy a ship that is struck

	public int[] advancedCpuTargeting(Player targetPlayer){
		int pos[] = {0,0};
		Node[][] enemyDefenseBoard = targetPlayer.getDefenseBoard().getBoard();

		//huntedTarget is the ship the cpu is attempting to destroy
		CPUTargetTracking huntedTarget = hitTargets.get(0);

		//latestHit stores the coordinate of the last strike on the ship
		//this is updated with subsequent hits
		int[] latestHit = {0,0};
		//if the end of the ship is reached then use the first hit location instead
		if (huntedTarget.getEndOfShip() == false){
			latestHit = huntedTarget.getHits().get(huntedTarget.getHits().size() - 1);
		} else {
			latestHit = huntedTarget.getHits().get(0);
			huntedTarget.setEndOfShip(false);
		}

		if (hitTargets.size() == 0)
			System.out.println("Something went wrong!");

		//The search pattern is right, below, left, above
		//If any of these positions have already been hit then it will skip and go to the next
		//Also will skip a direction if the ship orientation is known

		//search right of latest hit
		if (latestHit[1] + 1 > 9 || huntedTarget.getVertical() == true) {
			//System.out.println("*DEBUG* Cant go Right!");
		} else if (enemyDefenseBoard[latestHit[0]][latestHit[1] + 1].getHit() == false){
			pos[0] = latestHit[0]; 
			pos[1] = latestHit[1] + 1;
			//System.out.println("*DEBUG* Going Right!");
			return pos;

		}

		//search below latest hit
		if (latestHit[0] + 1 > 9 || huntedTarget.getHorizontal() == true) {
			//System.out.println("*DEBUG* Can't go down!");
		} else if (enemyDefenseBoard[latestHit[0] + 1][latestHit[1]].getHit() == false){
			pos[0] = latestHit[0] + 1; 
			pos[1] = latestHit[1];
			//System.out.println("*DEBUG* Going down!");
			return pos;
		}

		//search to left of latest hit
		if (latestHit[1] - 1 < 0 || huntedTarget.getVertical() == true) {
			//System.out.println("*DEBUG* Can't go left!");
		} else if (enemyDefenseBoard[latestHit[0]][latestHit[1] - 1].getHit() == false){
			pos[0] = latestHit[0]; 
			pos[1] = latestHit[1] - 1;
			//System.out.println("*DEBUG* Going left!");
			return pos;
		}

		//search above latest hit
		if (latestHit[0] - 1 < 0 || huntedTarget.getHorizontal() == true){
			//System.out.println("*DEBUG* Can't go up!");
		} else if (enemyDefenseBoard[latestHit[0] - 1][latestHit[1]].getHit() == false){
			pos[0] = latestHit[0] - 1; 
			pos[1] = latestHit[1];
			//System.out.println("*DEBUG* Going up!");
			return pos;
		}

		return pos;

	}

}





