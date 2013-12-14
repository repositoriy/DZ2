package gameMechanics;

import base.AccountService;
import base.Address;
import messageSystem.MsgToAccountService;

public class MsgSaveResult extends MsgToAccountService{

	private int id;
	private int countClicks;
	public MsgSaveResult(Address from, Address to, int id, int countClicks ){
		super(from, to);
		this.id = id;
		this.countClicks = countClicks;
	}
	public void exec(AccountService accountService) {
		accountService.saveResult(id, countClicks);
		
	}

}
