// Dimitrios - Marios Exarchou , 8805 , exarchou.dimitris@gmail.com , 6947014888
// Konstantinos Diamantis , 8981 , kostas.diama97@gmail.com , 6971990336

import java.util.ArrayList;


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
 		int r = 3;
 		boolean check1 = false;
 		boolean check2 = false;
 		
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
 		ArrayList <Integer[]> path = new ArrayList<Integer[]>();
		HeuristicPlayer player1 = new HeuristicPlayer(1, "Heuristic Player 1", board, 0, -N/2, -M/2 , null, null, null, path, r);
		Player player2 = new Player(2, "Player 2", board, 0, N/2, M/2, null, null, null);

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
						
						board.resizeBoard(player1 , player2);	
					}
					
					
					// Moving
					if (flag == 0) {
						
						player1.move(player2);
						check1 = player1.kill(player1,player2, 2);
						flag = 1;
						player1.statistics();
						
					}else {
						
						player2.move();
						check2 = player1.kill(player2,player1, 2);
						flag = 0;
					}
					
					break;
			}
			
			
			// Printing Current Board.

			String [][] array = new String [player1.board.getN()][player2.board.getM()];
			array = board.getStringRepresentation();
						
			N = player1.board.getN();
			M = player2.board.getM();
					
			// Put Players on the Board.
			//Transforming the Coordinates of a PLAYER to Board Coordinates. For this reason I add N/2 or M/2 to player's current coordinates. 
			//If Player has POSITIVE coordinates then I reduce by 1 so index will not get away of the array limits. This is happening because coordinate 0 so for X as for Y is not represented in my Board.
			
			//Player 1.
			if (player1.getPlayerX() < 0 && player1.getPlayerY() < 0) {
					array[player1.getPlayerX() + N/2][player1.getPlayerY() + M/2] = "#1";
							
			}else if(player1.getPlayerX() < 0 && player1.getPlayerY() > 0) {
					array[player1.getPlayerX() + N/2][player1.getPlayerY() + M/2 -1] = "#1";
							
			}else if(player1.getPlayerX() > 0 && player1.getPlayerY() < 0) {
					array[player1.getPlayerX() + N/2 -1][player1.getPlayerY() + M/2] = "#1";
							
			}else {
					array[player1.getPlayerX() + N/2 -1][player1.getPlayerY() + M/2 -1] = "#1";
			}
						
			//Player 2.			
			if (player2.getPlayerX() < 0 && player2.getPlayerY() < 0) {
					array[player2.getPlayerX() + N/2][player2.getPlayerY() + M/2] = "#2";
							
			}else if(player2.getPlayerX() < 0 && player2.getPlayerY() > 0) {
					array[player2.getPlayerX() + N/2][player2.getPlayerY() + M/2 -1] = "#2";
							
			}else if(player2.getPlayerX() > 0 && player2.getPlayerY() < 0) {
					array[player2.getPlayerX() + N/2 -1][player2.getPlayerY() + M/2] = "#2";
							
			}else {
					array[player2.getPlayerX() + N/2 - 1][player2.getPlayerY() + M/2 -1] = "#2";
			}
			
			// Both players on same position.
			if (player1.getPlayerX() == player2.getPlayerX() && player1.getPlayerY() == player2.getPlayerY()) {
				
				if (player1.getPlayerX() < 0 && player1.getPlayerY() < 0) {
					array[player1.getPlayerX() + N/2][player1.getPlayerY() + M/2] = "XX";
							
				}else if(player1.getPlayerX() < 0 && player1.getPlayerY() > 0) {
					array[player1.getPlayerX() + N/2][player1.getPlayerY() + M/2 -1] = "XX";
							
				}else if(player1.getPlayerX() > 0 && player1.getPlayerY() < 0) {
					array[player1.getPlayerX() + N/2 -1][player1.getPlayerY() + M/2] = "XX";
							
				}else {
					array[player1.getPlayerX() + N/2 -1][player1.getPlayerY() + M/2 -1] = "XX";
				}
				
			}
						
			
			// Printing current Board.
			for(int i = 0; i < player1.board.getN(); i++){
				
					System.out.print("| ");   
					for(int j = 0; j <player1.board.getM(); j++){
						
						System.out.printf("%-3s", array[i][j]);
						System.out.print("| ");
					}
					System.out.println();
			}
					
			game.round++;
			
			if (N == 4 || check1 == true || check2 == true) {
				
				break; // End of the Game.
			}
		}
		
		
	   

	   //Printing points.
	   
	   if (check1 == false && check2 == false) {
		   
		   
		   System.out.println();
		   System.out.println();
		   System.out.println("************  The island became too small. The game is over!  ************");
		   System.out.println();
		   System.out.println(player1.getName() + " has " + player1.getScore() + " points");
		   System.out.println(player2.getName() + " has " + player2.getScore() + " points");
		   //Choosing winner.
		   if (player1.getScore() > player2.getScore()) {

			   System.out.println();
			   System.out.println(player1.getName() + " won the game!");
			   System.out.println();
			   System.out.println("Winner-winner, chicken-dinner!!!");

		   } else if (player1.getScore() < player2.getScore()) {

			   System.out.println();
			   System.out.println(player2.getName() + " won the game!");
			   System.out.println();
			   System.out.println("Winner-winner, chicken-dinner!!!");
			   
		   } else {

			   System.out.println("Nobody won the game! Both players have the same score!");
			
		   } 
		
	   }else if (check1 == true) {
		
		   System.out.println("\n\n*********** BAAAAAAAAAAAAAAANG!!! ***********\n\n");
		   System.out.println("Player 1 shoted Player 2! It is fatal! Game Over!");
			
		
	   }else {
		
		   System.out.println("\n\n*********** BAAAAAAAAAAAAAAANG!!! ***********\n\n");
		   System.out.println("Player 2 shoted Player 1! It is fatal! Game Over!");
		
	   }
	   
 	} 
 	
}
