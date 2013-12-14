package gameMechanics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import base.Address;
import frontend.MsgToGameMechanic;
import frontend.UserSession;

public class MsgSetGamerNames extends MsgToGameMechanic {

	private List<Integer> usersId;

	public MsgSetGamerNames(Address from, Address to, Collection <UserSession> gamerNames) {
		super(from, to);		
		usersId = new ArrayList<Integer>(gamerNames.size());
		for (UserSession userSession : gamerNames) {
			usersId.add(userSession.getUserId());
		}		
	}

	public void exec(GameMechanic gameMechanic) {
		gameMechanic.setGamerNames(this.usersId);
	}

}
