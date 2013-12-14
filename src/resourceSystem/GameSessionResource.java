package resourceSystem;

public class GameSessionResource implements Resource {
	private final int  countGamers;
	private final long time;
	
	public GameSessionResource(long time ,int countGamers){
		this.countGamers = countGamers;
		this.time = time;
	}
	public GameSessionResource (){
		this.countGamers = 0;
		this.time		 = 0;
	}
	public int getCountGamers(){
		return countGamers;
	}
	public long getTime(){
		return time;
	}
	
	
	
}
