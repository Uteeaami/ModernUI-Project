package com.example.guiproject.view.footer;

import com.example.guiproject.control.MediaController;
import com.example.guiproject.control.MediaControllerAPI;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FooterBar extends VBox {
  private final MediaControllerAPI mediaControllerAPI;

  public ProgressBar progressBar;
  public Button playButton;
  public boolean isPlaying;
  public VolumeSlider volumeSlider;
  public Controls controls;

  public FooterBar() {
    this.mediaControllerAPI = MediaController.getInstance();
    this.progressBar = new ProgressBar();
    this.playButton = new Button("▶");
    this.volumeSlider = new VolumeSlider();
    this.controls = new Controls();
    this.isPlaying = false;
    setActions();

    HBox controls = new HBox(playButton, this.controls, volumeSlider);

    this.setPadding(new Insets(5, 10, 5, 10));
    this.setMinHeight(52);
    this.getChildren().addAll(progressBar, controls);
    this.setStyles();
  }

  public void setActions() {
    playButton.setOnAction(e -> this.mediaControllerAPI.playOrPause(isPlaying));
    controls.stopMediaButton.setOnAction(e -> this.mediaControllerAPI.stop());
    controls.nextMediaButton.setOnAction(e -> this.mediaControllerAPI.playNextMedia());
    controls.previousMediaButton.setOnAction(e -> this.mediaControllerAPI.playPreviousMedia());
  }

  // TODO: As a improvement use real icons
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
