package ch.chrigu.demo.ui;

import ch.chrigu.demo.instances.CollectionInstance;
import ch.chrigu.demo.instances.CollectionInstances;
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
 * Includes table of instances instances and action buttons.
 */
public class MainController {
    private CollectionInstances collections;

    @FXML
    private Button removeCollectionButton;

    @FXML
    private TableView<CollectionInstance<Integer>> collectionsTable;

    @FXML
    private TableColumn<CollectionInstance<Integer>, String> collectionName;

    @FXML
    private TableColumn<CollectionInstance<Integer>, String> collectionParameters;

    @FXML
    private BorderPane borderPane;

    private TableRow<CollectionInstance<Integer>> selectedRow;

    public MainController() {
        collections = new CollectionInstances();
    }

    @FXML
    public void initialize() {

        collectionsTable.setItems(collections.getInstances());

        collectionsTable.setRowFactory(collectionInstance -> {
            TableRow<CollectionInstance<Integer>> row = new TableRow<>();
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
     * Opens the new instances menu and overgives border pane and collections.
     */
    public void addCollection() {
        openPane("newCollection.fxml");
    }
    public void removeCollection() {
        if (selectedRow != null) {
            collectionsTable.getItems().remove(selectedRow.getItem());
            if (collectionsTable.getItems().size() <= 0) {
                removeCollectionButton.setDisable(false);
            }
        }
    }
    public void clearAllCollections() {
        collectionsTable.getItems().clear();
    }

    public void startAction() {
        openPane("startAction.fxml");
    }

    private void openPane(String fxmlFile) {
        AnchorPane anchorPane;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        AnchorPaneController controller = loader.getController();
        controller.setMainBorderPane(borderPane);
        controller.setCollectionInstances(collections);
        borderPane.setLeft(anchorPane);
    }
}


