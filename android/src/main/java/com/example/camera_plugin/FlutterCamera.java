package com.example.camera_plugin;

import com.example.camera_plugin.R;
import com.example.camera_plugin.helpers.CameraUI;
import com.example.camera_plugin.utils.Permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.platform.PlatformView;

import android.os.Bundle;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterCamera extends Activity implements PlatformView, MethodCallHandler {
    private final static int MY_PERMISSION_REQUEST_CAMERA = 100;

    Context context;
    Registrar registrar;
    MethodChannel channel;
    View view;
    CameraUI cameraUI;

    FlutterCamera(Context context, Registrar registrar, int id) {
        this.context = context;
        this.registrar = registrar;
        view = getViewFromFile(registrar);

        this.askPermissions(registrar.activity());

        this.cameraUI = new CameraUI(this.view);

        channel = new MethodChannel(registrar.messenger(), "cameraplugin_" + id);

        channel.setMethodCallHandler(this);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Permission.askPermissions(this.registrar.activity());
        switch (call.method) {
            case "start":
                this.cameraUI.startCamera();
                break;
            case "takePicture":
                String res = this.cameraUI.takePicutre();
                result.success(res);
                break;
            default:
                result.notImplemented();
        }
    }

    private View getViewFromFile(Registrar registrar) {
        View view = LayoutInflater.from(registrar.activity()).inflate(R.layout.camera_preview, null);
        return view;
    }

}