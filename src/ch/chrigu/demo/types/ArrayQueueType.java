package ch.chrigu.demo.types;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Collection;

/**
 * Provides creation of {@link ArrayQueue} instances.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class ArrayQueueType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        throw new UnsupportedOperationException("ArrayQueue cannot be initiated without parameters");
    }

    @Override
    public boolean allowsNoParams() {
        return false;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new ArrayQueue<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
