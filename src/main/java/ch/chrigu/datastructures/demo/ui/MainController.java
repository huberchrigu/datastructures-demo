package ch.chrigu.datastructures.demo.ui;

import ch.chrigu.datastructures.demo.instances.CollectionInstances;
import ch.chrigu.datastructures.demo.ui.anchorpane.AnchorPaneController;
import ch.chrigu.datastructures.demo.instances.CollectionInstance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

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

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressText;

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

        hideProgress();
    }

    /**
     * Opens the new instances menu and overgives border pane and collections.
     */
    public void addCollection() {
        openPane(FXMLResources.getAddCollection());
    }
    public void removeCollection() {
        collectionsTable.getItems().removeAll(collectionsTable.getSelectionModel().getSelectedItems());
    }
    public void clearAll() {
        for (CollectionInstance<Integer> collectionInstance : collectionsTable.getItems()) {
            collectionInstance.clear();
        }
    }

    public void startOperation() {
        openPane(FXMLResources.getStartOperation());
    }

    private void hideProgress() {
        progressBar.setVisible(false);
        progressText.setVisible(false);
    }

    private void openPane(URL fxmlFile) {
        AnchorPane anchorPane;
        FXMLLoader loader = new FXMLLoader(fxmlFile);
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        AnchorPaneController controller = loader.getController();
        controller.setMainBorderPane(borderPane);
        controller.setCollectionInstances(collections);
        controller.setMainElements(new MainElements() {
            @Override
            public void enableAllButtons() {
                removeCollectionButton.setDisable(false);
                startOperationButton.setDisable(false);
                clearButton.setDisable(false);
                addCollectionButton.setDisable(false);
            }

            @Override
            public void disableAllButtons() {
                removeCollectionButton.setDisable(true);
                startOperationButton.setDisable(true);
                clearButton.setDisable(true);
                addCollectionButton.setDisable(true);
            }

            @Override
            public void startProgress(String label) {
                progressText.setText(label);
                progressBar.setProgress(0);
                progressText.setVisible(true);
                progressBar.setVisible(true);
            }

            @Override
            public ProgressBar getProgressBar() {
                return progressBar;
            }
        });
        borderPane.setLeft(anchorPane);
    }
}


