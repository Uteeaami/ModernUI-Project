package com.example.guiproject.control;

import com.example.guiproject.view.footer.FooterComponent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaController implements MediaControllerAPI {
  private static MediaController instance;

  private MediaPlayer mediaPlayer;
  private List<File> playlist;
  private int currentIndex = -1;
  private double currentVolume = 0.5;

  private MediaView mediaView;
  private FooterComponent mediaFooterComponent;
  private Runnable onPlaylistUpdated;

  private MediaController() {}

  public static MediaController getInstance() {
    if (instance == null) {
      instance = new MediaController();
    }
    return instance;
  }

  @Override
  public void initialize(MediaView mediaView, FooterComponent footerComponent) {
    this.mediaView = mediaView;
    this.mediaFooterComponent = footerComponent;
    this.mediaView.setFitHeight(320);

    this.mediaFooterComponent.volumeComponent.volumeSlider.setValue(50);
    this.mediaFooterComponent.volumeComponent.volumeSlider.setMin(0);
    this.mediaFooterComponent.volumeComponent.volumeSlider.setMax(100);

    this.currentVolume = mediaFooterComponent.volumeComponent.volumeSlider.getValue() / 100;

    this.mediaFooterComponent
        .volumeComponent
        .volumeSlider
        .valueProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              this.currentVolume = newVal.doubleValue() / 100;
              if (mediaPlayer != null) {
                mediaPlayer.setVolume(currentVolume);
              }
            });
  }

  @Override
  public void loadMedia(File file) {
    if (file != null && mediaView != null) {
      String mediaPath = file.toURI().toString();

      if (mediaPlayer != null) {
        mediaPlayer.stop();
        mediaPlayer.dispose();
      }

      Media media = new Media(mediaPath);
      mediaPlayer = new MediaPlayer(media);
      mediaView.setMediaPlayer(mediaPlayer);

      if (mediaFooterComponent != null) {
        mediaFooterComponent.playButton.setDisable(false);
        mediaFooterComponent.progressBarComponent.progressBar.setDisable(false);
        mediaPlayer.setVolume(mediaFooterComponent.volumeComponent.volumeSlider.getValue() / 100);
        mediaPlayer.setVolume(currentVolume);

        mediaPlayer.setOnReady(
            () -> {
              Duration totalDuration = media.getDuration();
              mediaFooterComponent.progressBarComponent.totalTimeLabel.setText(
                  formatTime(totalDuration));
            });

        mediaPlayer
            .currentTimeProperty()
            .addListener(
                (observable, oldValue, newValue) -> {
                  if (!mediaFooterComponent.progressBarComponent.progressBar.isValueChanging()) {
                    double progress = newValue.toSeconds() / media.getDuration().toSeconds();
                    mediaFooterComponent.progressBarComponent.progressBar.setValue(progress * 100);
                    mediaFooterComponent.progressBarComponent.currentTimeLabel.setText(
                        formatTime(newValue));
                  }
                });

        mediaPlayer.setOnEndOfMedia(this::playNextMedia);
      }

      mediaPlayer.play();
      if (mediaFooterComponent != null) {
        mediaFooterComponent.setPlayingStatus(true);
      }
    }
  }

  public void setOnPlaylistUpdated(Runnable listener) {
    this.onPlaylistUpdated = listener;
  }

  @Override
  public void setPlaylist(List<File> playlist) {
    this.playlist = playlist;
    if (onPlaylistUpdated != null) {
      onPlaylistUpdated.run();
    }
  }

  @Override
  public List<File> getPlaylist() {
    return playlist == null ? new ArrayList<>() : playlist;
  }

  @Override
  public void playFirstMedia() {
    if (!playlist.isEmpty()) {
      currentIndex = 0;
      loadMedia(playlist.get(currentIndex));
    }
  }

  @Override
  public void playNextMedia() {
    if (playlist != null && !playlist.isEmpty()) {
      currentIndex = (currentIndex + 1) % playlist.size();
      loadMedia(playlist.get(currentIndex));
    }
  }

  @Override
  public void playPreviousMedia() {
    if (playlist != null && !playlist.isEmpty()) {
      currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
      loadMedia(playlist.get(currentIndex));
    }
  }

  @Override
  public void pause() {
    if (mediaPlayer != null) {
      mediaPlayer.pause();
      if (mediaFooterComponent != null) {
        mediaFooterComponent.setPlayingStatus(false);
      }
    }
  }

  @Override
  public void play() {
    if (mediaPlayer != null) {
      mediaPlayer.play();
      if (mediaFooterComponent != null) {
        mediaFooterComponent.setPlayingStatus(true);
      }
    }
  }

  @Override
  public void stop() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      if (mediaFooterComponent != null) {
        mediaFooterComponent.setPlayingStatus(false);
      }
    }
  }

  @Override
  public void setCurrentIndex(int index) {
    this.currentIndex = index;
  }

  @Override
  public void playOrPause(boolean isPlaying) {
    if (mediaPlayer != null) {
      if (isPlaying) {
        mediaPlayer.pause();
        if (mediaFooterComponent != null) {
          mediaFooterComponent.setPlayingStatus(false);
        }
      } else {
        mediaPlayer.play();
        if (mediaFooterComponent != null) {
          mediaFooterComponent.setPlayingStatus(true);
        }
      }
    }
  }

  @Override
  public void adjustVolume(double delta) {
    double newVolume = currentVolume + delta;
    if (newVolume >= 0.0 && newVolume <= 1.0) {
      currentVolume = newVolume;
      if (mediaPlayer != null) {
        mediaPlayer.setVolume(currentVolume);
        if (mediaFooterComponent != null) {
          mediaFooterComponent.volumeComponent.volumeSlider.setValue(currentVolume * 100);
        }
      }
    }
  }

  @Override
  public void skipForward() {
    if (mediaPlayer != null) {
      Duration currentTime = mediaPlayer.getCurrentTime();
      Duration newTime = currentTime.add(Duration.seconds(10));
      if (newTime.lessThan(mediaPlayer.getTotalDuration())) {
        mediaPlayer.seek(newTime);
      } else {
        mediaPlayer.seek(mediaPlayer.getTotalDuration()); // Jump to the end if exceeding duration
      }
    }
  }

  @Override
  public void skipBackward() {
    if (mediaPlayer != null) {
      Duration currentTime = mediaPlayer.getCurrentTime();
      Duration newTime = currentTime.subtract(Duration.seconds(10));
      if (newTime.greaterThan(Duration.ZERO)) {
        mediaPlayer.seek(newTime);
      } else {
        mediaPlayer.seek(Duration.ZERO); // Jump to the beginning if below zero
      }
    }
  }

  private String formatTime(Duration duration) {
    int minutes = (int) duration.toMinutes();
    int seconds = (int) duration.toSeconds() % 60;
    return String.format("%02d:%02d", minutes, seconds);
  }
}
