package ch.chrigu.demo.collection;

import ch.chrigu.demo.Operation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Adds all items of the <code>secondCollection</code> to the collection.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public class AddAllOperation implements Operation {
    private Collection<Integer> secondCollection = new ArrayList<Integer>();
    public void execute(Collection<Integer> secondCollection, Collection collection) {
        secondCollection.addAll(secondCollection);
    }
}
