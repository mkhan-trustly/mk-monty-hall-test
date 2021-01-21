package se.comhem.test.montyhall.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.comhem.test.montyhall.model.Door;
import se.comhem.test.montyhall.model.GameOutcome;
import se.comhem.test.montyhall.model.PlayingStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GameSessionTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        this.game = new GameSession();
    }

    @AfterEach
    public void tearDown() {
        game = null;
    }

    @Test
    public void testWinningDoor() {
        Door door = game.getWinningDoor();
        assertThat(door.hasWinningDeal());
    }

    @Test
    public void testSelectRandomDoor() {
        Door door = game.selectRandomDoor();
        assertThat(door.isAvailable());
    }

    @Test
    public void testOpenDoorForHost() {
        Door door = game.openDoorForHost();
        assertThat(door.isAvailable());
        assertThat(door.hasWinningDeal()).isFalse();
        assertThat(door.isChosenByPlayer()).isFalse();
    }

    @Test
    public void testGetTheLastDoor() {
        Door door = game.getTheLastDoor();
        assertThat(door.isAvailable());
        assertThat(door.isChosenByPlayer()).isFalse();
    }

    @Test
    public void testGetPlayerSelectedDoor_should_throw_exception_if_called_b4_selection() {
        assertThrows(IllegalStateException.class, () -> game.getPlayerSelectedDoor());
    }

    @Test
    public void testGetPlayerSelectedDoor() {
        game.selectRandomDoor().registerSelectedByPlayer();
        Door door = game.getPlayerSelectedDoor();
        assertThat(door.isChosenByPlayer());
    }

    @Test
    public void testSwitchToDoor_player_should_initiate_the_game() {
        assertThrows(IllegalStateException.class, () -> {
            Door door = game.selectRandomDoor();
            game.switchToDoor(door);
        });
    }

    @Test
    public void testSwitchToDoor_should_not_allow_switching_to_host_opened_door() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.selectRandomDoor().registerSelectedByPlayer();
            Door hostDoor = game.openDoorForHost().open();
            game.switchToDoor(hostDoor);
        });
    }

    @Test
    public void testSwitchToDoor_previous_door_next_door_should_not_be_same() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.selectRandomDoor().registerSelectedByPlayer();
            Door nextDoor = game.getPlayerSelectedDoor();
            game.switchToDoor(nextDoor);
        });
    }

    @Test
    public void testSimulate_stick_to_initial_door() {
        GameOutcome gameOutcome = game.simulate(PlayingStrategy.STICK_TO_INITIAL_DOOR);
        assertThat(gameOutcome).isNotNull();
    }

    @Test
    public void testSimulate_switch_door() {
        GameOutcome gameOutcome = game.simulate(PlayingStrategy.SWITCH_DOOR);
        assertThat(gameOutcome).isNotNull();
    }
}
