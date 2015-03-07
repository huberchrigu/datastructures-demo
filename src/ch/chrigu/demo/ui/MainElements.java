package ch.chrigu.demo.ui;

import javafx.scene.control.ProgressBar;

/**
 * Can be used to disable and enable all main buttons to prevent conflicts between different operations.
 *
 *
 * Created by Christoph Huber on 06.03.2015.
 */
public interface MainElements {

    void enableAllButtons();

    void disableAllButtons();

    void startProgress(String progressText);

    ProgressBar getProgressBar();
}
