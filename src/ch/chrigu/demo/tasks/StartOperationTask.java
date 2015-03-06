package ch.chrigu.demo.tasks;

import ch.chrigu.demo.instances.CollectionInstance;
import ch.chrigu.demo.instances.CollectionInstances;
import ch.chrigu.demo.operations.Operation;
import ch.chrigu.demo.operations.collection.AddAllOperation;
import ch.chrigu.demo.operations.collection.CollectionOperation;
import ch.chrigu.demo.operations.collection.list.ListOperation;
import ch.chrigu.demo.ui.MainButtons;
import javafx.concurrent.Task;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Task performing a {@link CollectionOperation} in a non-UI thread,
 * as this may take some time dependent on the data set.
 * <p>
 *     Calls <code>{@link MainButtons#enableAll()}</code> after operation has finished on each instance.
 * </p>
 *
 * Created by Christoph Huber on 06.03.2015.
 */
public class StartOperationTask extends Task<Integer> {

    private final int dataSetSize;
    private final Operation operation;
    private final DataSetGenerator dataSetGenerator;
    private final MainButtons mainButtons;
    private final CollectionInstances collectionInstances;

    public enum DataSetGenerator {RANDOM_VALUE, RANDOM_INDEX, ALL_ZEROS}

    public StartOperationTask(CollectionInstances collectionInstances, int dataSetSize, Operation operation, DataSetGenerator dataSetGenerator, MainButtons mainButtons) {
        this.collectionInstances = collectionInstances;
        this.dataSetSize = dataSetSize;
        this.operation = operation;
        this.dataSetGenerator = dataSetGenerator;
        this.mainButtons = mainButtons;
    }


    @Override
    public Integer call() {
        Integer[] dataSet = generateDataSet();
        List<Integer> dataSetList = Arrays.asList(dataSet);
        for (CollectionInstance<Integer> collectionInstance : this.collectionInstances.getInstances()) {
            Collection<Integer> c = collectionInstance.getInstance();
            if (operation instanceof ListOperation && !(c instanceof List)) {
                collectionInstance.resetLastMeasurementInMs();
                continue;
            }

            Measurement measurement = new Measurement();
            measurement.start();
            if (operation instanceof CollectionOperation) {
                executeCollectionOperations((CollectionOperation) operation, c, dataSet);
            } else if (operation instanceof ListOperation) {
                executeListOperation((ListOperation) operation, (List) c, dataSet);
            } else if (operation instanceof AddAllOperation) {
                c.addAll(dataSetList);
            } else {
                throw new IllegalStateException("Unknown operations types");
            }
            measurement.stop();
            collectionInstance.updateLastMesasurementInMs(measurement.getTimeInMs());
        }
        return collectionInstances.getInstances().size();
    }

    @Override
    public void succeeded() {
        mainButtons.enableAll();
    }

    private void executeListOperation(ListOperation operation, List list, Integer[] dataSet) {
        for (Integer value : dataSet) {
            operation.execute(value, list);
        }
    }

    private void executeCollectionOperations(CollectionOperation operation, Collection c, Integer[] dataSet) {
        for (Integer value : dataSet) {
            operation.execute(value, c);
        }
    }

    private Integer[] generateDataSet() {
        Integer[] result = new Integer[dataSetSize];
        for (int i = 0; i < dataSetSize; i++) {
            switch(dataSetGenerator) {
                case RANDOM_VALUE:
                    result[i] = (int) (Math.random() * Integer.MAX_VALUE);
                    break;
                case ALL_ZEROS:
                    result[i] = 0;
                    break;
                case RANDOM_INDEX:
                    result[i] = (int) (Math.random() * dataSetSize);
                    break;
            }
        }
        return result;
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
