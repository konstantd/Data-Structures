//Class for Board. It creates a Board by generating random Weapons, Food and Traps. Also it creates a Representation for the Board.

import java.lang.Math;

public class Board {
	
	int N, M;
	int W, F, T;
	int[][] weaponAreaLimits;
	int[][] foodAreaLimits;
	int[][] trapAreaLimits;
	Weapon[] weapons;
	Food[] food;
	Trap[] traps;
	
	
	//Empty Constructor.
	public Board() {
		
		N = 0;
		M = 0;
		W = 0;
		F = 0;
		T = 0;
		weaponAreaLimits = new int[0][0];
		foodAreaLimits = new int[0][0];
		trapAreaLimits = new int[0][0];
		weapons = new Weapon[0];
		food = new Food[0];
		traps = new Trap[0];
		
	}	
	
	//Constructor with arguments.
	public Board(int n, int m, int w, int f, int t) {

		N = n;
		M = m;
		W = w;
		F = f;
		T = t;
		
		weapons = new Weapon[w];
		food = new Food[f];
		traps = new Trap[t];
	}

	//Constructor with object.
	public Board (Board b) {
		
		this.N = b.N;
		this.M = b.M;
		this.W = b.W;
		this.F = b.F;
		this.T = b.T;
		this.weaponAreaLimits = b.weaponAreaLimits;
		this.foodAreaLimits = b.foodAreaLimits;
		this.trapAreaLimits = b.trapAreaLimits;
		this.weapons = b.getWeapons();
		this.food = b.getFood();
		this.traps = b.getTraps();
			
		}
	

	// Getters.
	public int getN() {
		
		return N;
	}
	
	public int getM() {
		
		return M;
	}
	
	public int getW() {
		
		return W;
	}
	
	public int getF() {
		
		return F;
	}
	
	public int getT() {
		
		return T;
	}
	
	public int[][] getWeaponAreaLimits(){
		
		return weaponAreaLimits;
	}
	
	public int[][] getFoodAreaLimits(){
		
		return foodAreaLimits;
	}
	
	public int[][] getTrapAreaLimits(){
		
		return trapAreaLimits;
	}
	
	public Weapon[] getWeapons(){
		
		return weapons;
	}
	
	public Food[] getFood(){
		
		return food;
	}
	
	public Trap[] getTraps(){
		
		return traps;
	}
	
	// Setters.
	public void setN(int n) {
		
		N = n;
	}
	
	public void setM(int m) {
		
		M = m;
	}
	
	public void setW(int w) {
		
		W = w;
	}
	
	public void setF(int f) {
		
		F = f;
	}
	
	public void setT(int t) {
		
		T = t;
	}
	
	public void setWeaponAreaLimits(int[][] wal) {
		
		weaponAreaLimits = wal;
	}
	
	public void setFoodAreaLimits(int[][] fal) {
		
		foodAreaLimits = fal;
	}
	
	public void setTrapAreaLimits(int[][] tal) {
		
		trapAreaLimits = tal;
	}
	
	public void setWeapons(Weapon[] w) {
		
		weapons = w;
	}
	
	public void setFood(Food[] f) {
		
		food = f;
	}
	
	public void setTraps(Trap[] t) {
		
		traps = t;
	}
	
	
	// Functions to find max and min of a 2d array.
	
	public int maxValue(int[][] array) {
		
        int max = array[0][0];
        
        for(int i = 0; i < array.length; i++){
        	
            for(int j = 0; j < array[i].length; j++){
            	
                if(array[i][j] > max){
                	
                    max = array[i][j];
                }
            } 
        }
        return max;
	}
	
	public int minValue(int[][] array) {
		
        int min = array[0][0];
        
        for(int i = 0; i < array.length; i++){
        	
            for(int j = 0; j < array[i].length; j++){
            	
                if(array[i][j] < min){
                	
                    min = array[i][j];
                }
            } 
        }
        return min;
	}
	
	
	// Creators
	
