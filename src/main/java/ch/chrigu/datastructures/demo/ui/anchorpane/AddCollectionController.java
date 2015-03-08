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

package ch.chrigu.datastructures.demo.ui.anchorpane;

import ch.chrigu.datastructures.demo.instances.CollectionInstances;
import ch.chrigu.datastructures.demo.types.CollectionType;
import ch.chrigu.datastructures.demo.ui.MainElements;
import ch.chrigu.datastructures.demo.instances.options.CollectionOptions;
import ch.chrigu.datastructures.demo.types.CollectionTypes;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
/**
 * Controller for the add menu, where the instances types can be chosen.
 * Created by Christoph Huber on 21.01.2015.
 */
public class AddCollectionController implements AnchorPaneController {

    @FXML
    private ChoiceBox<String> type;

    @FXML
    private CheckBox synchronizedCollection;

    @FXML
    private TextField capacity;

    private BorderPane mainBorderPane;
    private CollectionInstances collectionInstances;

    @FXML
    public void initialize() {
        type.setItems(FXCollections.observableArrayList(CollectionTypes.getAvailableTypes()));
        type.setValue("ArrayList");
    }

    public void cancel() {
        mainBorderPane.setLeft(null);

    }

    /**
     * Will be used to remove this menu again.
     */
    @Override
    public void setMainBorderPane(BorderPane borderPane) {
        this.mainBorderPane = borderPane;
    }

    @Override
    public void setCollectionInstances(CollectionInstances collectionInstances) {
        this.collectionInstances = collectionInstances;
    }

    @Override
    public void setMainElements(MainElements mainElements) {
        // ignore, we don't need the button here
    }

    /**
     * Tries to create a valid {@link CollectionType}.
     * If valid, calls {@link CollectionInstances#add} and unsets this menu.
     */
    public void add() {
        if (type.getValue() == null) {
            warn("Please choose a instances types");
            return;
        }
        CollectionType<Integer> collectionType = CollectionTypes.fromString(type.getValue());
        CollectionOptions collectionOptions = new CollectionOptions();
        if (capacity.textProperty().isEmpty().getValue()) {
            if (!collectionType.allowsNoParams()) {
                warn("This instances types requires an initial capacity parameter");
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
                    warn("Initial capacity parameter not allowed for this instances types");
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
