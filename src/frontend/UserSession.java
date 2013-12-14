package frontend;


import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;


public class UserSession {
	
	private Integer sessionId ;
	private String UserName;
	private Integer UserId;
	private static AtomicInteger sessionIdGenerator = new AtomicInteger();
	private boolean inProgress = false;
	private int clicks;
	private boolean winner;
	private String victory;
	private Calendar lastVisit;
	private int bestCountClicks;

	
	
	
	public UserSession(){
		this.sessionId = sessionIdGenerator.incrementAndGet();
	}
	public UserSession( int value){
		this.sessionId = value;
	}
	public int hashCode() {
		return sessionId;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		this.UserName = userName;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		this.UserId = userId;
	}
	public boolean isInProgress() {
		return inProgress;
	}
	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public String getVictory() {
		return victory;
	}
	public void setVictory(String victory) {
		this.victory = victory;
	}
	public Calendar getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Calendar lastVisit) {
		this.lastVisit = lastVisit;
	}
	public int getBestCountClicks() {
		return bestCountClicks;
	}
	public void setBestCountClicks(int bestCountClicks) {
		this.bestCountClicks = bestCountClicks;
	}
}
