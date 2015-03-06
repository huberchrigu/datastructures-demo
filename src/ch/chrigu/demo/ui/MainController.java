package ch.chrigu.demo.ui;

import ch.chrigu.demo.instances.CollectionInstance;
import ch.chrigu.demo.instances.CollectionInstances;
import ch.chrigu.demo.ui.anchorpane.AnchorPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
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
    private Button startOperationButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button addCollectionButton;

    @FXML
    private TableView<CollectionInstance<Integer>> collectionsTable;

    @FXML
    private TableColumn<CollectionInstance<Integer>, String> collectionName;

    @FXML
    private TableColumn<CollectionInstance<Integer>, String> collectionParameters;

    @FXML
    private TableColumn<CollectionInstance<Integer>, Number> collectionSize;

    @FXML
    private TableColumn<CollectionInstance<Integer>, String> collectionElements;

    @FXML
    private TableColumn<CollectionInstance<Integer>, Number> lastMeasurement;

    @FXML
    private BorderPane borderPane;

    public MainController() {
        collections = new CollectionInstances();
    }

    @FXML
    public void initialize() {
        collectionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        collectionsTable.setItems(collections.getInstances());

        collectionName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        collectionParameters.setCellValueFactory(cellData -> cellData.getValue().parametersProperty());
        collectionSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        collectionElements.setCellValueFactory(cellData -> cellData.getValue().elementsProperty());
        lastMeasurement.setCellValueFactory(cellData -> cellData.getValue().lastMeasurementInMsProperty());
    }

    /**
     * Opens the new instances menu and overgives border pane and collections.
     */
    public void addCollection() {
        openPane("newCollection.fxml");
    }
    public void removeCollection() {
        collectionsTable.getItems().removeAll(collectionsTable.getSelectionModel().getSelectedItems());
    }
    public void clear() {
        collectionsTable.getItems().clear();
    }

    public void startOperation() {
        openPane("startOperation.fxml");
    }

    private void openPane(String fxmlFile) {
        AnchorPane anchorPane;
        FXMLLoader loader = new FXMLLoader(AnchorPaneController.class.getResource(fxmlFile));
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        AnchorPaneController controller = loader.getController();
        controller.setMainBorderPane(borderPane);
        controller.setCollectionInstances(collections);
        controller.setMainButtons(new MainButtons() {
            @Override
            public void enableAll() {
                removeCollectionButton.setDisable(false);
                startOperationButton.setDisable(false);
                clearButton.setDisable(false);
                addCollectionButton.setDisable(false);
            }

            @Override
            public void disableAll() {
                removeCollectionButton.setDisable(true);
                startOperationButton.setDisable(true);
                clearButton.setDisable(true);
                addCollectionButton.setDisable(true);
            }
        });
        borderPane.setLeft(anchorPane);
    }
}


