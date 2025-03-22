module com.example.guiproject {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires javafx.media;

  opens com.example.guiproject to
      javafx.fxml;

  exports com.example.guiproject;
  exports com.example.guiproject.control;

  opens com.example.guiproject.control to
      javafx.fxml;

  exports com.example.guiproject.view;

  opens com.example.guiproject.view to
      javafx.fxml;

  exports com.example.guiproject.view.footer;

  opens com.example.guiproject.view.footer to
      javafx.fxml;
}
