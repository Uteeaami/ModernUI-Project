package com.example.guiproject.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class MediaContainer {
  private final MediaView mediaView;
  private final StackPane mediaContainer;

  public MediaContainer() {
    this.mediaView = new MediaView();
    this.mediaContainer = new StackPane(mediaView);

    mediaContainer.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    mediaContainer.setPrefHeight(Integer.MAX_VALUE);
  }

  public MediaView getMediaView() {
    return mediaView;
  }

  public Node getView() {
    return mediaContainer;
  }
}
