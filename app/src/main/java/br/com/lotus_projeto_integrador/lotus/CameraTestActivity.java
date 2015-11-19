/*
 * Basic no frills app which integrates the ZBar barcode scanner with
 * the camera.
 * 
 * Created by lisah0 on 2012-02-24
 */
package br.com.lotus_projeto_integrador.lotus;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

/* Import ZBar Class files */

public class CameraTestActivity extends Activity
{
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private FrameLayout mFlCameraPreview;
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlCameraPreview = (FrameLayout) findViewById(R.id.cameraPreview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCameraInstance();
        }

        if (mCameraPreview == null) {
            mCameraPreview = new CameraPreview(this, mCamera);
            mFlCameraPreview.addView(mCameraPreview);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

        if (mCameraPreview != null) {
            mFlCameraPreview.removeView(mCameraPreview);
            mCameraPreview = null;
        }
    }

    public static Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {

        }
        return camera;
    }
}
