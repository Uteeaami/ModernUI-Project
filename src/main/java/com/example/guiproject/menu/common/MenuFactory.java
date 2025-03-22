package com.example.guiproject.menu.common;

import com.example.guiproject.menu.FileMenu;
import com.example.guiproject.menu.GuideMenu;
import com.example.guiproject.menu.ViewMenu;
import javafx.scene.control.Menu;

public class MenuFactory {
  public static Menu createFileMenu() {
    return new FileMenu(MenuItemFactoryProvider.getFileMenuFactories());
  }

  public static Menu createViewMenu() {
    return new ViewMenu(MenuItemFactoryProvider.getViewMenuFactories());
  }

  public static Menu createGuideMenu() {
    return new GuideMenu(MenuItemFactoryProvider.getGuideMenuFactories());
  }
  // TODO: Other menus?
}
