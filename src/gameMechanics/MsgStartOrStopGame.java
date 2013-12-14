package gameMechanics;


import base.Address;
import base.Frontend;
import messageSystem.MsgToFrontend;

public class MsgStartOrStopGame extends MsgToFrontend {
	
	private boolean gameActivity;
	
	public MsgStartOrStopGame( Address from, Address to, boolean gameActivity){
		super(from, to);
		this.gameActivity = gameActivity;		
	}
	
	public void exec(Frontend frontend) {
		frontend.setGameActivity(gameActivity);		
	}

}
