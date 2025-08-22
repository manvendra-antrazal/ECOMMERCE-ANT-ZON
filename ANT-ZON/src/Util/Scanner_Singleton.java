package Util;

import java.util.Scanner;

public class Scanner_Singleton {

    private static Scanner_Singleton input;
    private static Scanner inputscanner;
    
    private  Scanner_Singleton() {
        inputscanner = new Scanner(System.in);                 // here it takes input from the keyboard
    }
    
    public static Scanner_Singleton getInstance() {
        if (input == null) {                             // here it created instance only one time 
                input = new Scanner_Singleton();
        }
        return input;
    }    
    
    public Scanner getScanner() {
        return inputscanner;
    }
}
