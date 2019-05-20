import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void CameraCreateCallBack(CameraController controller);

class CameraPlugin extends StatefulWidget {
  final CameraCreateCallBack onCameraCreated;
  final double height;

  CameraPlugin({Key key, @required this.onCameraCreated, this.height});

  @override
  _CameraPluginState createState() => _CameraPluginState();
}

class _CameraPluginState extends State<CameraPlugin> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return Container(
        height: widget.height,
        child: AndroidView(
          viewType: 'cameraplugin',
          onPlatformViewCreated: onPlatformViewCreated,
          creationParamsCodec: const StandardMessageCodec(),
        ),
      );
    }

    return new Text(
        '$defaultTargetPlatform is not yet supported by this plugin');
  }

  Future<void> onPlatformViewCreated(id) async {
    if (widget.onCameraCreated == null) {
      return;
    }

    widget.onCameraCreated(new CameraController.init(id));
  }
}

class CameraController {
  MethodChannel _channel;

  CameraController.init(int id) {
    _channel = new MethodChannel('cameraplugin_$id');
  }

  Future<void> start() async {
    return _channel.invokeMethod('start');
  }

  Future<void> pause() async {
    return _channel.invokeMethod('pause');
  }

  Future<void> stop() async {
    return _channel.invokeMethod('stop');
  }

  Future<String> takePicture() async {
    return _channel.invokeMethod('takePicture');
  }

  Future<void> switchCamera() async {
    return _channel.invokeMethod('switchCamera');
  }

  Future<void> startRecord() async {}

  Future<void> stopRecord() async {}

  Future<void> cancelRecord() async {}
}
