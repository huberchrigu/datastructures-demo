package ch.chrigu.demo.operations;

import ch.chrigu.demo.operations.collection.AddAllOperation;
import ch.chrigu.demo.operations.collection.AddLastOperation;
import ch.chrigu.demo.operations.collection.RemoveValueOperation;
import ch.chrigu.demo.operations.collection.list.AddFirstOperation;
import ch.chrigu.demo.operations.collection.list.AddMedianOperation;
import ch.chrigu.demo.operations.collection.list.GetAtIndexOperation;
import ch.chrigu.demo.operations.collection.list.RemoveAtIndexOperation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maintains an instance of each {@link Operation}.
 * Maps String to instances.
 *
 * Created by Christoph Huber on 06.03.2015.
 */
public class Operations {
    private Operations() {}

    private static final List<Operation> OPERATIONS = new LinkedList<>();

    static {
        OPERATIONS.add(new AddAllOperation());
        OPERATIONS.add(new AddLastOperation());
        OPERATIONS.add(new RemoveValueOperation());
        OPERATIONS.add(new AddFirstOperation());
        OPERATIONS.add(new AddMedianOperation());
        OPERATIONS.add(new GetAtIndexOperation());
        OPERATIONS.add(new RemoveAtIndexOperation());
    }

    public static List<String> getOperationNames() {
        return OPERATIONS.stream().map(operation -> operation.getClass().getSimpleName()).collect(Collectors.toList());
    }

    public static Operation fromName(String operationName) {
        List<Operation> matches = OPERATIONS.stream().filter(
                operation -> operation.getClass().getSimpleName().equals(operationName)
            ).collect(Collectors.toList());
        if (matches.size() != 1) {
            throw new IllegalStateException(operationName + " was found " + matches.size() + " times, but exactly one such operations should exist!");
        }
        return matches.get(0);
    }
}
