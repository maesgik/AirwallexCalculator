package com.maesgik.sample.airwallex;

import java.util.Scanner;

public class RPNCommandLine {
    public static void main(String[] args) {
        RPNCalculator calculator = new RPNCalculator();
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Airwallex RPN Calculator!");
        System.out.println("Author: Jiahua Chen");
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            calculator.parse(input);
        }
    }

}
