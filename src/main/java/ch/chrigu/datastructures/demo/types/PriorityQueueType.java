package ch.chrigu.datastructures.demo.types;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * A priority queue can be created with or without initial capacity parameter.
 *
 * Created by Christoph Huber on 07.03.2015.
 */
public class PriorityQueueType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new PriorityQueue<>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new PriorityQueue<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
