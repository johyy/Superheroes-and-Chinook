package com.example.Chinook.reader;

import java.util.Scanner;

public class Reader {
    /**
* This is a class for reading input from the keyboard.
     * @param question
     * @return String
*/
    public String read(String question) {
        /**
         * This is the main method
         * which is very important for
         * execution for a java program.
         */
        try {
            System.out.println(question);
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Not and acceptable value");
            e.printStackTrace();
        }
        return null;
    }
}
