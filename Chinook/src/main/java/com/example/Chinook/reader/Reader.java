package com.example.Chinook.reader;

import java.util.Scanner;

public class Reader {

    public String read(String question) {
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
