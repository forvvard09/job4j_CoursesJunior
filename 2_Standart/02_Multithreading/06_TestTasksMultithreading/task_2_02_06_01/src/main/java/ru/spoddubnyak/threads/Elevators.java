package ru.spoddubnyak.threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Elevators {

    private Scanner in;
    private volatile boolean flag;



    public Elevators() {
        this.in = new Scanner(System.in);
        this.flag = true;
    }

    public boolean isFlag() {
        return this.flag;
    }

    private class InputThread implements Runnable {

        @Override
        public void run() {
            while (flag) {
                //System.out.println("                    Лифт проехжает этаж");
                //System.out.println("                    Лифт открывает двери");
                //System.out.println("                    Лифт закрывает двери");
                System.out.println(flag);
                try {
                    Thread.sleep(999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class OutputInfoPrintThread implements Runnable {

        @Override
        public void run() {
            int inputNumber = 0;

            do {
                System.out.println("Введите число>: ");
                inputNumber = in.nextInt();

            } while (inputNumber != 9);
            flag = false;
        }
    }

    public void startThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new InputThread());
        executorService.submit(new OutputInfoPrintThread());
        if (!flag) {
            System.out.println("Флаг отрицательный...");
            executorService.shutdownNow();
            System.out.println("Остановка сервиса + " + executorService.isShutdown());
        }
    }


    public static void main(String[] args) {
        Elevators elevators = new Elevators();
        elevators.startThreads();


    }
}
