package accountService;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import resourceSystem.Resource;

import base.AccountService;
import base.Address;
import base.MessageSystem;


import frontend.FrontendImpl;
import frontend.MsgUpdateUserId;



import utils.TimeHelper;

public class AccountServiceImpl implements AccountService {
	
	private static final Map< String, Integer> userId = new HashMap<String, Integer>();

	private Address addressAS;
	private MessageSystem ms;
	private Resource resource;
	private static AtomicInteger userIdGenerator = new AtomicInteger();
	
	
	public AccountServiceImpl (MessageSystem ms, Resource resource){
		this.ms = ms;
		this.resource = resource;
		addressAS = new Address();
		//addressFrontend = ms.getAddressService().getAddress(FrontendImpl.class);
		ms.addService(this);
	}
	public void run(){
		while (true){
				ms.execForAbonent(this);				
				TimeHelper.sleep(1);
		}
	}

	public void getUserId(String userName, Integer sessionId ){
		this.getAddress().setThreadUsed(true);
		if (userId.get(userName) == null) 	userId.put(userName, userIdGenerator.incrementAndGet());
		System.out.println( "для этой сессии "+ sessionId +" для пользователя "+ userName+ " отработал AccountService "+ this.getAddress().hashCode() );
		this.getAddress().setThreadUsed(false);

	}
	
	public Address getAddress() {		
		return addressAS;
	}
	
	public MessageSystem getMessageSystem(){
		return ms;
	}
	@Override
	public void saveResult(int id, int countClicks) {
		// TODO Auto-generated method stub
		
	}
}
