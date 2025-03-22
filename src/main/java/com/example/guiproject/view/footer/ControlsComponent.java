package com.example.guiproject.view.footer;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ControlsComponent extends HBox {

  public Button previousMediaButton;
  public Button nextMediaButton;
  public Button stopMediaButton;

  public ControlsComponent() {
    this.previousMediaButton = new Button("◁");
    this.nextMediaButton = new Button("▷");
    this.stopMediaButton = new Button("■");
    HBox.setHgrow(this, Priority.ALWAYS);

    this.getChildren().addAll(previousMediaButton, stopMediaButton, nextMediaButton);
  }
}
