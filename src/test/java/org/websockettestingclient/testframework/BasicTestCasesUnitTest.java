package org.websockettestingclient.testframework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.websockettestingclient.core.stomp.model.StompServerFrameType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class BasicTestCasesUnitTest {

    @Test
    public void testBasicCondition() throws URISyntaxException {
        TestCase testCase = new TestCase()
                .connectingTo(new URI("ws://localhost:8080/websocket-registry-endpoint"))
                .when(new ActionSubscribe("/topic/listener/1"))
                .waitFor(5000L)
                .expect(new OutcomeMessageReceived(
                        messages -> messages.size() > 0 && Objects.equals(StompServerFrameType.MESSAGE, messages.getLast().getType())
                ));

        Assertions.assertTrue(testCase.runTest());
    }

}
