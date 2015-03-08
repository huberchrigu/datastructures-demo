package ch.chrigu.datastructures.demo.operations.collection;

import ch.chrigu.datastructures.demo.operations.Operation;

import java.util.Collection;

/**
 * An operations that can be taken on a instances.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public interface CollectionOperation extends Operation {
    void execute(Integer i, Collection collection);
}
