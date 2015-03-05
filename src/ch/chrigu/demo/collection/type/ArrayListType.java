package ch.chrigu.demo.collection.type;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides creation of ArrayLists.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class ArrayListType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new ArrayList<>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new ArrayList<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
