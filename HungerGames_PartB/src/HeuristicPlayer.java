// Class for the Heuristic Player.

import java.util.ArrayList;
import java.lang.Math; 
import java.util.HashMap;
import java.util.Map;



public class HeuristicPlayer extends Player {
	
	
    // the HeuristicPlayer subclass adds tow more fields 
	ArrayList <Integer[]> path;
	static int r;
	
	public HeuristicPlayer() {
		super();
		path = null;
		r = 0;
		
	}

	public HeuristicPlayer(int id, String n, Board b, int s, int x, int y, Weapon bo, Weapon pi, Weapon sw, ArrayList <Integer[]> pa, int rad) {
		super(id, n, b, s, x, y, bo, pi, sw);
		path = pa;
		r = rad;
	}

	public HeuristicPlayer(Player p, ArrayList <Integer[]> pa, int rad) {
		super(p);
		path = pa;
		r = rad;
		
	}
	
	
	// Getters.
	
	public ArrayList <Integer[]> getPlayerPath() {
		
		return path;
	}
	public int getR() {
		
		return r;
	}
	
	// Setters.
	
	public void setPath(ArrayList<Integer[]> pa) {
			
		path = pa;
	}
	public void setR(int rad) {
			
		r = rad;
	}
	

	
	//Function to calculate distance.
	float playersDistance(Player p) {
		
		int enemyX = p.getPlayerX();
		int enemyY = p.getPlayerY();
		
		int ownX = this.getPlayerX();
		int ownY = this.getPlayerY();
		
		float distance = (float) Math.sqrt(Math.pow(ownX-enemyX, 2) + Math.pow(ownY-enemyY, 2));
		
	
		if ( (Math.abs(ownX-enemyX) + Math.abs(ownY-enemyY)) > this.getR() ) {
			
			distance = -1;
		}
		
		return distance;
	}
	
	

	//Function to evaluate a move.
	double evaluate(int dice, Player p) {
		
		Board board = this.getBoard();
		Weapon[] weapons = board.getWeapons();
		Food[] food = board.getFood();
		Trap[] traps = board.getTraps();
		float distance;
		int flag = 0;
		int gainWeapons = 0, gainPoints = 0, avoidTraps = 0;
		float forceKill = 0;
		int newX = this.getPlayerX();
		int newY = this.getPlayerY();
		double[] weights = {0.40,0.30,0.30,0};
		double evaluation;
		
		
		switch(dice) {
	
		  case 1:
			  newY = newY-1;
			  if (newY == 0) { //Zeros are not represented in my Board. For this reason I skip zero Coordinates by moving the player one more step.
				  newY = newY-1;
			  }
			  break;
		  case 2:
			  newY = newY-1;
			  if (newY == 0) {
				  newY = newY-1;
			  }
			  newX = newX+1;
			  if (newX == 0) {
				  newX = newX+1;
			  }
			  break;
		  case 3:
			  newX = newX+1;
			  if (newX == 0) {
				  newX = newX+1;
			  }
			  break;
		  case 4:
			  newY = newY+1;
			  if (newY == 0) {
				  newY = newY+1;
			  }
			  newX = newX+1;
			  if (newX == 0) {
				  newX = newX+1;
			  }
			  break;
		  case 5:
			  newY = newY+1;
			  if (newY == 0) {
				  newY = newY+1;
			  }
			  break;
		  case 6:
			  newY = newY+1;
			  if (newY == 0) {
				  newY = newY+1;
			  }
			  newX = newX-1;
			  if (newX == 0) {
				  newX = newX-1;
			  }
			  break;
		  case 7:
			  newX = newX-1;
			  if (newX == 0) {
				  newX = newX-1;
			  }
			  break;
		  case 8:
			  newY = newY-1;
			  if (newY == 0) {
				  newY = newY-1;
			  }
			  newX = newX-1;
			  if (newX == 0) {
				  newX = newX-1;
			  }
			  break;
		  default:
			  break;
		
		}
		
		

		//I have to look for the item that player found.
		for (int i = 0; i < board.getWeapons().length; i++) {
				
			if (newX == weapons[i].getWeaponX() && newY == weapons[i].getWeaponY()) { //Player is on a Weapon.
						
				flag = 1; // Found a Weapon
					
				if(weapons[i].getPlayerId() == this.getPlayerId()) { //The weapon belongs to player.
						
					gainWeapons = 5;
						
				}
				break;	// Stop Searching Weapons
			}

				
		}
				
				
		if (flag == 0) { //Don't found a Weapon in current Position.
				
			for (int i = 0; i < board.getF(); i++) {
						
				if (newX == food[i].getFoodX() && newY == food[i].getFoodY()) { //Player is on Food.
							
					flag = 1; // Found Food
						
					gainPoints = food[i].getFoodPoints();
						
					break; //Stop Searching Food
				}
			}

		}
			
			
		if (flag == 0) { // Not found a Weapon or Food in current Position.
				
			for (int i = 0; i < board.getT(); i++) {
					
				if (newX == traps[i].getTrapX() && newY == traps[i].getTrapY()) { //Player is on Trap.
						
					//Found Trap
						
					if (traps[i].getTrapType() == "animal" && this.pistol == null || traps[i].getTrapType() == "rope" && this.sword == null) { //Player will lose points only if he has not the necessary weapon.
								
						avoidTraps = traps[i].getTrapPoints();
					}

					break; //Stop Searching Traps
				}
			} 
		}
		
		
		
		// Changing weights.
		
		if ( this.getPistol() != null  && p.getPistol() == null) {
			
			weights[0] = 0.2;
			weights[1] = 0.4;
			weights[2] = 0.4;
			weights[3] = 1;
			
		}
		
		if ( this.getPistol() == null && p.getPistol() != null ) {
			
			weights[0] = 0.4;
			weights[1] = 0.2;
			weights[2] = 0.2;
			weights[3] = -1;
			
		}
		
		
		//distance = playersDistance(p);
		int enemyX = p.getPlayerX();
		int enemyY = p.getPlayerY();
		
		distance = (float) Math.sqrt(Math.pow(newX-enemyX, 2) + Math.pow(newY-enemyY, 2));
		
		if ( (Math.abs(newX-enemyX) + Math.abs(newY-enemyY)) > this.getR() ) {
			
			distance = -1;
		}
		
		
		if ( distance < 0 ) {
			
			forceKill = 0;
			
		}else {
			
			forceKill = ( 3 - distance ) * 5;
			
		}
		
		
		// Target Function
		
		evaluation = weights[0]*gainWeapons + weights[1]*avoidTraps + weights[2]*gainPoints + weights[3]*forceKill;
		
		return evaluation;
	
		
	}
	
	

