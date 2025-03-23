package com.example.guiproject.menu.items;

import com.example.guiproject.ContainerManager;
import com.example.guiproject.control.MediaController;
import com.example.guiproject.control.MediaControllerAPI;
import com.example.guiproject.menu.common.MenuItemFactory;
import com.example.guiproject.view.ContainerType;
import java.io.File;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class OpenFileMenuItem implements MenuItemFactory {
  private final MediaControllerAPI mediaControllerAPI;

  public OpenFileMenuItem() {
    this.mediaControllerAPI = MediaController.getInstance();
  }

  @Override
  public MenuItem create() {
    MenuItem item = new MenuItem("Open File");
    FileChooser fileChooser = new FileChooser();
    fileChooser
        .getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Media Files", "*.mp4", "*.mp3", "*.wav"));

    String userDirectoryString = System.getProperty("user.home");
    File userDirectory = new File(userDirectoryString);
    if (!userDirectory.canRead()) {
      userDirectory = new File("c:/");
    }
    fileChooser.setInitialDirectory(userDirectory);

    item.setOnAction(
        e -> {
          File selectedFile = fileChooser.showOpenDialog(null);
          if (selectedFile != null) {
            mediaControllerAPI.loadMedia(selectedFile);
            ContainerManager.switchContainer(ContainerType.MEDIA_CONTAINER);
          }
        });

    return item;
  }
}
