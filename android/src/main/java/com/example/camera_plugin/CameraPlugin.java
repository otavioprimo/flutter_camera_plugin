package com.example.camera_plugin;

import io.flutter.plugin.common.PluginRegistry.Registrar;

/** CameraPlugin */
public class CameraPlugin {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
      registrar
        .platformViewRegistry()
        .registerViewFactory("cameraplugin", new CameraFactory(registrar));
  }
}
