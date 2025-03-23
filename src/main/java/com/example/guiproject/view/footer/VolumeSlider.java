package com.example.guiproject.view.footer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class VolumeSlider extends HBox {

  public Slider slider;

  // TODO: As an improvement, volumeLabel could have a event handler to mute / un-mute
  //       and use real icons
  public VolumeSlider() {
    Label volumeLabel = new Label("🔊");
    this.slider = new Slider();
    slider.getStyleClass().add("volume-slider");

    this.getChildren().addAll(volumeLabel, slider);
  }
}
