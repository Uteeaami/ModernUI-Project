package com.example.guiproject.view.footer;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Controls extends HBox {

  public Button previousMediaButton;
  public Button nextMediaButton;
  public Button stopMediaButton;

  // TODO: As a improvement use real icons
  public Controls() {
    this.previousMediaButton = new Button("◁");
    this.nextMediaButton = new Button("▷");
    this.stopMediaButton = new Button("■");
    HBox.setHgrow(this, Priority.ALWAYS);

    this.getChildren().addAll(previousMediaButton, stopMediaButton, nextMediaButton);
  }
}
