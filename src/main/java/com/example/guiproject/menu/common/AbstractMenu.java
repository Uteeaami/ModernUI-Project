package com.example.guiproject.menu.common;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public abstract class AbstractMenu extends Menu {
  public AbstractMenu(String title) {
    this.setText(title);
  }

  public void addMenuItem(MenuItem item) {
    this.getItems().add(item);
  }
}
