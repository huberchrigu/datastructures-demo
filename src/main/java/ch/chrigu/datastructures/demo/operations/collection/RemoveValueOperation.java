package ch.chrigu.datastructures.demo.operations.collection;

import java.util.Collection;

/**
 * Executes {@link Collection#remove} for each value.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public class RemoveValueOperation implements CollectionOperation {
    public void execute(Integer i, Collection c) {
        c.remove(i);
    }
}
