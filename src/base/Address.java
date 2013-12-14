package base;

import java.util.concurrent.atomic.AtomicInteger;

public class Address {
	private static AtomicInteger abonentIdCreator = new AtomicInteger();
	final private int abonentId;
	private boolean threadUsed = false;
	
	public Address(){
		this.abonentId = abonentIdCreator.incrementAndGet();
	
	}
	public int hashCode(){
		return abonentId;
	}
	public boolean isThreadUsed() {
		return threadUsed;
	}
	public void setThreadUsed(boolean threadUsed) {
		this.threadUsed = threadUsed;
	}
	
}
