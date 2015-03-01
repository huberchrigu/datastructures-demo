package ch.chrigu.demo.ui;

import ch.chrigu.demo.collection.CollectionTypes;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

/**
 * Controller for the add menu, where the collection type can be chosen.
 * Created by Christoph Huber on 21.01.2015.
 */
public class AddController {

    @FXML
    private ChoiceBox<String> type;

    private BorderPane mainBorderPane;
    private CollectionTypes collectionTypes;

    @FXML
    public void initialize() {
        type.setItems(FXCollections.observableArrayList(CollectionTypes.TYPES));
    }

    public void cancel() {
        mainBorderPane.setLeft(null);

    }

    /**
     * Will be used to remove this menu again.
     */
    public void setMainBorderPane(BorderPane borderPane) {
        this.mainBorderPane = borderPane;
    }
    public void setCollectionTypes(CollectionTypes collectionTypes) {
        this.collectionTypes = collectionTypes;
    }

    /**
     * Call {@link CollectionTypes#add} and unset this menu.
     * TODO: Does not work yet, since collection types is not oversable.
     */
    public void add() {
        String value = type.getValue();
        if (value != null) {
            collectionTypes.add(value);
            mainBorderPane.setLeft(null);
        }
    }
}
