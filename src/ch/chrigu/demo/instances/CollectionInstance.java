package ch.chrigu.demo.instances;

import ch.chrigu.demo.instances.options.CollectionOptions;
import ch.chrigu.demo.types.CollectionType;
import com.sun.deploy.util.StringUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Maintains an instance of a {@link CollectionType}.
 * Generates a name property for the GUI.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class CollectionInstance<T> {

    private CollectionOptions collectionOptions;
    private CollectionType<T> collectionType;
    private Collection<T> instance;

    // FX
    private SimpleStringProperty name;
    private SimpleStringProperty parametersProperty;
    private SimpleStringProperty elements;
    private SimpleIntegerProperty size;
    private SimpleLongProperty lastMeasurementInMs;

    public CollectionInstance(CollectionType<T> collectionType) {
        this.collectionType = collectionType;
        this.instance = collectionType.createInstance();
        initProperties();
    }

    public CollectionInstance(CollectionType<T> collectionType, CollectionOptions collectionOptions) {
        this.collectionType = collectionType;
        this.collectionOptions = collectionOptions;
        if (collectionOptions.getCapacity() == null) {
            this.instance = this.collectionType.createInstance();
        } else {
            this.instance = this.collectionType.createInstance(collectionOptions.getCapacity());
        }
        initProperties();

        // do this after initProperties, because instance's class would be SynchronizedCollection otherwise
        if (collectionOptions.isSynchronizedCollection()) {
            this.instance = synchronizedCollection(instance);
        }
    }

    /**
     * The best matching {@link Collections}'s synchronized... function is applied.
     */
    private Collection<T> synchronizedCollection(Collection<T> instance) {
        if (instance instanceof List) {
            return Collections.synchronizedList((List<T>) instance);
        }
        if (instance instanceof Set) {
            if (instance instanceof NavigableSet) {
                return Collections.synchronizedNavigableSet((NavigableSet<T>) instance);
            }
            return Collections.synchronizedSet((Set<T>) instance);
        }
        return Collections.synchronizedCollection(instance);
    }

    private void initProperties() {
        this.name = new SimpleStringProperty(instance.getClass().getName());
        if (collectionOptions != null) {
            this.parametersProperty = new SimpleStringProperty(collectionOptions.toString());
        }
        lastMeasurementInMs = new SimpleLongProperty();
        elements = new SimpleStringProperty();
        size = new SimpleIntegerProperty();
        updateElements();
        updateSize();
    }

    public void updateLastMesasurementInMs(long timeInMs) {
        this.lastMeasurementInMs.setValue(timeInMs);
        updateElements();
        updateSize();
    }

    private void updateSize() {
        size.set(instance.size());
    }

    private void updateElements() {
        if (instance.isEmpty()) {
            elements.set("empty");
        } else if (instance.size() <= 10) {
            elements.set(StringUtils.join(instance.stream().map(String::valueOf).collect(Collectors.toList()), ", "));
        } else {
            Collection firstFive = instance.stream().limit(5).map(String::valueOf).collect(Collectors.toList());
            Collection lastFive = instance.stream().skip(instance.size() - 5).map(String::valueOf).collect(Collectors.toList());
            elements.set(StringUtils.join(firstFive, ", ") + " ... " + StringUtils.join(lastFive, ", "));
        }
    }

    public Collection<T> getInstance() {
        return instance;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty parametersProperty() {
        return parametersProperty;
    }

    public SimpleStringProperty elementsProperty() {
        return elements;
    }

    public SimpleIntegerProperty sizeProperty() {
        return size;
    }

    public SimpleLongProperty lastMeasurementInMsProperty() {
        return lastMeasurementInMs;
    }

    public void resetLastMeasurementInMs() {
        lastMeasurementInMs.setValue(-1);
    }

    public void clear() {
        instance.clear();
        updateElements();
        updateSize();
    }
}
