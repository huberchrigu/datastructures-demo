package ch.chrigu.demo.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 * Created by Christoph Huber on 03.01.2015.
 */
public class CollectionType {
    private final SimpleStringProperty name;
    private SimpleStringProperty parameters;

    public CollectionType(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty getNameProperty() {
        return this.name;
    }

    public SimpleStringProperty getParametersProperty() {
        return this.parameters;
    }

    public String getName() {
        return this.name.getValue();
    }
}
