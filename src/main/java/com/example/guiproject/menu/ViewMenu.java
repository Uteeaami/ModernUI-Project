package com.example.guiproject.menu;

import com.example.guiproject.menu.common.AbstractMenu;
import com.example.guiproject.menu.common.MenuItemFactory;

import java.util.List;

public class ViewMenu extends AbstractMenu {
  public ViewMenu(List<MenuItemFactory> menuItemFactoryList) {
    super("View");
    menuItemFactoryList.forEach(factory -> this.addMenuItem(factory.create()));
  }
}
