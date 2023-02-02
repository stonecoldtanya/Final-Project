package com.example.checkers;

import com.example.checkers.checkers.Board;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(12);
        System.out.println(b);
        System.out.println(b.getPieceBlack());
    }
}
