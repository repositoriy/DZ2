package frontend;

import java.util.Calendar;

import messageSystem.MsgToFrontend;
import base.Address;
import base.Frontend;

public class MsgUpdateUserId extends MsgToFrontend {

	final private int sessionId;
	final private int userId;
	final private int bestCountClick;
	final private Calendar lastVisit;

	public MsgUpdateUserId(Address from, Address to,  int userId, int sessionId, int bestCountClick, Calendar lastVisit) {
		super(from, to);
		
		this.sessionId = sessionId;
		this.userId = userId;
		this.bestCountClick = bestCountClick;
		this.lastVisit = lastVisit;
	}

	public void exec(Frontend frontend) {
		frontend.updateUserId(sessionId,  userId, bestCountClick,lastVisit);
	}	
}
