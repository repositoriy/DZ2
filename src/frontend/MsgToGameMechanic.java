package frontend;

import gameMechanics.GameMechanic;
import base.Abonent;

import base.Address;
import base.Msg;

public abstract class MsgToGameMechanic extends Msg {
	
	public   MsgToGameMechanic (Address from, Address to ){
		super(from, to);
	} 
		
	
	public void exec (Abonent abonent){
		if ( abonent instanceof GameMechanic ){
			exec((GameMechanic)abonent);
		}
		
	}
	
	public abstract void exec ( GameMechanic gameMechanic);	

}
