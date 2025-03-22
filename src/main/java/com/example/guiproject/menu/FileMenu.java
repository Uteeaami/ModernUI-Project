package com.example.guiproject.menu;

import com.example.guiproject.menu.common.AbstractMenu;
import com.example.guiproject.menu.common.MenuItemFactory;
import java.util.List;

public class FileMenu extends AbstractMenu {

  public FileMenu(List<MenuItemFactory> menuItemFactoryList) {
    super("File");
    menuItemFactoryList.forEach(factory -> this.addMenuItem(factory.create()));
  }
}
