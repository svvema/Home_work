package com.company;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {new Cat("Barsik", 1000, 5,0), new Dog("Sharik", 2000, 2, 20)};
        Animal[] a2 = {new Cat("Mursik", 1000, 5,0),
                new Dog("Bobr",1500,3,6),
                new Cat("Kit",500,7,0),
                new Dog("Jopr",300,4,11)};
        Obstacle[] course = {new Cross(500), new Wall(2), new Water(5), new Cross(800)};
        Team t1 = new Team("T1",animals);
        Team t3 = new Team("T3",new Dog("Pesik", 1000, 5,3),
                new Cat("Weird Kotik",1500,30,6),
                new Cat("Kitik",500,7,0),
                new Dog("Good Pesik",2000,14,15));
        Team t4 = new Team("T4",  a2);

        for(int i = 0; i < animals.length; i++){
            for(int j = 0; j < course.length; j++){
                course[j].doIt(animals[i]);
                if(!animals[i].onDistance) break;
            }
        }
        System.out.println();
        Course course1 = new Course();
        course1.trail(t4);
        System.out.println();
        course1.trail(t3);

        System.out.println();
        t1.teamPassInfo();
        System.out.println();
        t1.teamInfo();

        System.out.println();
        t3.teamPassInfo();
        System.out.println();
        t3.teamInfo();

        System.out.println();
        t4.teamPassInfo();
        System.out.println();
        t4.teamInfo();
//        for(int i = 0; i < animals.length; i++){
//            animals[i].run(500);
//            if(!animals[i].onDistance) continue;
//            animals[i].jump(2);
//            if(!animals[i].onDistance) continue;
//            animals[i].swim(5);
//            if(!animals[i].onDistance) continue;
//            animals[i].run(800);
//        }
//        System.out.println("Результаты: ");
//        animals[0].printResult();
//        animals[1].printResult();
    }
}
