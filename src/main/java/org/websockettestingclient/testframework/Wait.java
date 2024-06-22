package org.websockettestingclient.testframework;

public class Wait extends BasePredicate {

    private final long time;

    public Wait(long time) {
        this.time = time;
    }

    public void doWait() throws InterruptedException {
        Thread.sleep(time);
    }
}
