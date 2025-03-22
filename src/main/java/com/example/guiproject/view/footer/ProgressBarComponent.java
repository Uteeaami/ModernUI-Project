package com.example.guiproject.view.footer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ProgressBarComponent extends HBox {
  public Label currentTimeLabel;
  public Slider progressBar;
  public Label totalTimeLabel;

  private final String NO_TIME_LABEL = "--:--";

  public ProgressBarComponent() {
    this.currentTimeLabel = new Label(NO_TIME_LABEL);
    this.progressBar = new Slider();
    this.totalTimeLabel = new Label(NO_TIME_LABEL);

    HBox.setHgrow(progressBar, Priority.ALWAYS);

    setSpacing(10);

    getChildren().addAll(currentTimeLabel, progressBar, totalTimeLabel);
  }
}
