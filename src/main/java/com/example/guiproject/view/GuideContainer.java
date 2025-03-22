package com.example.guiproject.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class GuideContainer {

  private final StackPane initialContainer;

  public GuideContainer() {
    this.initialContainer = new StackPane();

    TextArea textArea = new TextArea();
    textArea.setText(getMediaAppGuide());
    textArea.setWrapText(true);
    textArea.setEditable(false);
    textArea.setStyle("-fx-font-size: 14px;");
    textArea.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE); // Ensure full expansion

    ScrollPane scrollPane = new ScrollPane(textArea);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setStyle("-fx-background-color: transparent;");

    initialContainer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    initialContainer.getChildren().add(scrollPane);
  }

  private String getMediaAppGuide() {
    return "Welcome to my Media Application! This application allows you to play both audio and video files, "
        + "supporting formats such as MP3, MP4, and WAV.\n\n"
        + "How to Use:\n"
        + "- File Menu: Open a single media file for playback or choose a folder to add all supported files to the playlist.\n"
        + "- View Menu: Navigate between the Playlist and Media View. Double-click on a file in the Playlist to start playing it instantly.\n"
        + "- Guide Menu: Access this guide anytime for quick reference.\n\n"
        + "Keyboard Shortcuts:\n"
        + "Menu Shortcuts (select wanted item with arrow keys and press 'ENTER'):\n"
        + "- Ctrl + 1 → Open File Menu\n"
        + "- Ctrl + 2 → Open View Menu\n"
        + "- Ctrl + 3 → Open Guide Menu\n\n"
        + "Playback Controls:\n"
        + "- Ctrl + P → Play\n"
        + "- Ctrl + L → Pause\n"
        + "- Ctrl + S → Stop\n"
        + "- Ctrl + Right Arrow → Play Next Media\n"
        + "- Ctrl + Left Arrow → Play Previous Media\n"
        + "- Shift + Right Arrow → Skip 10 seconds forward\n"
        + "- Shift + Left Arrow → Skip 10 seconds backwards\n\n"
        + "Volume Control:\n"
        + "- Ctrl + Up Arrow → Increase Volume\n"
        + "- Ctrl + Down Arrow → Decrease Volume\n\n"
        + "Happy Listening!\n"
        + "(This text was enhanced by a mutual acquittance)";
  }

  public Node getView() {
    return initialContainer;
  }
}
