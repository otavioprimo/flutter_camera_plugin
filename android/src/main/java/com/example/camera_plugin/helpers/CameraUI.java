package com.example.camera_plugin.helpers;

import android.app.Activity;
import android.hardware.Camera;
import android.view.View;
import android.widget.FrameLayout;

import com.example.camera_plugin.R;

public class CameraUI extends Activity {
    private View view;
    private Camera mCamera;
    private CameraPreview mPreview;
    private Activity activity;

    public CameraUI(View view, Activity activity) {
        this.view = view;
        this.activity = activity;

        this.mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        this.mPreview = new CameraPreview(this.view.getContext(), mCamera);
        FrameLayout preview = this.view.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    public void startCamera() {

    }

    public String takePicutre() {
        return "Foto tirada!";
    }

    public void releaseCamera() {
        this.mCamera.release();
    }

    private Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }
}
