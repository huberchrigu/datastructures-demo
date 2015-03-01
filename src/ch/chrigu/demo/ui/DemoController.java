package ch.chrigu.demo.ui;

import ch.chrigu.demo.collection.CollectionTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Main window's controller.
 * Includes table of collections and action buttons.
 */
public class DemoController {
    private CollectionTypes collections;

    @FXML
    private Button removeCollectionButton;

    @FXML
    private TableView<CollectionType> collectionsTable;

    @FXML
    private TableColumn<CollectionType, String> collectionName;

    @FXML
    private TableColumn<CollectionType, String> collectionParameters;

    @FXML
    private BorderPane borderPane;

    private TableRow selectedRow;

    public DemoController() {
        collections = new CollectionTypes();
    }

    @FXML
    public void initialize() {

        ObservableList<CollectionType> names = FXCollections.observableArrayList(collections.getMetadata());
        collectionsTable.setItems(names);

        collectionsTable.setRowFactory(collectionType -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                selectedRow = row;
                removeCollectionButton.setDisable(false);
            });
            return row;
        });

        collectionName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        collectionParameters.setCellValueFactory(cellData -> cellData.getValue().getParametersProperty());

        removeCollectionButton.setDisable(true);
    }

    /**
     * Opens the new collection menu and overgives border pane and collections.
     */
    public void addCollection() {
        AnchorPane anchorPane;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newCollection.fxml"));
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        AddController controller = loader.getController();
        controller.setMainBorderPane(borderPane);
        controller.setCollectionTypes(collections);
        borderPane.setLeft(anchorPane);

    }
    public void removeCollection() {
        if (selectedRow != null) {
            collections.remove((CollectionType) selectedRow.getItem());
            collectionsTable.setItems(FXCollections.observableArrayList(collections.getMetadata()));
            if (collectionsTable.getItems().size() <= 0) {
                removeCollectionButton.setDisable(false);
            }
        }
    }
    public void clearAllCollections() {
        collections.clearAll();
    }
}


