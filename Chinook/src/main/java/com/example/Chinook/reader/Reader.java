package com.example.Chinook.reader;

import java.util.Scanner;

public class Reader {
    /**
     * This is a class for reading input from the keyboard.
     * @param question
     * @return String
     * @exception Exception if wrong type of input.
     */
    public String read(String question) {
        /**
         * Read input with printing the question.
         * Return reading input.
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
