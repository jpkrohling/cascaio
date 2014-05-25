package com.cascaio.backend.v1.control.batch;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class BasicBatchCheckpoint implements Serializable {

    private AtomicInteger counter = new AtomicInteger(0);

    public int incrementAndGet() {
        return counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }

    @Override
    public String toString() {
        return "BasicBatchCheckpoint{" +
                "counter=" + counter.get() +
                '}';
    }
}
