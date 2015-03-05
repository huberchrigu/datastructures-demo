package ch.chrigu.demo.ui;

import ch.chrigu.demo.collection.CollectionInstances;
import ch.chrigu.demo.collection.options.CollectionOptions;
import ch.chrigu.demo.collection.type.CollectionType;
import ch.chrigu.demo.collection.type.CollectionTypes;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
/**
 * Controller for the add menu, where the collection type can be chosen.
 * Created by Christoph Huber on 21.01.2015.
 */
public class AddController {

    @FXML
    private ChoiceBox<String> type;

    @FXML
    private CheckBox synchronizedCollection;

    @FXML
    private CheckBox blocking;

    @FXML
    private TextField capacity;

    private BorderPane mainBorderPane;
    private CollectionInstances collectionInstances;

    @FXML
    public void initialize() {
        type.setItems(FXCollections.observableArrayList(CollectionTypes.getAvailableTypes()));
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
    public void setCollectionInstances(CollectionInstances collectionInstances) {
        this.collectionInstances = collectionInstances;
    }

    /**
     * Tries to create a valid {@link ch.chrigu.demo.collection.type.CollectionType}.
     * If valid, calls {@link ch.chrigu.demo.collection.CollectionInstances#add} and unsets this menu.
     */
    public void add() {
        if (type.getValue() == null) {
            warn("Please choose a collection type");
            return;
        }
        CollectionType<Integer> collectionType = CollectionTypes.fromString(type.getValue());
        CollectionOptions collectionOptions = new CollectionOptions();
        if (capacity.textProperty().isEmpty().getValue()) {
            if (!collectionType.allowsNoParams()) {
                warn("This collection type requires an initial capacity parameter");
                return;
            }
        } else {
            try {
                int capacityNumber = Integer.parseInt(capacity.getText());
                if (capacityNumber < 1) {
                    warn("Initial capacity must be a positive number");
                    return;
                }
                if (!collectionType.allowsCapacityParam()) {
                    warn("Initial capacity parameter not allowed for this collection type");
                    return;
                }
                collectionOptions = collectionOptions.capacity(capacityNumber);
            } catch (NumberFormatException e) {
                warn("The initial capacity value needs to be empty or a valid number");
                return;
            }
        }
        if (synchronizedCollection.isSelected()) {
            collectionOptions = collectionOptions.synchronizedCollection();
        }
        if (blocking.isSelected()) {
            // TODO: Eval and add option.
        }
        collectionInstances.add(collectionType, collectionOptions);
        mainBorderPane.setLeft(null);
    }

    private void warn(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid parameters");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