	// Function to check if a shot has been taken.
	
	public static boolean kill(Player player1, Player player2, float d) {
		
		int enemyX = player2.getPlayerX();
		int enemyY = player2.getPlayerY();
	
		int ownX = player1.getPlayerX();
		int ownY = player1.getPlayerY();
	
		float distance = (float) Math.pow(Math.pow(ownX-enemyX, 2) + Math.pow(ownY-enemyY, 2) , 0.5);
	
		if (distance < d && player1.pistol != null) {
			
			return true;
		}
	
		return false;
	
	}

	
	

	// Function to finally move Heuristic Player
	int[] move(Player p){
		
		Map <Integer, Double> map = new HashMap<Integer, Double>(); 
		
		// I have to find possible moves
		int x = this.getPlayerX();
		int y = this.getPlayerY();
		
		
		
		if ( x == - this.getBoard().getN()/2 ) {

			if ( y == - this.getBoard().getM()/2 ) {
				
				map.put(3, evaluate(3,p));
				map.put(4, evaluate(4,p));
				map.put(5, evaluate(5,p));
				
			}else if ( y == this.getBoard().getM()/2 ) {
				
				map.put(1, evaluate(1,p));
				map.put(2, evaluate(2,p));
				map.put(3, evaluate(3,p));
				
			}else {
				
				map.put(1, evaluate(1,p));
				map.put(2, evaluate(2,p));
				map.put(3, evaluate(3,p));
				map.put(4, evaluate(4,p));
				map.put(5, evaluate(5,p));
				
			}
			
			
			
		}else if (x == getBoard().getN()/2 ) {
			
			
			
			if ( y == - getBoard().getM()/2 ) {
				
				map.put(5, evaluate(5,p));
				map.put(6, evaluate(6,p));
				map.put(7, evaluate(7,p));
				
			}else if ( y == getBoard().getM()/2 ) {
				
				map.put(7, evaluate(7,p));
				map.put(8, evaluate(8,p));
				map.put(1, evaluate(1,p));
				
			}else {
				
				map.put(1, evaluate(1,p));
				map.put(5, evaluate(5,p));
				map.put(6, evaluate(6,p));
				map.put(7, evaluate(7,p));
				map.put(8, evaluate(8,p));
				
			}
			
			
		}else {
			
			
			
			if ( y == - getBoard().getM()/2 ) {
				
				map.put(3, evaluate(3,p));
				map.put(4, evaluate(4,p));
				map.put(5, evaluate(5,p));
				map.put(6, evaluate(6,p));
				map.put(7, evaluate(7,p));
				
			}else if ( y == getBoard().getM()/2 ) {
				
				map.put(7, evaluate(7,p));
				map.put(8, evaluate(8,p));
				map.put(1, evaluate(1,p));
				map.put(2, evaluate(2,p));
				map.put(3, evaluate(3,p));
				
			}else {
				
				map.put(1, evaluate(1,p));
				map.put(2, evaluate(2,p));
				map.put(3, evaluate(3,p));
				map.put(4, evaluate(4,p));
				map.put(5, evaluate(5,p));
				map.put(6, evaluate(6,p));
				map.put(7, evaluate(7,p));
				map.put(8, evaluate(8,p));
			}
		}
		

		// I have to find dice with maximum evaluation.
		
		int bestDice = 0;	
		
		Map.Entry<Integer, Double> maxEntry = null;

		for (Map.Entry<Integer, Double> entry : map.entrySet())
		{
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			{
			    maxEntry = entry;
			    bestDice = entry.getKey();
			    
			}
		}
		
		
		// Creating a list to save every best evaluated move.
		
		ArrayList <Integer> Dices = new ArrayList<Integer>();
		
		
		for (Map.Entry<Integer, Double> entry : map.entrySet())
		{
			if (entry.getValue().compareTo(maxEntry.getValue()) == 0)
			{
				
				Dices.add(entry.getKey());
				
			    
			}
		}
		
		
		//Randomly chose one of the best dices.
		
		int max = Dices.size()-1; 
	    int min = 0; 
	    int range = max - min + 1; 
		
	    int randNumber = (int)(Math.random() * range) + min;
	    
	    bestDice = Dices.get(randNumber);
		
		
	
		
		// Move player.
	    
		int newX = this.getPlayerX();
		int newY = this.getPlayerY();
		
		switch (bestDice) {
		
		  case 1:
			  newY = newY-1;
			  if (newY == 0) { //Zeros are not represented in my Board. For this reason I skip zero Coordinates by moving the player one more step.
				  newY = newY-1;
			  }
			  break;
		  case 2:
			  newY = newY-1;
			  if (newY == 0) {
				  newY = newY-1;
			  }
			  newX = newX+1;
			  if (newX == 0) {
				  newX = newX+1;
			  }
			  break;
		  case 3:
			  newX = newX+1;
			  if (newX == 0) {
				  newX = newX+1;
			  }
			  break;
		  case 4:
			  newY = newY+1;
			  if (newY == 0) {
				  newY = newY+1;
			  }
			  newX = newX+1;
			  if (newX == 0) {
				  newX = newX+1;
			  }
			  break;
		  case 5:
			  newY = newY+1;
			  if (newY == 0) {
				  newY = newY+1;
			  }
			  break;
		  case 6:
			  newY = newY+1;
			  if (newY == 0) {
				  newY = newY+1;
			  }
			  newX = newX-1;
			  if (newX == 0) {
				  newX = newX-1;
			  }
			  break;
		  case 7:
			  newX = newX-1;
			  if (newX == 0) {
				  newX = newX-1;
			  }
			  break;
		  case 8:
			  newY = newY-1;
			  if (newY == 0) {
				  newY = newY-1;
			  }
			  newX = newX-1;
			  if (newX == 0) {
				  newX = newX-1;
			  }
			  break;
		  default:
			  break;
			    
		}
 
		
		
		int[] newPos = new int[2];
		newPos[0] = newX;
		newPos[1] = newY;
		
		this.setPlayerX (newX) ; 
		this.setPlayerY (newY) ; 

		
			
		Board board = this.getBoard();
		int WeaponNo = 0, FoodNo = 0, TrapNo = 0, Points = 0;
		String[][] Board = board.getStringRepresentation();
		int flag = 0;//Variable to check if player found an item.
			
		
			
		int k,l;
			
		//Transforming the Coordinates of a PLAYER to Board Coordinates. For this reason I add N/2 to player's current coordinates. 
		//If Player has POSITIVE coordinates then I reduce by 1 so index will not get away of the array limits. This is happening because coordinate 0 so for X as for Y is not represented in my Board.
		
		if (newPos[0] < 0) {
				
			k = newPos[0] + board.getN()/2;
				
		}else {
				
			k = newPos[0] + board.getN()/2 - 1;
		}
			
		if (newPos[1] < 0) {
				
			l = newPos[1] + board.getM()/2;
				
		}else {
				
			l = newPos[1] + board.getM()/2 -1;
		}
			
			
		if (Board[k][l] == "__") {
				
			//If player moves to an empty cell, then he picks no items.
				
		}else {
			
			//Player found an item.
				
			Weapon[] weapons = board.getWeapons();
			Food[] food = board.getFood();
			Trap[] traps = board.getTraps();
				
			//I have to look for the item that player found.
			
			for (int i = 0; i < board.getWeapons().length; i++) {
					
				if (newPos[0] == weapons[i].getWeaponX() && newPos[1] == weapons[i].getWeaponY()) { //Player is on a Weapon.
							
					flag = 1; // Found a Weapon
						
					if(weapons[i].getPlayerId() == this.getPlayerId()) { //The weapon belongs to player.
							
						switch (weapons[i].getWeaponType()) {
							
							case "bow":

								bow = new Weapon(weapons[i]);
								
								System.out.println("Player 1 picked a " + weapons[i].getWeaponType() + "!");
								System.out.println();
								weapons[i].setWeaponX(0); //Weapon will be disappeared
								weapons[i].setWeaponY(0);
								WeaponNo++;
								break;
							
							case "pistol":

								pistol = new Weapon (weapons[i]);
								
								System.out.println("Player 1 picked a " + weapons[i].getWeaponType() + "!");
								System.out.println();
								weapons[i].setWeaponX(0);
								weapons[i].setWeaponY(0);
								WeaponNo++;
								break;
								
							case "sword":

								sword = new Weapon(weapons[i]);
								
								System.out.println("Player 1 picked a " + weapons[i].getWeaponType() + "!");
								System.out.println();
								weapons[i].setWeaponX(0);
								weapons[i].setWeaponY(0);
								WeaponNo++;
								break;
								
							default:
								break;		
						}
					}

					break;	// Stop Searching Weapons
				}
					
			}

				
			if (flag == 0) { //Don't found a Weapon in current Position.
					
				for (int i = 0; i < board.getF(); i++) {
							
					if (newPos[0] == food[i].getFoodX() && newPos[1] == food[i].getFoodY()) { //Player is on Food.
								
						flag = 1; // Found Food
							
						System.out.println("Player 1 found some Food!");
						System.out.println();
						setScore(getScore() + food[i].getFoodPoints());
						
						food[i].setFoodX(0);
						food[i].setFoodY(0);
						FoodNo++;
						Points = food[i].getFoodPoints();
						break; //Stop Searching Food
					}
				}

			}
				
				
			if (flag == 0) { // Not found a Weapon or Food in current Position.
					
				for (int i = 0; i < board.getT(); i++) {
						
					if (newPos[0] == traps[i].getTrapX() && newPos[1] == traps[i].getTrapY()) { //Player is on Trap.
							
						//Found Trap
							
						System.out.println("Player 1 fell in a Trap!");
						System.out.println();

						if (traps[i].getTrapType() == "animal" && this.pistol == null || traps[i].getTrapType() == "rope" && this.sword == null) { //Player will lose points only if he has not the necessary weapon.
									
							setScore(getScore() + traps[i].getTrapPoints());
								
							Points = traps[i].getTrapPoints();
						}
						
						traps[i].setTrapX(0);
						traps[i].setTrapY(0);
						TrapNo++;

						break; //Stop Searching Traps
					}
				} 
			}
		}
			

		Integer[] MoveResults = {bestDice,Points,WeaponNo,FoodNo,TrapNo};

		path.add(MoveResults);
	
		return newPos;  
	}
	

	
	// Function to print statistic of Heuristic Player's move.
	
	void statistics() {
			
		System.out.println("Heuristic Player: Dice: " + String.valueOf(path.get(path.size()-1)[0]) + " , Points: " + String.valueOf(path.get(path.size()-1)[1]) + " , # Weapons: " + String.valueOf(path.get(path.size()-1)[2]) + " , # Food: " + String.valueOf(path.get(path.size()-1)[3]) + " , # Traps: " + String.valueOf(path.get(path.size()-1)[4])+ "\n");	
			
	}

}

