package ch.chrigu.datastructures.demo.operations.collection.list;

import java.util.List;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public class RemoveAtIndexOperation implements ListOperation {
    @Override
    public void execute(Integer i, List l) {
        l.remove((int) i);
    }
}