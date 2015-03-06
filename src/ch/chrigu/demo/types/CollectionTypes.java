package ch.chrigu.demo.types;

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
    public static final CollectionType<Integer> ARRAY_QUEUE = new ArrayQueueType<>();
    public static final CollectionType<Integer> ARRAY_DEQUE = new ArrayDequeType<>();
    public static final CollectionType<Integer> VECTOR = new VectorType<>();

    private static final Map<String, CollectionType<Integer>> MAP = new HashMap<>();

    static {
        MAP.put("LinkedList", LINKED_LIST);
        MAP.put("ArrayList", ARRAY_LIST);
        MAP.put("HashSet", HASH_SET);
        MAP.put("LinkedHashSet", LINKED_HASH_SET);
        MAP.put("TreeSet", TREE_SET);
        MAP.put("ArrayQueue", ARRAY_QUEUE);
        MAP.put("ArrayDeque", ARRAY_DEQUE);
        MAP.put("Vector", VECTOR);
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
