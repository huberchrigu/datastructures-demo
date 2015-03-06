package ch.chrigu.demo.ui.anchorpane;

import ch.chrigu.demo.tasks.StartOperationTask;
import ch.chrigu.demo.operations.Operation;
import ch.chrigu.demo.operations.Operations;
import ch.chrigu.demo.instances.CollectionInstances;
import ch.chrigu.demo.ui.MainButtons;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Controller for startOperation.fxml. For configuring an operationChoice to be executed on each instances instance.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class StartOperationController implements AnchorPaneController {
    private static final String RANDOM_VALUE = "Random values";
    private static final String ALL_ZEROS = "All zeros";
    private static final String RANDOM_INDEX = "Random indices";

    private BorderPane mainBorderPane;
    private CollectionInstances collectionInstances;
    private MainButtons mainButtons;

    @FXML
    TextField dataSetSize;

    @FXML
    private ChoiceBox<String> operationChoice;

    @FXML ChoiceBox<String> dataSetGeneratorChoice;

    @FXML
    public void initialize() {
        operationChoice.setItems(FXCollections.observableArrayList(Operations.getOperationNames()));
        dataSetGeneratorChoice.setItems(FXCollections.observableArrayList(RANDOM_VALUE, ALL_ZEROS, RANDOM_INDEX));
    }

    @Override
    public void setMainBorderPane(BorderPane mainBorderPane) {
        this.mainBorderPane = mainBorderPane;
    }

    @Override
    public void setCollectionInstances(CollectionInstances collectionInstances) {
        this.collectionInstances = collectionInstances;
    }

    @Override
    public void setMainButtons(MainButtons mainButtons) {
        this.mainButtons = mainButtons;
    }

    /**
     * Validates the input params. Then disables the start button, which will be enabled
     * again after the operation was performed (by callback function).
     */
    public void start() {
        if (operationChoice.getValue() == null || dataSetGeneratorChoice.getValue() == null) {
            return;
        }
        String operationName = operationChoice.getValue();
        Operation operation = Operations.fromName(operationName);
        StartOperationTask.DataSetGenerator dataSetGenerator = getDataSetGenerator(dataSetGeneratorChoice.getValue());
        int size = parseNumericTextField(dataSetSize);
        if (!validateParams(size)) {
            return;
        }
        mainButtons.disableAll();
        new StartOperationTask(collectionInstances, size, operation, dataSetGenerator, mainButtons).run();
        cancel();
    }

    private boolean validateParams(int dataSetSize) {
        if (dataSetSize < 1) {
            warn("Data Set Size must be greater than zero");
            return false;
        }
        return true;
    }

    private void warn(String warningMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrong input parameter");
        alert.setContentText(warningMessage);
        alert.showAndWait();
    }

    private int parseNumericTextField(TextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private StartOperationTask.DataSetGenerator getDataSetGenerator(String value) {
        switch(value) {
            case RANDOM_VALUE:
                return StartOperationTask.DataSetGenerator.RANDOM_VALUE;
            case ALL_ZEROS:
                return StartOperationTask.DataSetGenerator.ALL_ZEROS;
            case RANDOM_INDEX:
                return StartOperationTask.DataSetGenerator.RANDOM_INDEX;
            default:
                throw new IllegalStateException("Invalid data set generator choice!");
        }
    }

    public void cancel() {
        mainBorderPane.setLeft(null);
    }
}
