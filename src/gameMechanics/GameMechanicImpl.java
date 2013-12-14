package gameMechanics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import databaseService.DatabaseServiceImpl;

import resourceSystem.GameSessionResource;

import frontend.FrontendImpl;

import utils.TimeHelper;



import base.Address;
import base.MessageSystem;


public class GameMechanicImpl implements Runnable, GameMechanic {

	private MessageSystem ms;
	private GameSessionResource resource;
	private Address address;
	private boolean gameActivity ;
	private long gameTime;
	
	private final Map<Integer, GameSession> userToSessions = new HashMap<Integer, GameSession>();

	
	public GameMechanicImpl ( MessageSystem ms , GameSessionResource resource){
		this.ms = ms;
		this.address = new Address();
		this.resource = resource;
		ms.addService(this);
	}
	public void run()  {		
		while (true){			
			ms.execForAbonent(this);
			doGameMechanicStep();
			TimeHelper.sleep(110);				
		}		
}

	public Address getAddress() {	
		return address;
	}
	public MessageSystem getMessageSystem(){
		return ms;
	}
	public void processMessages(int userId, int countClicks){		
		userToSessions.get(userId).setCountClicks(countClicks);	
	}

	public void doGameMechanicStep(){
					
		if ( resource == null ){
			return;
		}
		if (System.currentTimeMillis() - gameTime >= resource.getTime() && ((gameTime !=0) && gameActivity)) {
			gameActivity = false;
			GameSession bestSession = Collections.max(userToSessions.values(), new Comparator<GameSession>() {
			
				public boolean equals(Object obj) {					
					return super.equals(obj);
				}
				
				@Override
				public int compare(GameSession o1, GameSession o2) {
					if (o1.getCountClicks() > o2.getCountClicks() ) return 1;
					if (o1.getCountClicks() < o2.getCountClicks() ) return -1;
					
					return 0;
				}
			});
			
			ms.sendMessage(new MsgVictory(this.address, ms.getAddressService().getAddress(FrontendImpl.class), bestSession.hashCode(), bestSession.getCountClicks(), "ÒÛ ÏÎÁÅÄÈÒÅËÜ"+" ÈÃÐÀ ÎÊÎÍ×ÅÍÀ"));
			for (GameSession badSession : userToSessions.values()) {
				if ( !badSession.equals(bestSession)){
					ms.sendMessage(new MsgVictory(this.address, ms.getAddressService().getAddress(FrontendImpl.class), badSession.hashCode(), badSession.getCountClicks(), "Òû ïðîèãðàâøèé" + " ÈÃÐÀ ÎÊÎÍ×ÅÍÀ" ));
				}
			}
			
			ms.sendMessage(new MsgStartOrStopGame(this.address, ms.getAddressService().getAddress(FrontendImpl.class) , false));
			for (GameSession session : userToSessions.values()){
				ms.sendMessage(new MsgSaveResult(this.address, ms.getAddressService().getAddress(DatabaseServiceImpl.class), session.hashCode(), session.getCountClicks()));
			}
		}

		
	}
	public void setGamerNames( List<Integer> usersId ){
		for (Integer id : usersId) {
			userToSessions.put(id, new GameSession(id));
		}
		if ( userToSessions.size() == resource.getCountGamers() ) this.gameTime = System.currentTimeMillis();
		gameActivity = true;
		ms.sendMessage(new MsgStartOrStopGame(this.address, ms.getAddressService().getAddress(FrontendImpl.class), true));
	}
}
