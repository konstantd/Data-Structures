// Class for Player. It is responsible for moving the players and picking items.

public class Player {
	
	int playerId;
	String name;
	Board board;
	int score;
	int playerX;
	int playerY;
	Weapon bow;
	Weapon pistol;
	Weapon sword;
	
	//Empty Constructor.
	public Player() {
		
		playerId = 0;
		name = "";
		board = new Board();
		score = 0;
		playerX = 0;
		playerY = 0;
		bow = null;
		pistol = null;
		sword = null;
	}
	
	//Constructor with arguments.
	public Player(int id, String n, Board b, int s, int x, int y, Weapon bo, Weapon pi, Weapon sw) {
		
		playerId = id;
		name = n;
		board = b;
		score = s;
		playerX = x;
		playerY = y;
		bow = bo;
		pistol = pi;
		sword = sw;		
	}
		
	
	//Constructor with object.
	public Player (Player p) {
		
		this.playerId = p.playerId;
		this.name = p.name;
		this.board = p.board;
		this.score = p.score;
		this.playerX = p.playerX;
		this.playerY = p.playerY;
		this.bow = p.bow;
		this.pistol = p.pistol;
		this.sword = p.sword;
			
		}
	
	// Getters.
	
	public int getPlayerId() {
		
		return playerId;
	}
	public String getName() {
		
		return name;
	}
	public Board getBoard() {
		
		return board;
	}
	public int getScore() {
		
		return score;
	}
	public int getPlayerX() {
		
		return playerX;
	}
	public int getPlayerY() {
		
		return playerY;
	}
	public Weapon getBow() {
		
		return bow;
	}
	public Weapon getPistol() {
		
		return pistol;
	}
	public Weapon getSword() {
		
		return sword;
	}
	
	
	// Setters.
	
	public void setPlayerId(int id) {
		
		playerId = id;
	}
	public void setPlayerName(String n) {
		
		name = n;
	}
	public void setBoard(Board b) {
		
		board = b;
	}
	public void setScore(int s) {
		
		score = s;
	}
	public void setPlayerX(int x) {
		
		playerX = x;
	}
	public void setPlayerY(int y) {
		
		playerY = y;
	}
	public void setBow(Weapon bo) {
		
		bow = bo;
	}
	public void setPistol(Weapon pi) {
		
		pistol = pi;
	}
	public void setSword(Weapon sw) {
		
		sword = sw;
	}
	
	
	
	// Throw the dice.
	
