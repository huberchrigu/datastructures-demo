package ch.chrigu.datastructures.demo.operations.collection;

import java.util.Collection;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public class AddLastOperation implements CollectionOperation {
    public void execute(Integer i, Collection c) {
        c.add(i);
    }
}
