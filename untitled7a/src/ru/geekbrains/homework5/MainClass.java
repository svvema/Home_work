package ru.geekbrains.homework5;

// Домашнее задание
// Организуем гонки
// Есть набор правил:
// Все участники должны стартовать одновременно, несмотря на то что на подготовку у каждого уходит разное время
// В туннель не может заехать одновременно больше половины участников(условность)
// Попробуйте все это синхронизировать
// Как только первая машина пересекает финиш, необходимо ее объявить победителем(победитель
// должен быть только один, и вообще должен быть)
// Только после того как все завершат гонку нужно выдать объявление об окончании
// Можете корректировать классы(в т.ч. конструктор машин)
// и добавлять объекты классов из пакета util.concurrent

import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        final CountDownLatch finishCdl = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(finishCdl, race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            finishCdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
