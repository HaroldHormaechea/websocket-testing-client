package org.websockettestingclient.testframework;

import org.websockettestingclient.core.stomp.model.StompServerFrame;

import java.util.LinkedList;
import java.util.function.Predicate;

public abstract class Outcome extends BasePredicate {
    private final Predicate<LinkedList<StompServerFrame>> condition;

    protected Outcome(Predicate<LinkedList<StompServerFrame>> condition) {
        this.condition = condition;
    }

    protected boolean check(LinkedList<StompServerFrame> inboundFramesReceived) {
        return condition.test(inboundFramesReceived);
    }
}
