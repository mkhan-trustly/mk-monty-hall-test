package se.comhem.test.montyhall.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Stats {

    private long winWithSwitch;
    private long winWithoutSwitch;

    public Stats() {
        super();
    }

    public void addToWinWithSwitch() {
        winWithSwitch++;
    }

    public void addToWinWithoutSwitch() {
        winWithoutSwitch++;
    }

    public double getPercentOfWinsWithSwitch(double count) {
        return (winWithSwitch * 100d)/count;
    }

    public double getPercentOfWinsWithoutSwitch(double count) {
        return (winWithoutSwitch * 100d)/count;
    }

    public void showResult(long count) {
        log.info("Total number of times the game was played: {}", count);
        log.info("Wins when player opted to switch boxes {}% Vs Wins when player did not switch boxes {}%",
                getPercentOfWinsWithSwitch(count), getPercentOfWinsWithoutSwitch(count));
    }
}
