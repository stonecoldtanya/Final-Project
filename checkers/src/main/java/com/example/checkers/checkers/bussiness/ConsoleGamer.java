package com.example.checkers.checkers.bussiness;

import java.awt.*;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component("consoleGamer")
public class ConsoleGamer implements Player {
    private String name;
    private char colour;

    public ConsoleGamer() {
    }

    public ConsoleGamer(String name, char colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return this.name;
    }

    public char getColour() {
        return this.colour;
    }

    @Override
    public Move getNextMove(Board state) {
        System.out.println("Current state is:");
        System.out.println(state.toString());

        System.out.println("What is your next move?");
        Scanner input = new Scanner(System.in);

        int row = input.nextInt();
        int column = input.nextInt();
        Point current = new Point(row,column);
        int rowNext = input.nextInt();
        int columnNext = input.nextInt();
        Point next = new Point(rowNext,columnNext);

        return new Move(current, next);

    }

}
