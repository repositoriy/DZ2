package base;


public interface AccountService extends Abonent{
	
	MessageSystem getMessageSystem();
	void getUserId(String userName, Integer sessionId );
	void saveResult(int id, int countClicks);
}
