package ch.chrigu.demo;

import ch.chrigu.demo.collection.*;
import ch.chrigu.demo.collection.list.AddFirstOperation;
import ch.chrigu.demo.collection.list.GetAtIndexOperation;
import ch.chrigu.demo.collection.list.RemoveAtIndexOperation;
import ch.chrigu.demo.ui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        primaryStage.setTitle("Collections Demo");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        // consoleTest();
    }

    private static void consoleTest() {
        int n = 100000;
        CollectionInstances collections = new CollectionInstances();

        test("Add " + n + " random numbers", collections, n, new AddLastOperation(), CollectionInstances.DataSetGenerator.RANDOM_VALUE);

        test("Remove " + n + " random numbers", collections, n, new RemoveValueOperation(), CollectionInstances.DataSetGenerator.RANDOM_VALUE);

        // test("Add " + n + " times zeros", collections, n, new AddLastOperation(), CollectionTypes.DataSetGenerator.ALL_ZEROS);

        // test("Remove " + n + " times zeros", collections, n, new RemoveValueOperation(), CollectionTypes.DataSetGenerator.ALL_ZEROS);

        test("Add a collection of size " + n, collections, n, new AddAllOperation(), CollectionInstances.DataSetGenerator.RANDOM_VALUE);

        // test("Add " + n + " elements to middle of list", collections, n, new AddMedianOperation(), CollectionTypes.DataSetGenerator.RANDOM_VALUE);

        test("Add " + n + " elements to start of list", collections, n, new AddFirstOperation(), CollectionInstances.DataSetGenerator.RANDOM_VALUE);

        test("Get " + n + " elements at custom position", collections, n, new GetAtIndexOperation(), CollectionInstances.DataSetGenerator.RANDOM_INDEX);

        test("Remove " + n + " elements at custom position", collections, n, new RemoveAtIndexOperation(), CollectionInstances.DataSetGenerator.RANDOM_INDEX);
    }

    private static void test(String description, CollectionInstances collections, int n, Operation operation, CollectionInstances.DataSetGenerator generator) {
        System.out.println("\n" + description + ":");
        Map<String, CollectionInstances.Measurement> result = collections.performCollectionOperation(n, operation, generator);
        for (Map.Entry<String, CollectionInstances.Measurement> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getTimeInMs() + "ms");
        }
    }
}
