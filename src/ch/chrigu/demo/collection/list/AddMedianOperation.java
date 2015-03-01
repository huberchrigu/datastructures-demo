package ch.chrigu.demo.collection.list;

import java.util.List;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public class AddMedianOperation implements ListOperation {
    @Override
    public void execute(Integer i, List l) {
        l.add(l.size() / 2, i);
    }
}
