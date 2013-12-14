package gameMechanics;

import base.Address;
import base.Frontend;
import messageSystem.MsgToFrontend;

public class MsgVictory extends MsgToFrontend {

	private Integer userId;
	private int count;
	private String victoryString;
	
	public MsgVictory( Address from, Address to, int userId, int count, String victoryString){
		super(from, to);
		this.count = count;
		this.userId = userId;
		this.victoryString = victoryString;
		
		
	}
	public void exec(Frontend frontend) {
		frontend.setResult(userId, count, victoryString);
		
	}

}
