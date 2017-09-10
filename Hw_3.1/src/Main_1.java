import java.util.Random;
import java.util.Scanner;

public class Main_1 {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    game();
    }
    public static int getNumber(String mess, int min, int max){
        int x;
        do {
            System.out.println(mess);
            x = sc.nextInt();
        }while (x < min || x > max);
        return x;
    }
    public static int random(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }
    public static void  game() {
        do {
            int g = random(10);
            System.out.println("Загаданное число: " + g);
            int count = 3;
            do {
                int n = getNumber("Введите число в пределах от 0 до 9", 0, 9);
                count--;
                if (g == n && count >=0) {
                    System.out.print("Вы угадали");
                    if (count == 2) System.out.println(" c первой попытки!");
                    if (count == 1) System.out.println(" c двух попыток!");
                    if (count == 0) System.out.println(" c трех попыток!");
                    break;
                }
                if (count == 0)System.out.println("Вы проиграли!");

                if (g > n && count != 0) System.out.println("Число больше! Осталось попыток: " + count);
                if (g < n && count != 0) System.out.println("Число меньше! Осталось попыток: " + count);
            } while (count != 0);

        } while (getNumber("Повторить игру еще раз? 1 – да / 0 – нет", 0, 1) == 1);
        System.out.println("Удачи!");
    }
}
