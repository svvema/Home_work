import java.util.Random;
import java.util.Scanner;

public class Game {
    public static char[][] map;
    public static final int SIZE =3;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = '+';
    public static final char DOT_0 = 'o';
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true){
            humanTurn();
            printMap();
            if (chechWin(DOT_X)){
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (chechWin(DOT_0)){
                System.out.println("Победил ИИ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }
    public static void humanTurn(){
       int x, y;
       do {
           System.out.println("Введите координаты в формате X Y");
           x = scanner.nextInt()-1;
           y = scanner.nextInt()-1;
       }while (!isCellValid(x,y));
        map[y][x] = DOT_X;
    }
    public static void aiTurn(){
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }while (!isCellValid(x,y));
        System.out.println("Компьютер сделал ход в точку " + (x+1) + " " + (y + 1));
        map[y][x] = DOT_0;
    }
    public static boolean isMapFull(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)return false;
            }
        }
    return true;
    }
    public static  boolean chechWin(char symb){
//        if (map[0][0] == symb && map[0][1] == symb && map [0][2] == symb)return true;
//        if (map[1][0] == symb && map[1][1] == symb && map [1][2] == symb)return true;
//        if (map[2][0] == symb && map[2][1] == symb && map [2][2] == symb)return true;
//
//        if (map[0][0] == symb && map[0][1] == symb && map [2][0] == symb)return true;
//        if (map[0][1] == symb && map[1][1] == symb && map [2][1] == symb)return true;
//        if (map[0][2] == symb && map[1][2] == symb && map [2][2] == symb)return true;
//
//        if (map[0][0] == symb && map[1][1] == symb && map [2][2] == symb)return true;
//        if (map[0][2] == symb && map[1][1] == symb && map [2][0] == symb)return true;
//        return false;
        for (int i = 0; i < SIZE; i++) {
            int j=0;
            int count = 0;
          while (map[i][j]==symb && j<SIZE){
               j++;
                count++;
              if (count==3){return true;}
               }
           }
        for (int j = 0; j < SIZE; j++) {
            int i=0;
            int count = 0;
          while (map[i][j]==symb && i<SIZE){
               i++;
                count++;
              if (count==3){return true;}
               }
           }
        int count=0;
        int recount=0;
        if (map[SIZE-1][0]==symb)recount++;
        if (map[0][SIZE-1]==symb)recount++;
        for (int i = 0; i < SIZE; i++) {
                if (map[i][i]==symb)count++;
                if (count==3)return true;
                if (recount==2 && count==1)return true;
        }

//        if (map[0][0] == symb && map[1][1] == symb && map [2][2] == symb)return true;
//        if (map[0][2] == symb && map[1][1] == symb && map [2][0] == symb)return true;
        return false;
    }

    public static boolean isCellValid(int x, int y){
        if (x<0 || x>= SIZE || y<0 || y>=SIZE)return false;
        if (map[y][x]==DOT_EMPTY)return true;
        return false;
    }
    public static void initMap(){
        map = new  char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap(){
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i+1)+ " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
