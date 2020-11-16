// Dimitrios - Marios Exarchou , 8805 , exarchou.dimitris@gmail.com , 6947014888
// Konstantinos Diamantis , 8981 , kostas.diama97@gmail.com , 6971990336




// Main Class for Game

public class Game {
	
	int round;
	
	//Empty Constructor.
	public Game() {
		
		round = 0;
	}
	
	//Constructor with arguments.
	public Game(int r) {
		
		round = r;
	}
	
	//Constructor with object.
	public Game( Game g) {
		
		this.round=g.round;
	}
	
	
	// Getter.
	public int getRound() {
		
		return round;
	}
 
 	// Setter.
 	public void setRound(int r) {
 		
 		round = r;
 	}
 	
 	
 	// MAIN. Starting point of the Program.
 	public static void main(String[] args) {
 		
 		int N = 20;
 		int M = 20;
 		int W = 6;
 		int F = 10;
 		int T = 8;
 		
 		int[][] weaponAreaLimits = {{2,2},{-2,2},{-2,-2},{2,-2}};
 		int[][] foodAreaLimits =   {{3,3},{-3,3},{-3,-3},{3,-3}};
 		int[][] trapAreaLimits =   {{4,4},{-4,4},{-4,-4},{4,-4}};
 		
 		// Construct a game in 0 round.
 		Game game = new Game(0);
 		
 		// Construct a board.
 		Board board = new Board(N, M, W, F, T);
 		
 		// Set the limits.
 		board.setWeaponAreaLimits(weaponAreaLimits);
 		board.setFoodAreaLimits(foodAreaLimits);
 		board.setTrapAreaLimits(trapAreaLimits);
 		
 		// Generate a Board.
 		board.createBoard();
	
 		// Construct two players.
		Player[] players = new Player[2];
		players[0] = new Player(1, "Player 1", board, 0, -N/2, -M/2, null, null, null);
		players[1] = new Player(2, "Player 2", board, 0, N/2, M/2 , null, null, null);


		System.out.println();
		System.out.println("************ Let the Game begin ************");
		System.out.println();

		int flag = 0; // Variable to check who is playing.

		for (;;) {
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Round " + game.round);
			System.out.println();
			
			
			switch(game.round) {
			
				case 0 :
					
					break;
					
				default :
					//Resizing
					if (game.round % 3 == 0) {
						
						board.resizeBoard(players[0] , players[1]);	
					}
					
					
					// Moving
					if (flag == 0) {
						
						players[0].move();
						flag =1;
						
					}else {
						
						players[1].move();
						flag = 0;
					}
					
					break;
			}
			
			
			// Printing Current Board.

			String [][] array = new String [players[0].board.getN()][players[0].board.getM()];
			array = board.getStringRepresentation();
						
			N = players[0].board.getN();
			M = players[0].board.getM();
					
			// Put Players on the Board.
			//Transforming the Coordinates of a PLAYER to Board Coordinates. For this reason I add N/2 or M/2 to player's current coordinates. 
			//If Player has POSITIVE coordinates then I reduce by 1 so index will not get away of the array limits. This is happening because coordinate 0 so for X as for Y is not represented in my Board.
			
			//Player 1.
			if (players[0].getPlayerX() < 0 && players[0].getPlayerY() < 0) {
					array[players[0].getPlayerX() + N/2][players[0].getPlayerY() + M/2] = "P1";
							
			}else if(players[0].getPlayerX() < 0 && players[0].getPlayerY() > 0) {
					array[players[0].getPlayerX() + N/2][players[0].getPlayerY() + M/2 -1] = "P1";
							
			}else if(players[0].getPlayerX() > 0 && players[0].getPlayerY() < 0) {
					array[players[0].getPlayerX() + N/2 -1][players[0].getPlayerY() + M/2] = "P1";
							
			}else {
					array[players[0].getPlayerX() + N/2 -1][players[0].getPlayerY() + M/2 -1] = "P1";
			}
						
			//Player 2.			
			if (players[1].getPlayerX() < 0 && players[1].getPlayerY() < 0) {
					array[players[1].getPlayerX() + N/2][players[1].getPlayerY() + M/2] = "P2";
							
			}else if(players[1].getPlayerX() < 0 && players[1].getPlayerY() > 0) {
					array[players[1].getPlayerX() + N/2][players[1].getPlayerY() + M/2 -1] = "P2";
							
			}else if(players[1].getPlayerX() > 0 && players[1].getPlayerY() < 0) {
					array[players[1].getPlayerX() + N/2 -1][players[1].getPlayerY() + M/2] = "P2";
							
			}else {
					array[players[1].getPlayerX() + N/2 - 1][players[1].getPlayerY() + M/2 -1] = "P2";
			}
						
			
			// Printing current Board.
			for(int i = 0; i < players[0].board.getN(); i++){
				
					System.out.print("| ");   
					for(int j = 0; j <players[0].board.getM(); j++){
						
						System.out.printf("%-3s", array[i][j]);
						System.out.print("| ");
					}
					System.out.println();
			}
					
			game.round++;
			
			if (N == 4) {
				
				break; // End of the Game.
			}
		}
		
	   System.out.println();
	   System.out.println();
	   System.out.println("************  The island became too small. The game is over!  ************");
	   System.out.println();

	   //Printing points.
	   for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName()+" has " + players[i].getScore() + " points");
	   }

	   
	   //Choosing winner.
	   if (players[0].getScore() > players[1].getScore() ) {
		   
		    System.out.println();
			System.out.println(players[0].getName()  +" won the game!");  
			System.out.println();
			System.out.println("Winner-winner, chicken-dinner!!!");

	   }
	   else if  (players[0].getScore() < players[1].getScore() ) {
		   
		    System.out.println();
			System.out.println(players[1].getName()  +" won the game!");
			System.out.println();
			System.out.println("Winner-winner, chicken-dinner!!!");
	   }
	   else {
		   
			System.out.println("Nobody won the game! Both players have the same score!");  	

		}	
	} 
}
