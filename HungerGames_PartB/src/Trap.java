// Class for Trap. It contains Setters and Getters.

public class Trap {
	
	int Trap_id;
	int Trap_x;
	int Trap_y;
	String Trap_type;
	int Trap_points;
	
	//Empty Constructor.
	public Trap() {
		
		Trap_id = 0;
		Trap_x = 0;
		Trap_y = 0;    
		Trap_type = "";
		Trap_points = 0;
	}
			
	//Constructor with arguments.
	public Trap(int id, int x, int y, String t, int p) {
		
			Trap_id = id;
			Trap_x = x;
			Trap_y = y;
			Trap_type = t;
			Trap_points = p;
	}
	
	//Constructor with object.
	public Trap(Trap t) {
		
		this.Trap_id = t.Trap_id;
		this.Trap_x = t.Trap_x;
		this.Trap_y = t.Trap_y;
		this.Trap_points = t.Trap_points;	
		this.Trap_type = t.Trap_type;
	}
	
	//Setters.
	public void setTrapId(int id) {
		
		Trap_id = id;
	}
	
	public void setTrapX(int x) {
		
		Trap_x = x;
	}
	
	public void setTrapY(int y) {
		
		Trap_y = y;
	}
	
	public void setTrapPoints(int p) {
		
		Trap_points = p;
	}
	
	public void setTrapType(String t) {
		
		if ( t == "rope" || t == "animal" ) {
			
			Trap_type = t;
			
		}else {
			
			System.out.println("Not a valid trap. The trap will be set equal to rope.");
			Trap_type = "rope";
		}
	}
	
	//Getters.
	public int getTrapId() {
		
		return Trap_id;
	}
	
	public int getTrapX() {
		
		return Trap_x;
	}
	
	
	public int getTrapY() {
		
		return Trap_y;
	}
	
	public int getTrapPoints(){
		
		return Trap_points;
	}
	
	public String getTrapType(){
		
		return Trap_type;
	}
	
}