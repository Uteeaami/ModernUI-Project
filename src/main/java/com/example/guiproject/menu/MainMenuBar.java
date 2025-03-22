package com.example.guiproject.menu;

import com.example.guiproject.menu.common.MenuFactory;
import javafx.scene.control.MenuBar;

public class MainMenuBar extends MenuBar {

  public MainMenuBar() {
    this.getMenus()
        .addAll(
            MenuFactory.createFileMenu(),
            MenuFactory.createViewMenu(),
            MenuFactory.createGuideMenu());
  }
}
