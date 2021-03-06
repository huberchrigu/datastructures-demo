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

package ch.chrigu.datastructures.demo.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Maintains a {@link CollectionType} instance for each types.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class CollectionTypes {
    public static final CollectionType<Integer> LINKED_LIST = new LinkedListType<>();
    public static final CollectionType<Integer> ARRAY_LIST = new ArrayListType<>();
    public static final CollectionType<Integer> HASH_SET = new HashSetType<>();
    public static final CollectionType<Integer> LINKED_HASH_SET = new LinkedHashSetType<>();
    public static final CollectionType<Integer> TREE_SET = new TreeSetType<>();
    public static final CollectionType<Integer> ARRAY_DEQUE = new ArrayDequeType<>();
    public static final CollectionType<Integer> VECTOR = new VectorType<>();
    public static final CollectionType<Integer> PRIORITY_QUEUE = new PriorityQueueType<>();

    private static final Map<String, CollectionType<Integer>> MAP = new HashMap<>();

    static {
        MAP.put("LinkedList", LINKED_LIST);
        MAP.put("ArrayList", ARRAY_LIST);
        MAP.put("HashSet", HASH_SET);
        MAP.put("LinkedHashSet", LINKED_HASH_SET);
        MAP.put("TreeSet", TREE_SET);
        MAP.put("ArrayDeque", ARRAY_DEQUE);
        MAP.put("Vector", VECTOR);
        MAP.put("PriorityQueue", PRIORITY_QUEUE);
    }

    private CollectionTypes() {}


    public static CollectionType<Integer> fromString(String type) {
        CollectionType<Integer> collectionType = MAP.get(type);
        if (collectionType == null) {
            throw new IllegalStateException(type + " is an unknown types");
        }
        return collectionType;
    }

    public static Set<String> getAvailableTypes() {
        return MAP.keySet();
    }
}
