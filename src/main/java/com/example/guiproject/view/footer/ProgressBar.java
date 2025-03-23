package com.example.guiproject.view.footer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ProgressBar extends HBox {
  public Label currentTimeLabel;
  public Slider slider;
  public Label totalTimeLabel;

  private final String NO_TIME_LABEL = "--:--";

  public ProgressBar() {
    this.currentTimeLabel = new Label(NO_TIME_LABEL);
    this.slider = new Slider();
    this.totalTimeLabel = new Label(NO_TIME_LABEL);

    HBox.setHgrow(slider, Priority.ALWAYS);

    setSpacing(10);

    getChildren().addAll(currentTimeLabel, slider, totalTimeLabel);
  }
}
