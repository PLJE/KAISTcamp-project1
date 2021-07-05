package com.example.p1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static org.opencv.imgproc.Imgproc.CC_STAT_AREA;
import static org.opencv.imgproc.Imgproc.CC_STAT_HEIGHT;
import static org.opencv.imgproc.Imgproc.CC_STAT_LEFT;
import static org.opencv.imgproc.Imgproc.CC_STAT_TOP;
import static org.opencv.imgproc.Imgproc.CC_STAT_WIDTH;


public class cameraActivity extends AppCompatActivity
        implements CameraBridgeViewBase.CvCameraViewListener2 {


    private JavaCameraView javaCameraView;
    private static final String TAG = "cameraActivity";
    private Mat mRGBA;
    private Mat labeled;
    private Mat stat, centroid;
    private String morse = "에렐렐ㄹ레";
    private long count= System.currentTimeMillis();
    private int areaSum=0;
    private boolean stateNow=false;   //ture라면 모스부호 켜짐, false라면 모스부호 꺼짐

    private TextView txt_morse;


    BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(cameraActivity.this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case BaseLoaderCallback.SUCCESS: {
                    javaCameraView.enableView();
                    break;
                }
                default:{
                    super.onManagerConnected(status);
                    break;
                }
            }

        }
    };


    private void updateMorse(long time, boolean bool){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_morse.setText(morse);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        javaCameraView = (JavaCameraView) findViewById(R.id.activity_surface_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(cameraActivity.this);
        txt_morse = (TextView) findViewById(R.id.txt_morse);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRGBA = new Mat(height, width, CvType.CV_8UC4);
        stat = Mat.zeros(new Size(0, 0), 0);
        centroid = Mat.zeros(new Size(0, 0), 0);
        labeled = new Mat(mRGBA.size(), mRGBA.type());
    }

    @Override
    public void onCameraViewStopped() {
        mRGBA.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
    //    System.gc();



        Mat grayScaleGaussianBlur = new Mat();
        double gaussianBlurValue = 11;
        mRGBA = inputFrame.rgba().t();
        int tempSum=0;




        Imgproc.cvtColor(mRGBA, grayScaleGaussianBlur, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(grayScaleGaussianBlur, grayScaleGaussianBlur, new Size(gaussianBlurValue, gaussianBlurValue), 0);
        Imgproc.threshold(grayScaleGaussianBlur, grayScaleGaussianBlur, 200, 255, Imgproc.THRESH_BINARY);
       // Imgproc.erode(grayScaleGaussianBlur, grayScaleGaussianBlur, new Mat());
       // Imgproc.dilate(grayScaleGaussianBlur, grayScaleGaussianBlur, new Mat());  //이미지 프로세싱



        int compNum = Imgproc.connectedComponentsWithStats(grayScaleGaussianBlur, labeled, stat, centroid, 8);

        for(int i=0; i<compNum; i++){
            int area = (int) stat.get(i, CC_STAT_AREA)[0];
            if(area<300){ continue; }
            int left = (int) stat.get(i, CC_STAT_LEFT)[0];
            int top = (int) stat.get(i, CC_STAT_TOP)[0];
            int width = (int) stat.get(i, CC_STAT_WIDTH)[0];
            int height = (int) stat.get(i, CC_STAT_HEIGHT)[0];
            tempSum+=area;

            // 라벨링 박스
            Imgproc.rectangle(mRGBA, new Point(left, top), new Point(left + width, top + height), new Scalar(0, 0, 255), 3);
        }
        if(Math.abs(tempSum-areaSum)>300) {
            if (tempSum - areaSum > 0 && !stateNow) {
                stateNow = true;           // 기존보다 밝아진경우(켜진경우)
                long tempTime=System.currentTimeMillis();
                updateMorse(tempTime-count, stateNow);
                count = tempTime;
            } else if(tempSum - areaSum < 0 && stateNow){
                stateNow = false;          // 기존보다 어두워진경우(꺼진경우)
                long tempTime=System.currentTimeMillis();
                updateMorse(tempTime-count, stateNow);
                count = tempTime;
            }
            areaSum = tempSum;
        }
        Core.flip(mRGBA, mRGBA, 1);
        grayScaleGaussianBlur.release();
        return mRGBA;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(javaCameraView != null){
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(javaCameraView != null){
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV is Configured or Connected Succesfully");
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);
        }
        else{
            Log.d(TAG, "OpenCV not Working or Loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, baseLoaderCallback);
        }
    }
}