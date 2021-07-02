package com.example.p1;


import android.hardware.Camera;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;


public class free extends Fragment {
    private String inputString;
    private EditText message;
    private Button bt_submit;
    private HashMap<String,String> morseConverter;

    private Camera camera;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        camera.release();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        camera = Camera.open();
        View v = inflater.inflate(R.layout.fragment_free, container, false);
        message = (EditText) v.findViewById(R.id.ti_input);
        bt_submit = (Button) v.findViewById(R.id.bt_submit);
        Morse morse = new Morse();
        morse.setMaps();
        morseConverter = morse.getMaps();

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputString = message.getText().toString().replace(" ","");
                flashMessage();

                //Toast.makeText(getContext(), inputString, Toast.LENGTH_SHORT).show();
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
                Camera.Parameters param = camera.getParameters();
                param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(param);
                camera.startPreview();
//
//                try{
//                    Thread.sleep(1500);
//                } catch (InterruptedException e){
//
//                }
                param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(param);
                camera.stopPreview();

            }

            private void shortFlash() {
                Camera.Parameters param = camera.getParameters();
                param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(param);
                camera.startPreview();
//
//                try{
//                    Thread.sleep(500);
//                } catch (InterruptedException e){
//                    Toast.makeText(getContext(), "sleep Error", Toast.LENGTH_SHORT).show();
//                }
                param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(param);
                camera.stopPreview();

            }
        });


        return v;
    }

}