	public void createRandomWeapon() {
		
		// Coordinates
		int randomX = 0;
		int randomY = 0;

		// Type of Weapon (1:bow, 2:pistol, 3:sword)
		int max = 3; 
		int min = 1; 
		int range = max - min + 1; 
     
		int counterB = 0, counterP = 0, counterS = 0;
		int i = 0;
     
		int[][] WeaponPosition = new int[6][2];
		
		int maxCo =  maxValue(weaponAreaLimits); 
	    int minCo =  minValue(weaponAreaLimits); 
	    int rangeCo = maxCo - minCo + 1; 
     
	    while (i < W) {
     	
	    	int flag = 1; // Variable to check if a weapon is placed on another weapon.
			
			while (flag == 1) {
			
				do {
					randomX = (int) (Math.random() * rangeCo) + minCo;
					randomY = (int) (Math.random() * rangeCo) + minCo;
					
				} while (randomX == 0 || randomY == 0); 
				
				if ( i == 0 ) {
					
					break; //First Weapon Placed.
				}
			
				if ( i > 0) {
					
					int wrong = 1;
					
					for (int j = 0; j < i; j++) {
						
						if (randomX == WeaponPosition[j][0] && randomY == WeaponPosition[j][1]) { //Found another weapon in our position.
							
							wrong = 0;
							break;
						}
					}
					
					if (wrong == 1) { // Have not found any weapon in our desired position.
						
						flag = 0; //Ending Loop.
					}
				}
			}
     	
			weapons[i] = new Weapon();
     	
			int randomWeaponValue = (int)(Math.random() * range) + min;
			
			if (randomWeaponValue == 1 && counterB < 2) {
				
				weapons[i].setWeaponId(counterB*3 + 1); //Player's 1 Weapons' IDs: 1,2,3   Player's 2 Weapons' IDs: 4,5,6
				weapons[i].setPlayerId(counterB + 1); //First bow placed is for player 1 and second for player 2.
				weapons[i].setWeaponType("bow");
				counterB++;
				
			}else if (randomWeaponValue == 2 && counterP < 2) {
				
				weapons[i].setWeaponId(counterP*3 + 2);
				weapons[i].setPlayerId(counterP + 1);
				weapons[i].setWeaponType("pistol");
				counterP++;
				
			}else if (randomWeaponValue == 3 && counterS < 2) {
				
				weapons[i].setWeaponId(counterS*3 + 3);
				weapons[i].setPlayerId(counterS + 1);
				weapons[i].setWeaponType("sword");
				counterS++;
				
			}else {
				
				continue;
			}
			
			//Placing Weapon
			
			WeaponPosition[i][0] = randomX;
			WeaponPosition[i][1] = randomY;
			
			weapons[i].setWeaponX(randomX);
			weapons[i].setWeaponY(randomY);
			i++;

	    } 
	}
	
	

	public void createRandomFood() { //Same way as createRandomWeapon
		
		int randomX = 0;
		int randomY = 0;
		int max = 10; 
		int min = 1; 
		int range = max - min + 1; 
     
		int i = 0;
     
		int[][] FoodPosition = new int[10][2];
		
		int maxCo = maxValue(foodAreaLimits); 
	    int minCo = minValue(foodAreaLimits); 
	    int rangeCo = maxCo - minCo + 1; 
     
	    while (i < F) {
     	
	    	food[i] = new Food();
	    	
			//Placing Food
			int flag = 1;
			
			while (flag == 1) {
			
				do {
					randomX = (int) (Math.random() * rangeCo) + minCo;
					randomY = (int) (Math.random() * rangeCo) + minCo;
					
				} while (randomX == 0 || randomY == 0);
			
				if ( Math.abs(randomX) <= maxValue(weaponAreaLimits) && Math.abs(randomY) <= maxValue(weaponAreaLimits)) { //Check that food is not placed inside weapon area limits.
					
					continue;
				}
				
				if ( i == 0) {
					break;
				}
				
				if ( i > 0 ) {
					
					int wrong = 1;
					
					for (int j = 0; j < i; j++) {
						
						if (randomX == FoodPosition[j][0] && randomY == FoodPosition[j][1]) {
							
							wrong = 0;
							break;
						}
					}
					
					if (wrong == 1) {
						
						flag = 0;
					}
				}
			}
			
			FoodPosition[i][0] = randomX;
			FoodPosition[i][1] = randomY;
			
			food[i].setFoodId(i+1);
			food[i].setFoodX(FoodPosition[i][0]);
			food[i].setFoodY(FoodPosition[i][1]);
			
			int randomFoodValue = (int)(Math.random() * range) + min;
			food[i].setFoodPoints(randomFoodValue);
			i++;
	    } 
	}
	
	
	
