package com.example.camera_plugin.helpers;

import android.hardware.Camera;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.camera_plugin.R;

public class CameraUI {
    private Button button;
    private View view;
    private Camera mCamera;
    private CameraPreview mPreview;

    public CameraUI(View view){
        this.view = view;
        //this.addListeners();
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this.view.getContext(), mCamera);
        FrameLayout preview = this.view.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    private void addListeners(){
       /* button = this.view.findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

            }
        });*/
    }

    public void startCamera(){

    }

    public String takePicutre(){
        return "Foto tirada!";
    }

    public void releaseCamera(){
        this.mCamera.release();
    }

    private Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e){
        }
        return c;
    }


}
