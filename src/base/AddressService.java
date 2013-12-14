package base;

import java.util.concurrent.ConcurrentLinkedQueue;



public interface AddressService {
	
	public Address getAddress(Class<?> abonentClass) ;
	void setAddress(Abonent abonent);
	ConcurrentLinkedQueue<Address> getQueueAddressOfService (Abonent abonent);
	
}
