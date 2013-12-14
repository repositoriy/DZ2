package gameMechanics;

import java.util.List;

import base.Abonent;

public interface GameMechanic extends Abonent {
	 void setGamerNames( List<Integer> usersId);
	 void processMessages(int userId, int countClicks);
}
