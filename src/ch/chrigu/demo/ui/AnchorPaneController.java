package ch.chrigu.demo.ui;

import ch.chrigu.demo.instances.CollectionInstances;
import javafx.scene.layout.BorderPane;

/**
 * A controller that handles a menu that is opened in the anchor pane.
 * The {@link ch.chrigu.demo.ui.MainController} will pass the main border pane
 * for hiding the anchor pane again and the collections for any operations on it.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public interface AnchorPaneController {
    void setMainBorderPane(BorderPane borderPane);

    void setCollectionInstances(CollectionInstances collections);
}