/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Christoph Huber
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ch.chrigu.datastructures.demo.tasks;

import ch.chrigu.datastructures.demo.ui.MainElements;
import ch.chrigu.datastructures.demo.instances.CollectionInstance;
import ch.chrigu.datastructures.demo.instances.CollectionInstances;
import ch.chrigu.datastructures.demo.operations.Operation;
import ch.chrigu.datastructures.demo.operations.collection.AddAllOperation;
import ch.chrigu.datastructures.demo.operations.collection.CollectionOperation;
import ch.chrigu.datastructures.demo.operations.collection.list.ListOperation;
import javafx.concurrent.Task;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Task performing a {@link CollectionOperation} in a non-UI thread,
 * as this may take some time dependent on the data set.
 * <p>
 *     Calls <code>{@link MainElements#enableAllButtons()}</code> after operation has finished on each instance.
 * </p>
 *
 * Created by Christoph Huber on 06.03.2015.
 */
public class StartOperationTask extends Task<Integer> {

    private final int dataSetSize;
    private final Operation operation;
    private final DataSetGenerator dataSetGenerator;
    private final MainElements mainElements;
    private final CollectionInstances collectionInstances;

    public enum DataSetGenerator {RANDOM_VALUE, RANDOM_INDEX, ALL_ZEROS}

    public StartOperationTask(CollectionInstances collectionInstances, int dataSetSize, Operation operation, DataSetGenerator dataSetGenerator, MainElements mainElements) {
        this.collectionInstances = collectionInstances;
        this.dataSetSize = dataSetSize;
        this.operation = operation;
        this.dataSetGenerator = dataSetGenerator;
        this.mainElements = mainElements;
        mainElements.startProgress(operation.getClass().getSimpleName() + "...");
        mainElements.getProgressBar().progressProperty().bind(progressProperty());
    }


    @Override
    public Integer call() {
        Integer[] dataSet = generateDataSet();
        List<Integer> dataSetList = Arrays.asList(dataSet);
        int done = 0;
        for (CollectionInstance<Integer> collectionInstance : this.collectionInstances.getInstances()) {
            collectionInstance.resetLastMeasurementInMs();
        }
        for (CollectionInstance<Integer> collectionInstance : this.collectionInstances.getInstances()) {
            Collection<Integer> c = collectionInstance.getInstance();
            if (operation instanceof ListOperation && !(c instanceof List)) {
                done = instanceDone(done);
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
            collectionInstance.updateLastMeasurementInMs(measurement.getTimeInMs());
            done = instanceDone(done);
        }
        return collectionInstances.getInstances().size();
    }

    private int instanceDone(int done) {
        done++;
        updateProgress(done, collectionInstances.getInstances().size());
        return done;
    }

    @Override
    public void succeeded() {
        mainElements.getProgressBar().progressProperty().unbind();
        mainElements.enableAllButtons();
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
