package se.comhem.test.montyhall.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import se.comhem.test.montyhall.model.PlayStrategy;
import se.comhem.test.montyhall.rest.GameSimulationController;
import se.comhem.test.montyhall.rest.service.GameSimulationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
public class ApplicationExceptionHandlerTest {

    private MockMvc mvc;

    @Mock
    private GameSimulationService service;

    private GameSimulationController controller;

    @BeforeEach
    public void setUp() {
        controller = new GameSimulationController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ApplicationExceptionHandler())
                .build();
    }

    @Test
    public void testSimulate_ShouldHandleRuntimeException() throws Exception {
        Mockito.when(service.simulate(1, PlayStrategy.STICK_TO_ORIGINAL_DOOR))
                .thenThrow(new NullPointerException("x is null"));

        MockHttpServletResponse response = mvc.perform(get("/api/v1/game/simulate?iterations=1")).andReturn().getResponse();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

