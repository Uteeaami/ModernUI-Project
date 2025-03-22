package com.example.guiproject.view.footer;

import com.example.guiproject.control.MediaController;
import com.example.guiproject.control.MediaControllerAPI;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterComponent extends VBox {
  private final MediaControllerAPI mediaControllerAPI;

  public ProgressBarComponent progressBarComponent;
  public Button playButton;
  public boolean isPlaying;
  public VolumeComponent volumeComponent;
  public ControlsComponent controlsComponent;

  public FooterComponent() {
    this.mediaControllerAPI = MediaController.getInstance();
    this.progressBarComponent = new ProgressBarComponent();
    this.playButton = new Button("▶");
    this.volumeComponent = new VolumeComponent();
    this.controlsComponent = new ControlsComponent();
    this.isPlaying = false;
    setActions();

    HBox controls = new HBox(playButton, controlsComponent, volumeComponent);

    this.setPadding(new Insets(5, 10, 5, 10));
    this.setMinHeight(52);
    this.getChildren().addAll(progressBarComponent, controls);
    this.setStyles();
  }

  public void setActions() {
    playButton.setOnAction(e -> this.mediaControllerAPI.playOrPause(isPlaying));
    controlsComponent.stopMediaButton.setOnAction(e -> this.mediaControllerAPI.stop());
    controlsComponent.nextMediaButton.setOnAction(e -> this.mediaControllerAPI.playNextMedia());
    controlsComponent.previousMediaButton.setOnAction(
        e -> this.mediaControllerAPI.playPreviousMedia());
  }

  public void setPlayingStatus(boolean playing) {
    if (playing) {
      playButton.setText("⏸");
      isPlaying = true;
    } else {
      playButton.setText("▶");
      isPlaying = false;
    }
  }

  private void setStyles() {
    this.setStyle("-fx-background-color: #E6E6E6;");
    this.getStylesheets()
        .add(Objects.requireNonNull(getClass().getResource("/styles/styles.css")).toExternalForm());
  }
}
