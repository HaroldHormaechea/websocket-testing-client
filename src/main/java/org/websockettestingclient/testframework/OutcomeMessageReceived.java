package org.websockettestingclient.testframework;

import org.websockettestingclient.core.stomp.model.StompServerFrame;

import java.util.LinkedList;
import java.util.function.Predicate;

public class OutcomeMessageReceived extends Outcome {

    public OutcomeMessageReceived(Predicate<LinkedList<StompServerFrame>> condition) {
        super(condition);
    }

}
