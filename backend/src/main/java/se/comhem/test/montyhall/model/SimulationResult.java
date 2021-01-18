package se.comhem.test.montyhall.model;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class SimulationResult {

    private String host = "Monty Hall";
    private long nbrOfSimulations;
    private PlayingStrategy selectedStrategy;

    private long won;

    public void registerResult(GameLog gameLog) {
        if (gameLog.isWinningDeal()) {
            won++;
        }
    }

    public long getLost() {
        return (nbrOfSimulations - won);
    }

    public double getWinsInPercent() {
        return ((won * 100d) / nbrOfSimulations);
    }

    public double getLostInPercent() {
        return ((getLost() * 100d) / nbrOfSimulations);
    }
}
