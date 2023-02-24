package com.sebshader.roman;

import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    static Scanner input = new Scanner(System.in);

    public static void main( String[] args )
    {
        String inputString;
        RomanNumeral aNumeral;
        boolean quit = false;
        while (!quit) {
            System.out.println("enter roman numeral, integer, or empty string to quit");
            inputString = input.nextLine();
            if(inputString.isEmpty()) {
                quit = true;
                continue;
            }
            switch(inputString.charAt(0)) {
                case 'M':
                case 'D':
                case 'C':
                case 'L':
                case 'X':
                case 'V':
                case 'I':
                    try{
                        aNumeral = new RomanNumeral(inputString);
                    }
                    catch(Exception e) {
                        System.out.println("input error: " + e.getMessage());
                        continue;
                    }
                    System.out.println(aNumeral.toInt());
                    break;
                default:
                    try{
                        aNumeral = new RomanNumeral(Integer.parseInt(inputString));
                    }
                    catch(Exception e) {
                        System.out.println("input error: " + e.getMessage());
                        continue;
                    }
                    System.out.println(aNumeral.toString());
            }
        }
        System.out.println("Bye!");
    }
}
