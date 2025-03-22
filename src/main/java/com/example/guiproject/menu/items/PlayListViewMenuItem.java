package com.example.guiproject.menu.items;

import com.example.guiproject.ContainerManager;
import com.example.guiproject.menu.common.MenuItemFactory;
import com.example.guiproject.view.ContainerType;
import javafx.scene.control.MenuItem;

public class PlayListViewMenuItem implements MenuItemFactory {
  @Override
  public MenuItem create() {
    MenuItem item = new MenuItem("Playlist");

    item.setOnAction(
        e -> {
          ContainerManager.switchContainer(ContainerType.PLAYLIST_CONTAINER);
        });

    return item;
  }
}
