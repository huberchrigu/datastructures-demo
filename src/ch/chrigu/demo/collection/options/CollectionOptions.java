package ch.chrigu.demo.collection.options;

/**
 * Fluent API for building options how to create a collection.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class CollectionOptions {
    private boolean synchronizedCollection;
    private Integer capacity;

    public CollectionOptions synchronizedCollection() {
        this.synchronizedCollection = true;
        return this;
    }

    public CollectionOptions capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }


    public boolean isSynchronizedCollection() {
        return synchronizedCollection;
    }

    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (synchronizedCollection) {
            builder.append("synchronized");
            if (capacity != null) {
                builder.append(", ");
            }
        }
        if (capacity != null) {
            builder.append("initial capacity ").append(capacity);
        }
        return builder.toString();
    }
}
