package com.example.guiproject.view;

import com.example.guiproject.control.MediaController;
import com.example.guiproject.control.MediaControllerAPI;
import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PlayListContainer {
  private final MediaControllerAPI mediaControllerAPI;
  private final ListView<File> listView;
  private final ObservableList<File> observablePlaylist;

  public PlayListContainer() {
    this.mediaControllerAPI = MediaController.getInstance();
    this.observablePlaylist = FXCollections.observableArrayList();
    this.listView = new ListView<>(observablePlaylist);

    setupCellFactory();
    setupDoubleClickToPlay();

    refreshPlaylist();
    MediaController.getInstance().setOnPlaylistUpdated(this::refreshPlaylist);
  }

  private void setupCellFactory() {
    listView.setCellFactory(
        lv ->
            new ListCell<>() {
              @Override
              protected void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);
                if (empty || file == null) {
                  setText(null);
                } else {
                  setText(file.getName());
                }
              }
            });
  }

  public void refreshPlaylist() {
    List<File> playlist = mediaControllerAPI.getPlaylist();
    observablePlaylist.clear();

    if (playlist == null || playlist.isEmpty()) {
      observablePlaylist.add(new File(""));
    } else {
      observablePlaylist.addAll(playlist);
    }
  }

  private void setupDoubleClickToPlay() {
    listView.setOnMouseClicked(
        event -> {
          if (event.getClickCount() == 2) {
            File selectedFile = listView.getSelectionModel().getSelectedItem();
            if (selectedFile != null) {

              int selectedIndex = listView.getSelectionModel().getSelectedIndex();

              mediaControllerAPI.setCurrentIndex(selectedIndex);

              mediaControllerAPI.loadMedia(selectedFile);
            }
          }
        });
  }

  public Node getView() {
    return new VBox(listView);
  }
}
