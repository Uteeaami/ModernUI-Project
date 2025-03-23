# ModernUI-Project

A simple JavaFX-based media player application, that was done to University of Turku's Modern User Interfaces course.
Built using Maven and JavaFX 23.

## 1. Requirements

- Java 9 or later
- Maven

## 2. Getting Started

### Clone the Repository

```
git clone https://github.com/Uteeaami/ModernUI-Project.git
cd GuiProject
```

### Running the Application

#### Using Maven Wrapper

```
./mvnw clean javafx:run  # macOS/Linux
mvnw.cmd clean javafx:run # Windows
```

#### Using Installed Maven

```
mvn clean javafx:run
```

### Building the Application

To compile the project:

```
mvn clean compile
```

To package it into a JAR file:

```
mvn clean package
```

The built JAR file will be located in the `target/` directory.

## 3. How to Use the Media Player

### Opening Media Files

- File Menu: Open a single media file or choose a folder to load multiple files into the playlist.

- View Menu: Switch between the Playlist and Media View.

- Guide Menu: Access the user guide

### Keyboard Shortcuts

#### Menu Shortcuts

(Use arrow keys to select an item and press ENTER)

- Ctrl + 1 → Open File Menu

- Ctrl + 2 → Open View Menu

- Ctrl + 3 → Open Guide Menu

#### Playback Controls

- Ctrl + P → Play

- Ctrl + L → Pause

- Ctrl + S → Stop

- Ctrl + Right Arrow → Play Next Media

- Ctrl + Left Arrow → Play Previous Media

- Shift + Right Arrow → Skip 10 seconds forward

- Shift + Left Arrow → Skip 10 seconds backward

#### Volume Control

- Ctrl + Up Arrow → Increase Volume

- Ctrl + Down Arrow → Decrease Volume