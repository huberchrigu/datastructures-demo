package ch.chrigu.demo.types;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * A linked hash set can be instantiated with or without inital capacity.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class LinkedHashSetType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new LinkedHashSet<T>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new LinkedHashSet<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
