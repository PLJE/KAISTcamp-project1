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
        maps.put(".", "010101");
        maps.put(",", "110011");
        maps.put("?", "001100");
        maps.put("!", "101011");
        maps.put("-", "100001");
        maps.put("/", "10010");
        maps.put("@", "011010");
    }
    public void reverseSetMaps(){
        maps.put("01", "A");
        maps.put("1000","B");
        maps.put("1010","C");
        maps.put("100","D");
        maps.put("0","E");
        maps.put("0010","F");
        maps.put("110","G");
        maps.put("0000","H");
        maps.put("00","I");
        maps.put("0111","J");
        maps.put("101","K");
        maps.put("0100","L");
        maps.put("11","M");
        maps.put("10","N");
        maps.put("111","O");
        maps.put("0110","P");
        maps.put("1101","Q");
        maps.put("010","R");
        maps.put("000","S");
        maps.put("1","T");
        maps.put("001","U");
        maps.put("0001","V");
        maps.put("011","W");
        maps.put("1001","X");
        maps.put("1011","Y");
        maps.put("1100","Z");
        maps.put("11111","0");
        maps.put("01111","1");
        maps.put("00111", "2");
        maps.put("00011", "3");
        maps.put("00001", "4");
        maps.put("00000", "5");
        maps.put("10000", "6");
        maps.put("11000", "7");
        maps.put("11100", "8");
        maps.put("11110", "9");
        maps.put("010101","." );
        maps.put("110011","," );
        maps.put("001100","?" );
        maps.put("101011","!" );
        maps.put("100001","-" );
        maps.put("10010","/" );
        maps.put("011010","@" );


    }
    public HashMap<String,String> getMaps(){
        return maps;
    }
}
