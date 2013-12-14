package frontend;

import messageSystem.MsgToAccountService;
import base.AccountService;
import base.Address;

public class MsgGetUserId extends MsgToAccountService {
	
	private final String  userName;
	private final Integer sessionId;

	public MsgGetUserId(Address from, Address to, String userName, Integer sessionId) {
		super(from, to);
		this.userName  = userName;		
		this.sessionId = sessionId; 		
	}
	

	public void exec(AccountService accountService) {		
		accountService.getUserId(userName, sessionId);			
	}
		
}
