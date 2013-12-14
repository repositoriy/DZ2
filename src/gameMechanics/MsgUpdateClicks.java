package gameMechanics;

import base.Address;
import frontend.MsgToGameMechanic;

public class MsgUpdateClicks extends MsgToGameMechanic{

	private int userId;
	private int countClicks;
	public MsgUpdateClicks (Address from, Address to, int userId, int countClicks){
		super(from, to);
		this.userId = userId;
		this.countClicks = countClicks;
		
	}
	public void exec(GameMechanic gameMechanic) {	
		gameMechanic.processMessages(userId, countClicks);
	}

}
