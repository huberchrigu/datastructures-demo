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

package ch.chrigu.datastructures.demo.operations;

import ch.chrigu.datastructures.demo.operations.collection.list.AddFirstOperation;
import ch.chrigu.datastructures.demo.operations.collection.AddAllOperation;
import ch.chrigu.datastructures.demo.operations.collection.AddLastOperation;
import ch.chrigu.datastructures.demo.operations.collection.RemoveValueOperation;
import ch.chrigu.datastructures.demo.operations.collection.list.AddMedianOperation;
import ch.chrigu.datastructures.demo.operations.collection.list.GetAtIndexOperation;
import ch.chrigu.datastructures.demo.operations.collection.list.RemoveAtIndexOperation;

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
