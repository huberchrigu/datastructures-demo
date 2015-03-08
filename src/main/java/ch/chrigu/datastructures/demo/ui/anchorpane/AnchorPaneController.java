package ch.chrigu.datastructures.demo.ui.anchorpane;

import ch.chrigu.datastructures.demo.instances.CollectionInstances;
import ch.chrigu.datastructures.demo.ui.MainController;
import ch.chrigu.datastructures.demo.ui.MainElements;
import javafx.scene.layout.BorderPane;

/**
 * A controller that handles a menu that is opened in the anchor pane.
 * The {@link MainController} will pass the main border pane
 * for hiding the anchor pane again and the collections for any operations on it.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public interface AnchorPaneController {
    void setMainBorderPane(BorderPane borderPane);

    void setCollectionInstances(CollectionInstances collections);

    void setMainElements(MainElements mainElements);
}
