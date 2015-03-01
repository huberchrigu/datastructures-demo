package ch.chrigu.demo.collection.list;

import ch.chrigu.demo.Operation;

import java.util.List;

/**
 * Created by Christoph Huber on 02.01.2015.
 */
public interface ListOperation extends Operation {
    public void execute(Integer i, List list);
}
