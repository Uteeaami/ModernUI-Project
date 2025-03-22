package com.example.guiproject.control;

import com.example.guiproject.view.footer.FooterComponent;
import java.io.File;
import java.util.List;
import javafx.scene.media.MediaView;

public interface MediaControllerAPI {
  void loadMedia(File file);

  void setPlaylist(List<File> playlist);

  List<File> getPlaylist();

  void playFirstMedia();

  void playNextMedia();

  void playPreviousMedia();

  void playOrPause(boolean isPlaying);

  void play();

  void pause();

  void setCurrentIndex(int index);

  void initialize(MediaView mediaView, FooterComponent footerComponent);

  void adjustVolume(double delta);

  void skipForward();

  void skipBackward();

  void stop();
}
