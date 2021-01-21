package se.comhem.test.montyhall.service;

import se.comhem.test.montyhall.model.Door;
import se.comhem.test.montyhall.model.GameOutcome;
import se.comhem.test.montyhall.model.PlayingStrategy;

public interface Game {

    Door getWinningDoor();
    Door selectRandomDoor();
    Door openDoorForHost();
    Door getTheLastDoor();
    Door getPlayerSelectedDoor();
    Door switchToDoor(Door nextDoor);
    GameOutcome simulate(PlayingStrategy playingStrategy);
}
