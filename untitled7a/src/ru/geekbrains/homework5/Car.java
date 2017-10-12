package ru.geekbrains.homework5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static CyclicBarrier cb = new CyclicBarrier(MainClass.CARS_COUNT);

    private static boolean winner = false;
    private static boolean ready = false;
    private final static Object readyO = new Object();
    private static int readyCount = 0;

    static {
        CARS_COUNT = 0;

    }

    private CountDownLatch cdL;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(CountDownLatch cdL, Race race, int speed) {

        this.cdL = cdL;
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {

            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            readyCount++;
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (readyO) {
            if (readyCount == MainClass.CARS_COUNT && !ready)
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            ready = true;
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        synchronized (readyO) {
            if (!winner) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победил " + this.getName() + "!!!");
                winner = true;
            }
        }
        cdL.countDown();
    }
}

