package ch.chrigu.datastructures.demo.ui;

import java.net.URL;

/**
 * Created by Christoph Huber on 08.03.2015.
 */
public class FXMLResources {
    private FXMLResources() {}


    public static URL getMain() {
        return getResource("main.fxml");
    }

    public static URL getStartOperation() {
        return getResource("startOperation.fxml");
    }

    public static URL getAddCollection() {
        return getResource("addCollection.fxml");
    }

    private static URL getResource(String fileName) {
        return FXMLResources.class.getClassLoader().getResource("layout/" + fileName);
    }
}
