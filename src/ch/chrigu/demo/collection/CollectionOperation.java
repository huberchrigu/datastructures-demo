package ch.chrigu.demo.collection;

import ch.chrigu.demo.Operation;

import java.util.Collection;

/**
 * An operation that can be taken on a collection.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public interface CollectionOperation extends Operation {
    void execute(Integer i, Collection collection);
}
