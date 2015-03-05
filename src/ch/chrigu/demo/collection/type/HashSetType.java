package ch.chrigu.demo.collection.type;

import java.util.Collection;
import java.util.HashSet;

/**
 * A hash set can be instantiated with or without initial capacity.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class HashSetType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new HashSet<>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new HashSet<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
