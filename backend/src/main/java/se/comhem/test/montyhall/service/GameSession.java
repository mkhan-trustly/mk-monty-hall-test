package se.comhem.test.montyhall.service;

import lombok.extern.log4j.Log4j2;
import se.comhem.test.montyhall.model.Door;
import se.comhem.test.montyhall.model.GameOutcome;
import se.comhem.test.montyhall.model.PlayingStrategy;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@Log4j2
public class GameSession implements Game {

    private final int NBR_OF_DOORS = 3;
    private final Door[] DOORS = new Door[NBR_OF_DOORS];
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public GameSession() {
        createDoors();
        randomizeWinningDoor();
    }

    private void addDoor(int index) {
        this.DOORS[index] = new Door(index + 1);
    }

    private void createDoors() {
        IntStream.range(0, NBR_OF_DOORS)
                .forEach(this::addDoor);
    }

    private void randomizeWinningDoor() {
        int doorIndex = RANDOM.nextInt(DOORS.length);
        if (doorIndex < 0 || doorIndex > DOORS.length) {
            throw new IllegalStateException("Expected randomizer to give a door index between 0-" + (DOORS.length - 1));
        }
        Door winningDoor = DOORS[doorIndex].replaceWithAWinningDeal();
        log.debug("Init#Winning door allocated to [Door {}]", winningDoor.getId());
    }

    public Door selectRandomDoor() {
        return DOORS[RANDOM.nextInt(DOORS.length)];
    }

    public Door openDoorForHost() {
        return Arrays.stream(DOORS).filter(Door::canBeOpenedByHost)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "All boxes are either opened or chosen by the player. Host never opens a box with a winning deal"));
    }

    public Door getTheLastDoor() {
        return Arrays.stream(DOORS).filter(Door::isAvailable).findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "There should be a door which is not yet opened!"));
    }

    public Door getPlayerSelectedDoor() {
        return Arrays.stream(DOORS).filter(Door::isChosenByPlayer).findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "There should be a door which was selected by the player!"));
    }

    public Door switchToDoor(Door nextDoor) {
        Door previousDoor = getPlayerSelectedDoor();
        if (previousDoor.equals(nextDoor)) {
            throw new IllegalArgumentException("both doors are same, try switching between two different doors");
        } else if (nextDoor.isNotAvailable()) {
            throw new IllegalArgumentException("try switching to a different door since the door " + nextDoor.getId() + " was already opened");
        }

        previousDoor.unmarkAsChosen();
        log.debug("Player opted to switch doors from {} to {}", previousDoor.getDisplayName(), nextDoor.getDisplayName());
        return nextDoor.registerSelectedByPlayer();
    }

    @Override
    public GameOutcome simulate(PlayingStrategy playingStrategy) {
        log.debug("Game starts here, strategy opted {}", playingStrategy);

        Door playersDoor = selectRandomDoor().registerSelectedByPlayer();
        log.debug("Player selected {}", playersDoor.getDisplayName());

        Door hostDoor = openDoorForHost().open();
        log.debug("Host opted to open {}", hostDoor.getDisplayName());

        Door remainingDoor = getTheLastDoor();
        log.debug("{} is the last door left", remainingDoor.getDisplayName());

        if (remainingDoor.equals(hostDoor) || remainingDoor.equals(playersDoor)) {
            throw new IllegalStateException("Remaining door cannot be hostDoor or playerDoor");
        }

        Door playerFinalChoice = playingStrategy == PlayingStrategy.STICK_TO_INITIAL_DOOR
                ? playersDoor : switchToDoor(remainingDoor);
        log.debug("Won the deal ({})? {}", playingStrategy, playerFinalChoice.hasWinningDeal());

        return playerFinalChoice.hasWinningDeal() ? GameOutcome.WON : GameOutcome.LOST;
    }

    public Door getWinningDoor() {
        return Arrays.stream(DOORS).filter(Door::hasWinningDeal).findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "There should be a door which was the winning deal!"));
    }
}
