import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:camera_plugin/camera_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  CameraController cameraController;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('Flutter Camera Native'),
          ),
          body: Stack(
            children: <Widget>[
              CameraPlugin(
                onCameraCreated: onCameraCreated,
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 16.0),
                child: RaisedButton(
                  child: Text("CLICK"),
                  onPressed: () async {
                    var result = await cameraController.takePicture();
                    print(result);
                  },
                ),
              )
            ],
          )),
    );
  }

  void onCameraCreated(cameraController) {
    this.cameraController = cameraController;
    this.cameraController.start();
  }
}
