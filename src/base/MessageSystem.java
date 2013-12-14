package base;


public interface MessageSystem {
	 void addService (Abonent abonent);
	 void sendMessage ( Msg message );
	 void execForAbonent(Abonent abonent);
	 AddressService getAddressService();

}
