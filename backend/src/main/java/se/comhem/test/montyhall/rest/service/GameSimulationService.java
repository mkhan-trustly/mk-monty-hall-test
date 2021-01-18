package se.comhem.test.montyhall.rest.service;

import org.springframework.stereotype.Service;
import se.comhem.test.montyhall.model.PlayStrategy;
import se.comhem.test.montyhall.model.SimulationResult;
import se.comhem.test.montyhall.service.GameSession;

import java.util.stream.LongStream;

@Service
public class GameSimulationService {

    public SimulationResult simulate(long iterations, PlayStrategy playStrategy) {
        SimulationResult simulationResult = new SimulationResult();
        simulationResult.setNbrOfSimulations(iterations);
        simulationResult.setSelectedStrategy(playStrategy);
        LongStream.range(0, iterations).forEach(session -> {
            GameSession game = new GameSession();
            PlayStrategy winningStrategy = game.simulate();
            var gameData = simulationResult.getDataForStrategy(winningStrategy);
            gameData.registerWin();
        });
        return simulationResult;
    }


}
