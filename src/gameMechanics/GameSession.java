package gameMechanics;

public class GameSession {
	
	private final int userID;
	private int countClicks = 0;
	public GameSession ( int userID ){
		this.userID = userID;
	}
	public int hashCode(){
		return userID;		
	}

	public int getCountClicks() {
		return countClicks;
	}
	public void setCountClicks(int countClicks) {
		this.countClicks = countClicks;
	}


}
