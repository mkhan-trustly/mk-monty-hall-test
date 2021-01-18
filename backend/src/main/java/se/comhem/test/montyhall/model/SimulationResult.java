package se.comhem.test.montyhall.model;

import lombok.Data;

import java.util.Arrays;

@Data
public class SimulationResult {

    private String host = "Monty Hall";
    private long nbrOfSimulations;
    private GameData[] statistics;
    private PlayStrategy selectedStrategy;

    public SimulationResult() {
        statistics = new GameData[PlayStrategy.values().length];
        statistics[0] = new GameData(PlayStrategy.STICK_TO_ORIGINAL_DOOR);
        statistics[1] = new GameData(PlayStrategy.SWITCHING_DOOR);
    }

    public GameData getDataForStrategy(PlayStrategy playStrategy) {
        return Arrays.stream(statistics)
                .filter(stats -> stats.getPlayStrategy() == playStrategy)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unable to find the statistics data for " + playStrategy));
    }

    @Data
    @SuppressWarnings("unused")
    public class GameData {
        private long won;
        private long lost;
        private PlayStrategy playStrategy;

        public GameData(PlayStrategy playStrategy) {
            this.playStrategy = playStrategy;
        }

        public void registerWin() {
            won++;
        }

        public long getLost() {
            return (nbrOfSimulations - won);
        }

        public double getWinsInPercentage() {
            return ((won * 100d) / nbrOfSimulations);
        }
    }

}
