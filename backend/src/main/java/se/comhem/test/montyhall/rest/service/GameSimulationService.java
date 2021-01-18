package se.comhem.test.montyhall.rest.service;

import org.springframework.stereotype.Service;
import se.comhem.test.montyhall.model.PlayingStrategy;
import se.comhem.test.montyhall.model.SimulationResult;
import se.comhem.test.montyhall.service.GameSession;

import java.util.stream.LongStream;

@Service
public class GameSimulationService {

    public SimulationResult simulate(long iterations, PlayingStrategy playingStrategy) {
        var simulationResult = new SimulationResult();
        simulationResult.setNbrOfSimulations(iterations);
        simulationResult.setSelectedStrategy(playingStrategy);
        LongStream.range(0, iterations).forEach(session -> {
            var game = new GameSession();
            var gameLog = game.simulate(playingStrategy);
            simulationResult.registerResult(gameLog);
        });
        return simulationResult;
    }


}
