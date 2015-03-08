package ch.chrigu.datastructures.demo.types;


import java.util.Collection;

/**
 * A instances types describes any supported instances.
 * Its main purpose is creating an instance of this instances types.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public interface CollectionType<T> {

    Collection<T> createInstance();

    boolean allowsNoParams();

    Collection<T> createInstance(int capacity);

    boolean allowsCapacityParam();
}
