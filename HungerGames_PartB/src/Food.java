// Class for Food. It contains Setters and Getters.

public class Food {
	
	int Food_id;
	int Food_x;
	int Food_y;
	int Food_points;
	
	//Empty Constructor.
	public Food() {
		
		Food_id = 0;
		Food_x = 0;
		Food_y = 0;    
		Food_points = 0;
	}
	
	//Constructor with arguments.
	public Food(int id, int x, int y, int p) {
		
		Food_id = id;
		Food_x = x;
		Food_y = y;
		Food_points = p;
	}
	
	//Constructor with object.
	public Food(Food f) {
		
		this.Food_id = f.Food_id;
		this.Food_x = f.Food_x;
		this.Food_y = f.Food_y;
		this.Food_points = f.Food_points;	
	}
	
	//Setters.
	public void setFoodId(int id) {
		
		Food_id = id;
	}
	
	public void setFoodX(int x) {
		
		Food_x = x;
	}
	
	public void setFoodY(int y) {
		
		Food_y = y;
	}
	
	public void setFoodPoints(int p) {
		
		Food_points = p;
	}
	
	//Getters.
	public int getFoodId() {
		
		return Food_id;
	}
	
	public int getFoodX () {
		return Food_x;
	}
	
	public int getFoodY () {
		return Food_y;
	}
	
	public int getFoodPoints() {
		return Food_points;
	}
}