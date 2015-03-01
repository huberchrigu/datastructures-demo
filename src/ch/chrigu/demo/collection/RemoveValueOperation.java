package ch.chrigu.demo.collection;

import java.util.Collection;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public class RemoveValueOperation implements CollectionOperation {
    public void execute(Integer i, Collection c) {
        c.remove(i);
    }
}
