package com.example.guiproject.menu.common;

import com.example.guiproject.menu.items.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemFactoryProvider {
  public static List<MenuItemFactory> getFileMenuFactories() {
    List<MenuItemFactory> factories = new ArrayList<>();
    factories.add(new OpenFileMenuItem());
    factories.add(new OpenFolderMenuItem());
    return factories;
  }

  public static List<MenuItemFactory> getViewMenuFactories() {
    List<MenuItemFactory> factories = new ArrayList<>();
    factories.add(new MediaViewMenuItem());
    factories.add(new PlayListViewMenuItem());
    return factories;
  }

  public static List<MenuItemFactory> getGuideMenuFactories() {
    List<MenuItemFactory> factories = new ArrayList<>();
    factories.add(new GuideMenuItem());
    return factories;
  }
}
