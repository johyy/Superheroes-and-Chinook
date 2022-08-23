package com.example.Chinook.reader;

import java.util.Scanner;

public class Reader {

    public String read(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
