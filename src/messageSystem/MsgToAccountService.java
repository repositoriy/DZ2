package messageSystem;

import base.Abonent;
import base.AccountService;
import base.Address;
import base.Msg;

public abstract class MsgToAccountService extends Msg{

	public   MsgToAccountService (Address from, Address to ){
		super(from, to);
	} 
		
	
	public void exec (Abonent abonent){
		if ( abonent instanceof AccountService ){
			exec((AccountService)abonent);
		}
		
	}
	
	public abstract void exec ( AccountService accountService );	

}
