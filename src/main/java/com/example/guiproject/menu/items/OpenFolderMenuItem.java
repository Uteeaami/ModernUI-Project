package com.example.guiproject.menu.items;

import com.example.guiproject.ContainerManager;
import com.example.guiproject.control.MediaController;
import com.example.guiproject.control.MediaControllerAPI;
import com.example.guiproject.menu.common.MenuItemFactory;
import com.example.guiproject.view.ContainerType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;

public class OpenFolderMenuItem implements MenuItemFactory {

  private final MediaControllerAPI mediaControllerAPI;

  public OpenFolderMenuItem() {
    this.mediaControllerAPI = MediaController.getInstance();
  }

  @Override
  public MenuItem create() {
    MenuItem item = new MenuItem("Open Folder");
    DirectoryChooser directoryChooser = new DirectoryChooser();

    String userDirectoryString = System.getProperty("user.home");
    File userDirectory = new File(userDirectoryString);
    if (!userDirectory.canRead()) {
      userDirectory = new File("c:/");
    }
    directoryChooser.setInitialDirectory(userDirectory);

    item.setOnAction(
        e -> {
          File selectedDirectory = directoryChooser.showDialog(null);
          if (selectedDirectory != null) {
            List<File> mediaFiles = getMediaFiles(selectedDirectory);
            if (!mediaFiles.isEmpty()) {
              mediaControllerAPI.setPlaylist(mediaFiles);
              mediaControllerAPI.playFirstMedia();
              ContainerManager.switchContainer(ContainerType.MEDIA_CONTAINER);
            } else {
              System.err.println("No media files found in the selected directory.");
            }
          }
        });

    return item;
  }

  private List<File> getMediaFiles(File directory) {
    List<File> mediaFiles = new ArrayList<>();
    if (directory != null && directory.isDirectory()) {
      File[] files =
          directory.listFiles((dir, name) -> name.toLowerCase().matches(".*\\.(mp4|mp3|wav)$"));
      if (files != null) {
        mediaFiles.addAll(Arrays.asList(files));
      }
    }
    return mediaFiles;
  }
}
