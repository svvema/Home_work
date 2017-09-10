import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        play();
    }
    public static int getNumber(String mess, int min, int max) {
        int n;
        do {
            System.out.println(mess);
            n = sc.nextInt();
        } while (n > max || n < min);
        return n;
    }

    public static int getRandom(int min, int max) {
        int x = (int) (Math.random() * max + min);
        return x;
    }

    public static void play() {
        int r,x,n;
        do {
            x = getRandom(0, 9);
            System.out.println(x);
            int count = 3;
            do {
                n = getNumber("Введите число от 0 до 9", 0, 9);
                if (n != x) count--;
                if (n > x && count != 0) {
                    System.out.println("Загаданное число меньше. Осталось попыток: " + count);
                } else if (n < x && count != 0) {
                    System.out.println("Загаданное число больше. Осталось попыток: " + count);
                } else if (n == x) {
                    if (count==3) System.out.println("Вы выиграли с первой попытки!");
                    else if (count==1 || count==0 || count==2)System.out.println("Вы выиграли за " + (4 - count) + " попытки!");
                    break;
                } else System.out.println("Вы проиграли! Загаданное число: " + x);
            } while (count != 0);
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            r = sc.nextInt();
        }while (r==1);
        System.out.println("Удачи!");
    }
}
