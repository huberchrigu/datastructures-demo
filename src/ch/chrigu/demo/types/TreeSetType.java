package ch.chrigu.demo.types;

import java.util.Collection;
import java.util.TreeSet;

/**
 * A tree set can be instantiated without params only.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class TreeSetType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new TreeSet<T>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        throw new UnsupportedOperationException("Tree Set cannot be initialized with initial capacity");
    }

    @Override
    public boolean allowsCapacityParam() {
        return false;
    }
}
