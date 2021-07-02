package com.example.p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Morse {
    private HashMap<String , String> maps = new HashMap<String,String>();

    public void setMaps(){
        maps.put("A" ,"01");
        maps.put("B", "1000");
        maps.put("C","1010");
        maps.put("D","100");
        maps.put("E","0");
        maps.put("F", "0010");
        maps.put("G","110");
        maps.put("H","0000");
        maps.put("I","00");
        maps.put("J","0111");
        maps.put("K","101");
        maps.put("L","0100");
        maps.put("M","11");
        maps.put("N","10");
        maps.put("O","111");
        maps.put("P","0110");
        maps.put("Q","1101");
        maps.put("R","010");
        maps.put("S","000");
        maps.put("T","1");
        maps.put("U","001");
        maps.put("V","0001");
        maps.put("W","011");
        maps.put("X","1001");
        maps.put("Y","1011");
        maps.put("Z","1100");
        maps.put("0","11111");
        maps.put("1","01111");
        maps.put("2","00111");
        maps.put("3","00011");
        maps.put("4","00001");
        maps.put("5","00000");
        maps.put("6","10000");
        maps.put("7","11000");
        maps.put("8","11100");
        maps.put("9","11110");
    }
    public HashMap<String,String> getMaps(){
        return maps;
    }
}