	public void createRandomTrap() { //Same way as createRandomWeapon
		
		int randomX = 0;
		int randomY = 0;
		
		// Points
		int max = -1; 
		int min = -10;
		int range = max - min + 1; 
     
		int i = 0;
     
		int[][] TrapPosition = new int[8][2];
		
		int maxCo =  maxValue(trapAreaLimits); 
	    int minCo =  minValue(trapAreaLimits); 
	    int rangeCo = maxCo - minCo + 1; 
	    
	    //Trap type
	    int max3 = 1;
	    int min3 = 0;
	    int range3 = max3 - min3+1;
	    
	    while (i < T) {
	    	
	    	traps[i] = new Trap();
	    	
			//Placing Traps
			
			int flag = 1;
			
			while (flag == 1) {
			
				do {
					randomX = (int) (Math.random() * rangeCo) + minCo;
					randomY = (int) (Math.random() * rangeCo) + minCo;
					
				} while (randomX == 0 || randomY == 0);
				
				if ( Math.abs(randomX) <= maxValue(foodAreaLimits) && Math.abs(randomY) <= maxValue(foodAreaLimits)) { //Check that food is not placed inside food area limits.
					continue;
				}
			
				if ( i == 0) {
					break;
				}
				
				if ( i > 0 ) {
					
					int wrong = 1;
					
					for (int j = 0; j < i; j++) {
						
						if (randomX == TrapPosition[j][0] && randomY == TrapPosition[j][1]) {
							
							wrong = 0;
							break;
						}
					}
					
					if (wrong == 1) {
						flag = 0;
					}
				}
			}
			
			TrapPosition[i][0] = randomX;
			TrapPosition[i][1] = randomY;
			
			traps[i].setTrapId(i+1);
			traps[i].setTrapX(TrapPosition[i][0]);
			traps[i].setTrapY(TrapPosition[i][1]);
			
			int randomTrapValue = (int)(Math.random() * range) + min;
			traps[i].setTrapPoints(randomTrapValue);
			
			int randomTrapType = (int)(Math.random() * range3) + min3;
			
			switch(randomTrapType) {
				case 0:
					//type = "bow"
					traps[i].setTrapType("rope");
					break;
				case 1:
					//type = "animal"
					traps[i].setTrapType("animal");
					break;
				default:
					break;	
				
			}

			i++;
	    }   
	}
	
	
	
	//Calling Creators.
	public void createBoard() {
		
		System.out.println(" Generating Board...");
		System.out.println();
		
		createRandomWeapon();		
		createRandomFood();
		createRandomTrap();

	}
	
	
	
