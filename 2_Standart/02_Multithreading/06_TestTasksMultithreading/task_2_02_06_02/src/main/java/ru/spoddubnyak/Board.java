package ru.spoddubnyak;

import java.util.concurrent.locks.ReentrantLock;

public class Board {
    ReentrantLock[][] board;

    public Board() {
        this.board = new ReentrantLock[5][5];
    }
}
