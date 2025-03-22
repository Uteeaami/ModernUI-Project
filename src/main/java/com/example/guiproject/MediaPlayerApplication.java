package com.example.guiproject;

import com.example.guiproject.control.KeyboardEventHandler;
import com.example.guiproject.control.MediaController;
import com.example.guiproject.control.MediaControllerAPI;
import com.example.guiproject.menu.MainMenuBar;
import com.example.guiproject.view.ContainerType;
import com.example.guiproject.view.GuideContainer;
import com.example.guiproject.view.MediaContainer;
import com.example.guiproject.view.PlayListContainer;
import com.example.guiproject.view.footer.FooterComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MediaPlayerApplication extends Application {

  private final FooterComponent footerComponent;
  private final MainMenuBar mainMenuBar;
  private final StackPane contentContainer;
  private final MediaControllerAPI mediaControllerAPI;

  public MediaPlayerApplication() {
    MediaContainer mediaContainer = new MediaContainer();
    PlayListContainer playListContainer = new PlayListContainer();
    GuideContainer guideContainer = new GuideContainer();

    this.mainMenuBar = new MainMenuBar();
    this.footerComponent = new FooterComponent();
    this.contentContainer = new StackPane();

    mediaControllerAPI = MediaController.getInstance();
    mediaControllerAPI.initialize(mediaContainer.getMediaView(), footerComponent);

    ContainerManager.registerContainer(ContainerType.MEDIA_CONTAINER, mediaContainer.getView());
    ContainerManager.registerContainer(
        ContainerType.PLAYLIST_CONTAINER, playListContainer.getView());
    ContainerManager.registerContainer(ContainerType.GUIDE_CONTAINER, guideContainer.getView());

    ContainerManager.setContainer(contentContainer);
    contentContainer.getChildren().add(mediaContainer.getView());
  }

  @Override
  public void start(Stage mainStage) {
    mainStage.setTitle("Media Application");
    MediaPlayerApplication mediaPlayerApplication = new MediaPlayerApplication();
    Scene mainScene = mediaPlayerApplication.create();

    mainStage.setScene(mainScene);

    mainStage.setWidth(600);
    mainStage.setHeight(450);
    mainStage.setMinWidth(500);
    mainStage.setMinHeight(117);

    mainStage.show();
  }

  private Scene create() {
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(mainMenuBar);
    borderPane.setCenter(contentContainer);
    borderPane.setBottom(footerComponent);

    Scene scene = new Scene(borderPane, 600, 450);
    KeyboardEventHandler keyboardHandler =
        new KeyboardEventHandler(mediaControllerAPI, mainMenuBar);
    keyboardHandler.attachToScene(scene);

    return scene;
  }

  public static void main(String[] args) {
    launch();
  }
}
