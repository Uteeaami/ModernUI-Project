package com.example.guiproject;

import com.example.guiproject.view.ContainerType;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ContainerManager {
  private static StackPane container;
  private static Node currentContainer;
  private static final Map<ContainerType, Node> containerTypeNodeMap = new HashMap<>();

  public static void setContainer(StackPane container) {
    ContainerManager.container = container;
  }

  public static void registerContainer(ContainerType containerType, Node view) {
    containerTypeNodeMap.put(containerType, view);
  }

  public static void switchContainer(ContainerType containerType) {
    Node view = containerTypeNodeMap.get(containerType);
    if (view != null && container != null) {
      if (currentContainer != view) {
        container.getChildren().setAll(view);
        currentContainer = view;
      }
    } else {
      System.err.println("View not found: " + containerType.toString());
    }
  }
}
