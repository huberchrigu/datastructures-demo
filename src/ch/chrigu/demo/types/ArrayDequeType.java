package ch.chrigu.demo.types;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * Provides creation of ArrayDeques.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class ArrayDequeType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new ArrayDeque<>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new ArrayDeque<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
