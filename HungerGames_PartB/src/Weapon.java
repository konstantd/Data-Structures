// Class for Weapon. It contains Setters and Getters.

public class Weapon {
	
	int Weapon_id;
	int Weapon_x;
	int Weapon_y;
	int playerId;
	String Weapon_type;
		
	//Empty Constructor.
	public Weapon() {
		
		Weapon_id = 0;
		Weapon_x = 0;
		Weapon_y = 0;
		playerId = 0;
		Weapon_type = "";	
	}
	
	//Constructor with arguments.
	public Weapon(int id, int x, int y, int pid, String t) {
		
		Weapon_id = id;
		Weapon_x = x;
		Weapon_y = y;
		playerId = pid;
		Weapon_type = t;	
	}
	
	//Constructor with object.
	public Weapon(Weapon w) {
		
		this.Weapon_id = w.Weapon_id;
		this.Weapon_x = w.Weapon_x;
		this.Weapon_y = w.Weapon_y;
		this.playerId = w.playerId;
		this.Weapon_type = w.Weapon_type;		
	}
	
	//Setters.
	public void setWeaponId(int id) {
		
		Weapon_id = id;
	}
	
	public void setWeaponX(int x) {
		
		Weapon_x = x;
	}
	
	public void setWeaponY(int y) {
		
		Weapon_y = y;
	}
	
	public void setPlayerId(int pid) {
		
		playerId = pid;
	}

	public void setWeaponType(String t) {
		
		if ( t == "bow" || t == "pistol" || t == "sword" ) {
			
			Weapon_type = t;
			
		}else {
			
			System.out.println("Not a valid weapon. The weapon will be set equal to bow.");
			Weapon_type = "bow";
		}
	}
	
	//Getters.
	public int getWeaponId() {
		
		return Weapon_id;
	}
	
	public int getWeaponX() {
		
		return Weapon_x;
	}
	
	public int getWeaponY() {
		
		return Weapon_y;
	}
	
	public int getPlayerId() {
		
		return playerId;
	}
	
	public String getWeaponType() {
		
		return Weapon_type;
	}
	
}