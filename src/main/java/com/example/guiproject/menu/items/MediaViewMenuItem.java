package com.example.guiproject.menu.items;

import com.example.guiproject.ContainerManager;
import com.example.guiproject.menu.common.MenuItemFactory;
import com.example.guiproject.view.ContainerType;
import javafx.scene.control.MenuItem;

public class MediaViewMenuItem implements MenuItemFactory {
  @Override
  public MenuItem create() {
    MenuItem item = new MenuItem("Media");

    item.setOnAction(
        e -> {
          ContainerManager.switchContainer(ContainerType.MEDIA_CONTAINER);
        });

    return item;
  }
}
