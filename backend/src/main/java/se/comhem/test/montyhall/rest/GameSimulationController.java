package se.comhem.test.montyhall.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.comhem.test.montyhall.model.PlayingStrategy;
import se.comhem.test.montyhall.model.SimulationResult;
import se.comhem.test.montyhall.rest.service.GameSimulationService;

@RestController
@RequestMapping(path = "/api/v1/game")
public class GameSimulationController {

    private GameSimulationService service;

    public GameSimulationController(GameSimulationService service) {
        this.service = service;
    }

    @GetMapping(path = "/simulate")
    public SimulationResult simulate(@RequestParam(defaultValue = "1") long iterations,
                                     @RequestParam(name = "playingStrategy",
                                             defaultValue = "STICK_TO_INITIAL_DOOR") PlayingStrategy playingStrategy) {
        return service.simulate(iterations, playingStrategy);
    }

}
