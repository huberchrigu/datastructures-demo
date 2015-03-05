package ch.chrigu.demo.collection.type;


import java.util.Collection;

/**
 * A collection type describes any supported collection.
 * Its main purpose is creating an instance of this collection type.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public interface CollectionType<T> {

    Collection<T> createInstance();

    boolean allowsNoParams();

    Collection<T> createInstance(int capacity);

    boolean allowsCapacityParam();
}
