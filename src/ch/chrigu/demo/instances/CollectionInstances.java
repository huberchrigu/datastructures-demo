package ch.chrigu.demo.instances;

import ch.chrigu.demo.operations.Operation;
import ch.chrigu.demo.operations.collection.AddAllOperation;
import ch.chrigu.demo.operations.collection.CollectionOperation;
import ch.chrigu.demo.operations.collection.list.ListOperation;
import ch.chrigu.demo.instances.options.CollectionOptions;
import ch.chrigu.demo.types.CollectionType;
import ch.chrigu.demo.types.CollectionTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Maintains a list of created collections. On these, actions can be performed.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public class CollectionInstances {

    public enum DataSetGenerator {RANDOM_VALUE, RANDOM_INDEX, ALL_ZEROS}

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

    public Map<String, Measurement> performCollectionOperation(int n, Operation operation, DataSetGenerator datasetGenerator) {
        Map<String, Measurement> result = new TreeMap<>();

        Integer[] dataSet = generateDataSet(n, datasetGenerator);

        for (CollectionInstance<Integer> collectionInstance : this.collectionInstances) {
            String name = collectionInstance.getName();
            Collection<Integer> c = collectionInstance.getInstance();
            if (operation instanceof ListOperation && !(c instanceof List)) {
                continue;
            }

            Measurement measurement = new Measurement();
            measurement.start();
            if (operation instanceof CollectionOperation) {
                executeCollectionOperations(n, (CollectionOperation) operation, c, dataSet);
            } else if (operation instanceof ListOperation) {
                executeListOperation(n, (ListOperation) operation, (List) c, dataSet);
            } else if (operation instanceof AddAllOperation) {
                c.addAll(Arrays.asList(dataSet));
            } else {
                throw new IllegalStateException("Unknown operations types");
            }
            measurement.stop();
            result.put(name, measurement);
        }
        return result;
    }

    private void executeListOperation(int n, ListOperation operation, List list, Integer[] dataSet) {
        for (int i = 0; i < n; i++) {
            operation.execute(dataSet[i], list);
        }
    }

    private void executeCollectionOperations(int n, CollectionOperation operation, Collection c, Integer[] dataSet) {
        for (int i = 0; i < n; i++) {
            operation.execute(dataSet[i], c);
        }
    }

    private Integer[] generateDataSet(int n, DataSetGenerator datasetGenerator) {
        Integer[] result = new Integer[n];
        for (int i = 0; i < n; i++) {
            switch(datasetGenerator) {
                case RANDOM_VALUE:
                    result[i] = (int) (Math.random() * Integer.MAX_VALUE);
                    break;
                case ALL_ZEROS:
                    result[i] = 0;
                    break;
                case RANDOM_INDEX:
                    result[i] = (int) (Math.random() * n);
                    break;
            }
        }
        return result;
    }

    public ObservableList<CollectionInstance<Integer>> getInstances() {
        return collectionInstances;
    }

    public static class Measurement {
        private long start = -1;
        private long duration = -1;

        public void start() {
            start = System.currentTimeMillis();
        }
        public void stop() {
            duration = System.currentTimeMillis() - start;
        }
        public long getTimeInMs() {
            if (start == -1 || duration == -1) {
                throw new IllegalStateException("Time measurement was not started or stopped");
            }
            return duration;
        }
    }
}
