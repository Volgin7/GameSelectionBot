package com.gameselectionbot.gameselectionbot.service;

public class ComputerGames {
    static int numberOfGames=0;
    String name;
    String[][] keys = new String[5][3];

    public ComputerGames(String name,
                         String keys00, String keys01, String keys02,
                         String keys10, String keys11, String keys12,
                         String keys20, String keys21, String keys22,
                         String keys30, String keys31, String keys32,
                         String keys40, String keys41, String keys42) {

        this.name = name;

        this.keys[0][0] = keys00;
        this.keys[0][1] = keys01;
        this.keys[0][2] = keys02;

        this.keys[1][0] = keys10;
        this.keys[1][1] = keys11;
        this.keys[1][2] = keys12;

        this.keys[2][0] = keys20;
        this.keys[2][1] = keys21;
        this.keys[2][2] = keys22;

        this.keys[3][0] = keys30;
        this.keys[3][1] = keys31;
        this.keys[3][2] = keys32;

        this.keys[4][0] = keys40;
        this.keys[4][1] = keys41;
        this.keys[4][2] = keys42;

    }
}
