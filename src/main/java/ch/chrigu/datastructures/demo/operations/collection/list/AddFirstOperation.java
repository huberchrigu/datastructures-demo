package ch.chrigu.datastructures.demo.operations.collection.list;

import java.util.List;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public class AddFirstOperation implements ListOperation {
    @Override
    public void execute(Integer i, List l) {
        l.add(0, i);
    }
}
