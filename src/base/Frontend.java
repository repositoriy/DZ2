package base;

import java.util.Calendar;




public interface Frontend extends Abonent {
	
	MessageSystem getMessageSystem();
	void updateUserId( int  sessionId,  int userId, int bestCountClick, Calendar lastVisit);
	boolean isGameActivity();
	void setGameActivity(boolean gameStarted);
	void setResult (  Integer  userId, int count, String victory);
	
}
