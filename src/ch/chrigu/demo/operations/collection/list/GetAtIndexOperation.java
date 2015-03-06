package ch.chrigu.demo.operations.collection.list;

import java.util.List;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public class GetAtIndexOperation implements ListOperation {
    @Override
    public void execute(Integer i, List l) {
        l.get((int) i);
    }
}
