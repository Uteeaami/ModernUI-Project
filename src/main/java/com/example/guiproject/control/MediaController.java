package com.example.guiproject.control;

import com.example.guiproject.view.footer.FooterBar;
import com.example.guiproject.view.footer.VolumeSlider;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaController implements MediaControllerAPI {
  private static MediaController instance;
  private final double INITIAL_VOLUME = 0.5;
  private final int INITIAL_INDEX = -1;

  private MediaPlayer mediaPlayer;
  private List<File> playlist;
  private int currentIndex = INITIAL_INDEX;
  private double currentVolume = INITIAL_VOLUME;

  private MediaView mediaView;
  private FooterBar mediaFooterBar;
  private Runnable onPlaylistUpdated;

  private MediaController() {}

  public static MediaController getInstance() {
    if (instance == null) {
      instance = new MediaController();
    }
    return instance;
  }

  @Override
  public void initialize(MediaView mediaView, FooterBar footerBar) {
    this.mediaView = mediaView;
    this.mediaFooterBar = footerBar;
    setupMediaView();
    setupVolumeSlider();
  }

  // TODO: As a improvement, make the view dynamic
  private void setupMediaView() {
    this.mediaView.setFitHeight(320);
  }

  private void setupVolumeSlider() {
    if (mediaFooterBar != null) {
      VolumeSlider volumeSlider = mediaFooterBar.volumeSlider;
      volumeSlider.slider.setValue(50);
      volumeSlider.slider.setMin(0);
      volumeSlider.slider.setMax(100);

      this.currentVolume = volumeSlider.slider.getValue() / 100;

      volumeSlider
          .slider
          .valueProperty()
          .addListener(
              (obs, oldVal, newVal) -> {
                this.currentVolume = newVal.doubleValue() / 100;
                if (mediaPlayer != null) {
                  mediaPlayer.setVolume(currentVolume);
                }
              });
    }
  }

  @Override
  public void loadMedia(File file) {
    if (file == null || mediaView == null) return;

    clearMediaPlayer();

    Media media = new Media(file.toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    mediaView.setMediaPlayer(mediaPlayer);

    setupMediaPlayer(media);

    mediaPlayer.play();
    if (mediaFooterBar != null) {
      mediaFooterBar.setPlayingStatus(true);
    }
  }

  private void setupMediaPlayer(Media media) {
    if (mediaFooterBar == null) return;

    mediaFooterBar.playButton.setDisable(false);
    mediaFooterBar.progressBar.slider.setDisable(false);
    mediaPlayer.setVolume(currentVolume);

    mediaFooterBar.progressBar.slider.setOnMouseClicked(
        event -> {
          if (mediaPlayer != null) {
            double clickPosition = event.getX() / mediaFooterBar.progressBar.slider.getWidth();
            Duration seekTime = mediaPlayer.getTotalDuration().multiply(clickPosition);
            mediaPlayer.seek(seekTime);
          }
        });

    mediaPlayer.setOnReady(() -> updateTotalDurationLabel(media));

    mediaPlayer
        .currentTimeProperty()
        .addListener(
            (observable, oldValue, newValue) -> updateProgressBar(newValue, media.getDuration()));

    mediaPlayer.setOnEndOfMedia(this::playNextMedia);
  }

  private void updateTotalDurationLabel(Media media) {
    Duration totalDuration = media.getDuration();
    mediaFooterBar.progressBar.totalTimeLabel.setText(formatTime(totalDuration));
  }

  private void updateProgressBar(Duration newValue, Duration totalDuration) {
    if (!mediaFooterBar.progressBar.slider.isValueChanging()) {
      double progress = newValue.toSeconds() / totalDuration.toSeconds();
      mediaFooterBar.progressBar.slider.setValue(progress * 100);
      mediaFooterBar.progressBar.currentTimeLabel.setText(formatTime(newValue));
    }
  }

  private String formatTime(Duration duration) {
    int minutes = (int) duration.toMinutes();
    int seconds = (int) duration.toSeconds() % 60;
    return String.format("%02d:%02d", minutes, seconds);
  }

  private void clearMediaPlayer() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.dispose();
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
  public void stop() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      if (mediaFooterBar != null) {
        mediaFooterBar.setPlayingStatus(false);
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
        if (mediaFooterBar != null) {
          mediaFooterBar.setPlayingStatus(false);
        }
      } else {
        mediaPlayer.play();
        if (mediaFooterBar != null) {
          mediaFooterBar.setPlayingStatus(true);
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
        if (mediaFooterBar != null) {
          mediaFooterBar.volumeSlider.slider.setValue(currentVolume * 100);
        }
      }
    }
  }

  @Override
  public void pause() {
    if (mediaPlayer != null) {
      mediaPlayer.pause();
      if (mediaFooterBar != null) {
        mediaFooterBar.setPlayingStatus(false);
      }
    }
  }

  @Override
  public void play() {
    if (mediaPlayer != null) {
      mediaPlayer.play();
      if (mediaFooterBar != null) {
        mediaFooterBar.setPlayingStatus(true);
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
        mediaPlayer.seek(mediaPlayer.getTotalDuration());
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
        mediaPlayer.seek(Duration.ZERO);
      }
    }
  }
}