	public int[] getRandomMove() {
		
		int[] newPosition = new int[2];
		int newX = this.getPlayerX();
		int newY = this.getPlayerY();
		
		// Results of the dice[1,8].
		int max = 8; 
	    int min = 1; 
	    int range = max - min + 1; 
		
		do {
			
			int dice = (int)(Math.random() * range) + min;
			
			switch(dice) {
			  case 1:
				  newY = playerY-1;
				  if (newY == 0) { //Zeros are not represented in my Board. For this reason I skip zero Coordinates by moving the player one more step.
					  newY = newY-1;
				  }
				  break;
			  case 2:
				  newY = playerY-1;
				  if (newY == 0) {
					  newY = newY-1;
				  }
				  newX = playerX+1;
				  if (newX == 0) {
					  newX = newX+1;
				  }
				  break;
			  case 3:
				  newX = playerX+1;
				  if (newX == 0) {
					  newX = newX+1;
				  }
				  break;
			  case 4:
				  newY = playerY+1;
				  if (newY == 0) {
					  newY = newY+1;
				  }
				  newX = playerX+1;
				  if (newX == 0) {
					  newX = newX+1;
				  }
				  break;
			  case 5:
				  newY = playerY+1;
				  if (newY == 0) {
					  newY = newY+1;
				  }
				  break;
			  case 6:
				  newY = playerY+1;
				  if (newY == 0) {
					  newY = newY+1;
				  }
				  newX = playerX-1;
				  if (newX == 0) {
					  newX = newX-1;
				  }
				  break;
			  case 7:
				  newX = playerX-1;
				  if (newX == 0) {
					  newX = newX-1;
				  }
				  break;
			  case 8:
				  newY = playerY-1;
				  if (newY == 0) {
					  newY = newY-1;
				  }
				  newX = playerX-1;
				  if (newX == 0) {
					  newX = newX-1;
				  }
				  break;
			  default:
				  break;
				  
			}
			
			
		}while( Math.abs(newX) > this.getBoard().getN()/2  ||   Math.abs(newY) > this.getBoard().getM()/2 ); // Player must stay inside the limits of the board.
		
		this.setPlayerX (newX) ;
		this.setPlayerY (newY) ;
		
		newPosition[0] = newX;
		newPosition[1] = newY;
		
		return newPosition;
	}
	
	
	
	
	// Using RandomMove for calculate the results of the move of a player.
	public int[] move() {
		
		Board board = this.getBoard();
		int [] MoveResults = new int[5];
		int WeaponNo = 0, FoodNo = 0, TrapNo = 0;
		String[][] Board = board.getStringRepresentation();
		int flag = 0;//Variable to check if player found an item.
		
		int[] newPos = getRandomMove();
		
		MoveResults[0] = newPos[0] ; // I have to return the new position.
		MoveResults[1] = newPos[1] ;
		
		int k,l;
		
		//Transforming the Coordinates of a PLAYER to Board Coordinates. For this reason I add N/2 to player's current coordinates. 
		//If Player has POSITIVE coordinates then I reduce by 1 so index will not get away of the array limits. This is happening because coordinate 0 so for X as for Y is not represented in my Board.
		if (MoveResults[0] < 0) {
			
			k = newPos[0] + board.getN()/2;
			
		}else {
			
			k = newPos[0] + board.getN()/2 - 1;
		}
		
		if (MoveResults[1] < 0) {
			
			l = newPos[1] + board.getM()/2;
			
		}else {
			
			l = newPos[1] + board.getM()/2 -1;
		}
		

		//If player moves to an empty cell, then he picks no items.
		
		if (Board[k][l] == "__") {
			
			MoveResults[2] = 0;
			MoveResults[3] = 0;
			MoveResults[4] = 0;
			
		}else {
			
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
							
								System.out.println("A " + weapons[i].getWeaponType() + " has been picked.");
								System.out.println();
								weapons[i].setWeaponX(0); //Weapon will be disappeared
								weapons[i].setWeaponY(0);
								WeaponNo++;
								break;
						
							case "pistol":

								pistol = new Weapon (weapons[i]);
							
								System.out.println("A " + weapons[i].getWeaponType() + " has been picked.");
								System.out.println();
								weapons[i].setWeaponX(0);
								weapons[i].setWeaponY(0);
								WeaponNo++;
								break;
							
							case "sword":

								sword = new Weapon(weapons[i]);
							
								System.out.println("A " + weapons[i].getWeaponType() + " has been picked.");
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
						
						System.out.println("Some Food has been picked.");
						System.out.println();
						setScore(getScore() + food[i].getFoodPoints());
						//this.score = this.score + food[i].getFoodPoints();
						food[i].setFoodX(0);
						food[i].setFoodY(0);
						FoodNo++;
						break; //Stop Searching Food
					}
				}

			}
			
			
			if (flag == 0) { // Not found a Weapon or Food in current Position.
				
				for (int i = 0; i < board.getT(); i++) {
					
					if (newPos[0] == traps[i].getTrapX() && newPos[1] == traps[i].getTrapY()) { //Player is on Trap.
						
						//Found Trap
						
						System.out.println("A player has fallen in a Trap.");
						System.out.println();

						if (traps[i].getTrapType() == "animal" && this.pistol == null || traps[i].getTrapType() == "rope" && this.sword == null) { //Player will lose points only if he has not the necessary weapon.
								
							setScore(getScore() + traps[i].getTrapPoints());
							//this.score = this.score + traps[i].getTrapPoints();
						}
						traps[i].setTrapX(0);
						traps[i].setTrapY(0);
						TrapNo++;
						break; //Stop Searching Traps
					}
				} 
			}
		}
		
		MoveResults[2] = WeaponNo;
		MoveResults[3] = FoodNo;
		MoveResults[4] = TrapNo;
		
		return (MoveResults);
		
	}	
}