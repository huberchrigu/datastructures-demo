package ch.chrigu.demo.instances;

import ch.chrigu.demo.instances.options.CollectionOptions;
import ch.chrigu.demo.types.CollectionType;
import ch.chrigu.demo.types.CollectionTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Maintains a list of created collections. On these, actions can be performed.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public class CollectionInstances {

    private ObservableList<CollectionInstance<Integer>> collectionInstances;

    public CollectionInstances() {
        this.collectionInstances = FXCollections.observableArrayList();
        add(CollectionTypes.LINKED_LIST);
        add(CollectionTypes.ARRAY_LIST);
        add(CollectionTypes.HASH_SET);
        add(CollectionTypes.TREE_SET);
        add(CollectionTypes.LINKED_HASH_SET);
        add(CollectionTypes.ARRAY_LIST, new CollectionOptions().synchronizedCollection());
        add(CollectionTypes.ARRAY_LIST, new CollectionOptions().capacity(10000));
    }

    public void add(CollectionType<Integer> collectionType, CollectionOptions collectionOptions) {
        collectionInstances.add(new CollectionInstance<>(collectionType, collectionOptions));
    }
    private void add(CollectionType<Integer> collectionType) {
        collectionInstances.add(new CollectionInstance<>(collectionType));
    }

    public ObservableList<CollectionInstance<Integer>> getInstances() {
        return collectionInstances;
    }
}
