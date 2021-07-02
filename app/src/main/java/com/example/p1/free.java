package com.example.p1;


import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import static android.content.Context.CAMERA_SERVICE;


public class free extends Fragment {
    private String inputString;
    private EditText message;
    private Button bt_submit;
    private HashMap<String,String> morseConverter;

    private static CameraManager mCameraManager;
    private static boolean mFlashOn = false;
    private String mCameraId;

    public void flashLightOn() {
        mFlashOn = true;
        try {
            mCameraManager.setTorchMode(mCameraId, true);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void flashLightOff() {
        mFlashOn = false;
        //stopFlicker();
        //stopSOS();
        try {
            mCameraManager.setTorchMode(mCameraId, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void sleep(int time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException e){

        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_free, container, false);
        message = (EditText) v.findViewById(R.id.ti_input);
        bt_submit = (Button) v.findViewById(R.id.bt_submit);
        Morse morse = new Morse();
        morse.setMaps();
        morseConverter = morse.getMaps();


        mCameraManager = (CameraManager) getActivity().getSystemService(CAMERA_SERVICE);
        if (mCameraId == null) {
            try {
                for (String id : mCameraManager.getCameraIdList()) {
                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if (flashAvailable != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        mCameraId = id;
                        break;
                    }
                }
            } catch (CameraAccessException e) {
                mCameraId = null;
                e.printStackTrace();
            }
        }//카메라 세팅

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputString = message.getText().toString().replace(" ","");
                flashMessage();

            }

            private void flashMessage() {
                String flashString = "";
                for(String i : inputString.split("")){
                    flashString+=morseConverter.get(i.toUpperCase());
                }
                for(String i : flashString.split("")){
                    if(i.equals("0")){
                        shortFlash();
                    }
                    else if(i.equals("1")){
                        longFlash();
                    }
                }

            }

            private void longFlash() {
                flashLightOn();
                sleep(1500);
                flashLightOff();
                sleep(300);
            }

            private void shortFlash() {
                flashLightOn();
                sleep(500);
                flashLightOff();
                sleep(300);

            }
        });


        return v;
    }

}