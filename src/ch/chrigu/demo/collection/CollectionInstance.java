package ch.chrigu.demo.collection;

import ch.chrigu.demo.collection.options.CollectionOptions;
import ch.chrigu.demo.collection.type.CollectionType;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;
import java.util.Collections;

/**
 * Maintains an instance of a {@link CollectionType}.
 * Generates a name property for the GUI.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class CollectionInstance<T> {

    private CollectionOptions collectionOptions;
    private CollectionType<T> type;
    private Collection<T> instance;

    // FX
    private SimpleStringProperty name;
    private SimpleStringProperty parametersProperty;

    public CollectionInstance(CollectionType<T> type) {
        this.type = type;
        this.instance = type.createInstance();
        initProperties();
    }

    public CollectionInstance(CollectionType<T> collectionType, CollectionOptions collectionOptions) {
        this.type = collectionType;
        if (collectionOptions.getCapacity() == null) {
            this.instance = type.createInstance();
        } else {
            this.instance = type.createInstance(collectionOptions.getCapacity());
        }
        if (collectionOptions.isSynchronizedCollection()) {
            this.instance = Collections.synchronizedCollection(instance);
        }
        this.collectionOptions = collectionOptions;
        initProperties();
    }

    private void initProperties() {
        this.name = new SimpleStringProperty(instance.getClass().getName());
        if (collectionOptions != null) {
            this.parametersProperty = new SimpleStringProperty(collectionOptions.toString());
        }
    }

    public String getName() {
        return name.getValue();
    }

    public Collection<T> getInstance() {
        return instance;
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public SimpleStringProperty getParametersProperty() {
        return parametersProperty;
    }
}
