package co.com.bancolombia.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRestTest {

    AuthenticationRestController apiRest = new AuthenticationRestController();

    @Test
    void apiRestTest() {
        var response = apiRest.commandName();
        assertEquals("", response);
    }
}
