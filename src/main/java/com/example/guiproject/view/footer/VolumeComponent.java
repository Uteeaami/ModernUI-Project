package com.example.guiproject.view.footer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class VolumeComponent extends HBox {

  public Slider volumeSlider;

  public VolumeComponent() {
    Label volumeLabel = new Label("🔊");
    this.volumeSlider = new Slider();
    volumeSlider.getStyleClass().add("volume-slider");

    this.getChildren().addAll(volumeLabel, volumeSlider);
  }
}
