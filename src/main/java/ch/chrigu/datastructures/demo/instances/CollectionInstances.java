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

package ch.chrigu.datastructures.demo.instances;

import ch.chrigu.datastructures.demo.types.CollectionType;
import ch.chrigu.datastructures.demo.instances.options.CollectionOptions;
import ch.chrigu.datastructures.demo.types.CollectionTypes;
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
