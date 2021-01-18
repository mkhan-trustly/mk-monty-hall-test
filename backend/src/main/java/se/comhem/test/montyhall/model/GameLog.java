package se.comhem.test.montyhall.model;

import lombok.Data;

@Data
public class GameLog {

    private String playerFirstChoice;
    private String hostDoor;
    private String remainingDoor;
    private PlayingStrategy playingStrategy;
    private String playerFinalChoice;
    private boolean winningDeal;
}
