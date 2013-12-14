package messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import base.Abonent;
import base.Address;
import base.AddressService;
import base.MessageSystem;
import base.Msg;




public class MessageSystemImpl implements MessageSystem{
	private Map<ConcurrentLinkedQueue<Address>, ConcurrentLinkedQueue<Msg>> messages = 
														new HashMap<ConcurrentLinkedQueue<Address>, ConcurrentLinkedQueue<Msg>>();

	
	private AddressService addressService = new AddressServiceImpl();
	
	public void addService (Abonent abonent){
		addressService.setAddress(abonent);
		ConcurrentLinkedQueue<Address> queueAddressOfService 
										= addressService.getQueueAddressOfService(abonent);
		messages.put(queueAddressOfService, new ConcurrentLinkedQueue<Msg>());
		
	}
	public void sendMessage ( Msg message ){

		for (ConcurrentLinkedQueue<Address> queue : messages.keySet()) {
			
				if (queue.contains(message.getTo())){			
					Queue <Msg> messageQueue = messages.get(queue);//.add(message);
					messageQueue.add(message);
				}
			}
		}

	
	public void execForAbonent(Abonent abonent) {

		Queue<Msg> messageQueue = messages.get(addressService.getQueueAddressOfService(abonent));
		if ( messageQueue == null ){
			return;
		}
		while (!messageQueue.isEmpty() && !abonent.getAddress().isThreadUsed()){	
			if (messageQueue.peek() == null) return;
					messageQueue.poll().exec(abonent);							
		}
			
		
		
	}
	public AddressService getAddressService(){
		return addressService;
	}

}