	//Resizing board by erasing perimeter.
	public void resizeBoard(Player p1, Player p2){
		
		if ((p1.getPlayerX() != - N/2) && (p1.getPlayerX() != N/2) && (p1.getPlayerY() != -M/2) && (p1.getPlayerY() !=  M/2) && (p2.getPlayerX() != - N/2) && (p2.getPlayerX() !=  N/2) && (p2.getPlayerY() != -N/2) && (p2.getPlayerY() !=  N/2)) {
				
			p1.board.setN(N-2);
			p1.board.setM(M-2);
			
			System.out.println("WARNING: Map is closing!!!");
			System.out.println();
	
		}
	}
	
	
	// Returning a Visualization of the Board.
	public String[][] getStringRepresentation() { 
		
		// Declaration
		String[][] BoardRepresentation = new String[this.getN()][this.getN()]; 
		int x, y;
		String str;
		
		Weapon[] weapons = this.getWeapons();
		Food[] food = this.getFood();
		Trap[] traps = this.getTraps();
		
		// Initializing
		for (int i = 0; i < this.getN(); i++) {
			
			for (int j = 0; j < this.getN(); j++) {
				
				BoardRepresentation[i][j] = "__";
			}
		}
		
		//Put Items on Board.
		
		//Transforming the Coordinates of an ITEM to Board Coordinates. For this reason I add N/2 to item's current coordinates. 
		//If Item has POSITIVE coordinates then I reduce by 1 so index will not get away of the array limits. This is happening because coordinate 0 so for X as for Y is not represented in my Board.
		
		for (int i = 0; i < W; i++) {
			
			if (weapons[i].getWeaponX() < 0 && weapons[i].getWeaponY() < 0) {
				
				x = weapons[i].getWeaponX() + this.getN()/2;
				y = weapons[i].getWeaponY() + this.getN()/2;
				str = String.valueOf(weapons[i].getPlayerId()) + String.valueOf(weapons[i].getWeaponId() - (weapons[i].getPlayerId() - 1) * 3 ) ;	// I want the weapon with id for example 4 to be represented as W21.
				BoardRepresentation[x][y]= "W" + str;
				
			}else if(weapons[i].getWeaponX() < 0 && weapons[i].getWeaponY() > 0) {
				
				x = weapons[i].getWeaponX() + this.getN()/2 ;
				y = weapons[i].getWeaponY() + this.getM()/2 - 1;
				str = String.valueOf(weapons[i].getPlayerId()) + String.valueOf(weapons[i].getWeaponId() - (weapons[i].getPlayerId() - 1) * 3 ) ;	
				BoardRepresentation[x][y]= "W" + str;
				
			}else if (weapons[i].getWeaponX() > 0 && weapons[i].getWeaponY() < 0) {
				
				x = weapons[i].getWeaponX() + this.getN()/2 -1 ;
				y = weapons[i].getWeaponY() + this.getM()/2 ;
				str = String.valueOf(weapons[i].getPlayerId()) + String.valueOf(weapons[i].getWeaponId() - (weapons[i].getPlayerId() - 1) * 3 ) ;	
				BoardRepresentation[x][y]= "W" + str;
				
			}else if (weapons[i].getWeaponX() > 0 && weapons[i].getWeaponY() > 0){
				
				x = weapons[i].getWeaponX() + this.getN()/2 -1 ;
				y = weapons[i].getWeaponY() + this.getM()/2 - 1;
				str = String.valueOf(weapons[i].getPlayerId()) + String.valueOf(weapons[i].getWeaponId()- (weapons[i].getPlayerId() - 1) * 3 ) ;	
				BoardRepresentation[x][y]= "W" + str;
			}
			// Coordinates that are zeros will be not represented, because this means items that have bee already picked. 
		}
		
		
		for (int i = 0;  i < F; i++) {
			
			if (this.getN() == 4 && this.getM() == 4) { // I don't represent Foods when map is 4x4.
				break;
			}
			if (food[i].getFoodX() < 0 && food[i].getFoodY() < 0) {
				
				x = food[i].getFoodX() + this.getN()/2; 
				y = food[i].getFoodY() + this.getN()/2; 
				str = String.valueOf(food[i].getFoodId());	
				BoardRepresentation[x][y]= "F" + str;  
				
			}else if(food[i].getFoodX() < 0 && food[i].getFoodY() > 0){
				
				x = food[i].getFoodX() + this.getN()/2 ; 
				y = food[i].getFoodY() + this.getN()/2 - 1; 
				str = String.valueOf(food[i].getFoodId());	
				BoardRepresentation[x][y]= "F" + str;  
			}
			else if(food[i].getFoodX() > 0 && food[i].getFoodY() < 0) {
				
				x = food[i].getFoodX() + this.getN()/2 - 1; 
				y = food[i].getFoodY() + this.getN()/2 ; 
				str = String.valueOf(food[i].getFoodId());	
				BoardRepresentation[x][y]= "F" + str;  
				
			}else if(food[i].getFoodX() > 0 && food[i].getFoodY() > 0){
				
				x = food[i].getFoodX() + this.getN()/2 - 1; 
				y = food[i].getFoodY() + this.getN()/2 -1; 
				str = String.valueOf(food[i].getFoodId());	
				BoardRepresentation[x][y]= "F" + str; 
			}
		}

		
		for (int i = 0; i < T; i++) {  
			
			if ((this.getN() == 6 && this.getM() == 6) || (this.getN() == 4 && this.getM() == 4)) { // I don't represent Foods when map is 4x4 or 6x6.
				break;
			}
			
			if (traps[i].getTrapX() < 0 && traps[i].getTrapY() < 0) {
				
				x = traps[i].getTrapX() + this.getN()/2; 
				y = traps[i].getTrapY() + this.getN()/2; 
				str = String.valueOf(traps[i].getTrapId());	
				BoardRepresentation[x][y]= "T" + str; 
				
			}else if(traps[i].getTrapX() < 0 && traps[i].getTrapY() > 0){
				
				x = traps[i].getTrapX() + this.getN()/2; 
				y = traps[i].getTrapY() + this.getN()/2 - 1;  
				str = String.valueOf(traps[i].getTrapId());	
				BoardRepresentation[x][y]= "T" + str; 	
				
			}else if(traps[i].getTrapX() > 0 && traps[i].getTrapY() < 0) {
				
				x = traps[i].getTrapX() + this.getN()/2 -1; 
				y = traps[i].getTrapY() + this.getN()/2 ;   
				str = String.valueOf(traps[i].getTrapId());	
				BoardRepresentation[x][y]= "T" + str; 	
				
			}else if(traps[i].getTrapX() > 0 && traps[i].getTrapY() > 0){
				
				x = traps[i].getTrapX() + this.getN()/2  - 1; 
				y = traps[i].getTrapY() + this.getN()/2 - 1;   
				str = String.valueOf(traps[i].getTrapId());	
				BoardRepresentation[x][y]= "T" + str; 	
			}			   				   
		}
		
		return (BoardRepresentation);
		
	}

}