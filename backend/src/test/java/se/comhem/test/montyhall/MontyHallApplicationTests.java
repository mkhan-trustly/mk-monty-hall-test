package se.comhem.test.montyhall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.comhem.test.montyhall.rest.GameSimulationController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MontyHallApplicationTests {

	@Autowired
	private GameSimulationController gameSimulationController;

	@Test
	public void contextLoads() {
		assertThat(gameSimulationController).isNotNull();
	}

}
