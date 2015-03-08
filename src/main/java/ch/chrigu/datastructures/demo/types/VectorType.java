package ch.chrigu.datastructures.demo.types;

import java.util.Collection;
import java.util.Vector;

/**
 * Provides creation of Vector instances.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class VectorType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new Vector<>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new Vector<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
