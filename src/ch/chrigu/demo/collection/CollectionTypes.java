package ch.chrigu.demo.collection;

import ch.chrigu.demo.Operation;
import ch.chrigu.demo.collection.list.ListOperation;
import ch.chrigu.demo.ui.CollectionType;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Maintains a list of created collections. On these, actions can be performed.
 * TODO: Refactoring: Separation between types and instances, include observable list for FX.
 *
 * Created by Christoph Huber on 02.01.2015.
 */
public class CollectionTypes {
    public static final String[] TYPES = new String[] {"ArrayList", "LinkedList", "Vector", "ArrayQueue", "ArrayDeque",
            "HashSet", "TreeSet", "LinkedHashSet"};

    public void clearAll() {
        for (Collection c : allDatastructures.values()) {
            c.clear();
        }
    }

    public void remove(CollectionType item) {
        allDatastructures.remove(item.getName());
    }

    public void add(String type) {
        switch(type) {
            case "ArrayList":
                allDatastructures.put("List ArrayList", new ArrayList<Integer>());
                break;
            case "LinkedList":
                allDatastructures.put("List/Queue LinkedLIst", new LinkedList<Integer>());
                break;
            case "ArrayQueue":
                allDatastructures.put("Queue ArrayQueue", new ArrayQueue<Integer>(0));
                break;
            case "ArrayDeque":
                allDatastructures.put("Queue ArrayDeque", new ArrayDeque<Integer>());
                break;
            case "HashSet":
                allDatastructures.put("Set HashSet", new HashSet<Integer>());
                break;
            case "TreeSet":
                allDatastructures.put("Set TreeSet", new TreeSet<Integer>());
                break;
            case "LinkedHashSet":
                allDatastructures.put("Set LinkedHashSet", new LinkedHashSet<Integer>());
                break;
            case "Vector":
                allDatastructures.put("List Vector", new Vector<Integer>());
                break;
            default:
                throw new IllegalStateException(type + " is not a known type");
        }
    }

    public enum DataSetGenerator {RANDOM_VALUE, RANDOM_INDEX, ALL_ZEROS}

    private Map<String, Collection<Integer>> allDatastructures;

    public CollectionTypes() {
        this.allDatastructures = new TreeMap<String, Collection<Integer>>();
        this.allDatastructures.put("List LinkedList", new LinkedList<Integer>());
        this.allDatastructures.put("List ArrayList", new ArrayList<Integer>());
        this.allDatastructures.put("Set HashSet", new HashSet<Integer>());
        this.allDatastructures.put("Set TreeSet", new TreeSet<Integer>());
        this.allDatastructures.put("Set LinkedHashSet", new LinkedHashSet<Integer>());
        // this.allDatastructures.put("List ArrayList (sync)", Collections.synchronizedList(new ArrayList<Integer>()));
        // this.allDatastructures.put("List ArrayList (capacity of 10'000)", new ArrayList<Integer>());
    }

    public Map<String, Measurement> performCollectionOperation(int n, Operation operation, DataSetGenerator datasetGenerator) {
        Map<String, Measurement> result = new TreeMap<String, Measurement>();

        Integer[] dataSet = generateDataSet(n, datasetGenerator);

        for (Map.Entry<String, Collection<Integer>> entry : this.allDatastructures.entrySet()) {
            String name = entry.getKey();
            Collection c = entry.getValue();
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
                throw new IllegalStateException("Unknown operation type");
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

    public Collection<CollectionType> getMetadata() {
        return allDatastructures.keySet().stream().map(name -> new CollectionType(name)).collect(Collectors.toList());
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
