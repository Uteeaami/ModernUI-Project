package com.example.guiproject.menu.items;

import com.example.guiproject.ContainerManager;
import com.example.guiproject.menu.common.MenuItemFactory;
import com.example.guiproject.view.ContainerType;
import javafx.scene.control.MenuItem;

public class GuideMenuItem implements MenuItemFactory {
  @Override
  public MenuItem create() {
    MenuItem item = new MenuItem("Guide");

    item.setOnAction(
        e -> {
          ContainerManager.switchContainer(ContainerType.GUIDE_CONTAINER);
        });

    return item;
  }
}
