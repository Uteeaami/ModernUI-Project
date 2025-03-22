package com.example.guiproject.menu;

import com.example.guiproject.menu.common.AbstractMenu;
import com.example.guiproject.menu.common.MenuItemFactory;
import java.util.List;

public class GuideMenu extends AbstractMenu {
  public GuideMenu(List<MenuItemFactory> menuItemFactoryList) {
    super("Guide");
    menuItemFactoryList.forEach(factory -> this.addMenuItem(factory.create()));
  }
}
