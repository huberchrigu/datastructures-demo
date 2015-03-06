package ch.chrigu.demo.types;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A linked list can be instantiated without params only.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class LinkedListType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new LinkedList<T>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        throw new UnsupportedOperationException("The linked list types does not support initial capacity");
    }

    @Override
    public boolean allowsCapacityParam() {
        return false;
    }
}
