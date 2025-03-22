package com.example.guiproject.control;

import com.example.guiproject.menu.MainMenuBar;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyEvent;

public class KeyboardEventHandler {

  private final MediaControllerAPI mediaControllerAPI;
  private final MainMenuBar mainMenuBar;

  public KeyboardEventHandler(MediaControllerAPI mediaControllerAPI, MainMenuBar mainMenuBar) {
    this.mediaControllerAPI = mediaControllerAPI;
    this.mainMenuBar = mainMenuBar;
  }

  public void attachToScene(Scene scene) {
    scene.setOnKeyPressed(this::handleKeyPress);
  }

  private void handleKeyPress(KeyEvent event) {
    if (event.isControlDown()) {
      switch (event.getCode()) {
        case DIGIT1:
          openMenu("File");
          break;
        case DIGIT2:
          openMenu("View");
          break;
        case DIGIT3:
          openMenu("Guide");
          break;
        case P:
          mediaControllerAPI.play();
          break;
        case L:
          mediaControllerAPI.pause();
          break;
        case S:
          mediaControllerAPI.stop();
          break;
        case RIGHT:
          mediaControllerAPI.playNextMedia();
          break;
        case LEFT:
          mediaControllerAPI.playPreviousMedia();
          break;
        case UP:
          mediaControllerAPI.adjustVolume(0.1);
          break;
        case DOWN:
          mediaControllerAPI.adjustVolume(-0.1);
          break;
        default:
          break;
      }
    } else if (event.isShiftDown()) {
      switch (event.getCode()) {
        case RIGHT:
          mediaControllerAPI.skipForward();
          break;
        case LEFT:
          mediaControllerAPI.skipBackward();
          break;
      }
    }
  }

  private void openMenu(String menuName) {
    for (Menu menu : mainMenuBar.getMenus()) {
      if (menu.getText().equalsIgnoreCase(menuName)) {
        menu.show();
        break;
      }
    }
  }
}
