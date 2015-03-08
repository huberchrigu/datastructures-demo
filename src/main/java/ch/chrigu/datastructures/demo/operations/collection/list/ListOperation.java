package ch.chrigu.datastructures.demo.operations.collection.list;

import ch.chrigu.datastructures.demo.operations.Operation;

import java.util.List;

/**
 * An operations that can be performed on lists only.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public interface ListOperation extends Operation {
    public void execute(Integer i, List list);
}
