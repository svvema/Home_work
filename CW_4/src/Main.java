import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static char[][] map;
    public static final int size = 5;
    public static final int dots_to_win = 3;
    public static final char dot_Empty = '*';
    public static final char dot_X = 'X';
    public static final char dot_O = 'O';
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        switch (choose()){
            case 0:
                initMap(size);
                while (true){
                    p_2_Turn();
                    printMap();
                    if (winCheck(dot_O)){
                        System.out.println("Победил компьютер!");
                        break;
                    }
                    if (isFull()) {System.out.println("Ничья");
                        break;}
                    p_1_Turn();
                    printMap();
                    if (winCheck(dot_X)){
                        System.out.println("Победил игрок!");
                        break;
                    }
                    if (isFull()) {System.out.println("Ничья");
                        break;}
                }
                return;
            case 1:
                initMap(size);
                while (true){
                    printMap();
                    p_1_Turn();
                    if (winCheck(dot_X)){
                        System.out.println("Победил игрок!");
                        break;
                    }
                    if (isFull()) {System.out.println("Ничья");
                        break;}

                    p_2_Turn();
                    printMap();
                    if (winCheck(dot_O)){
                        System.out.println("Победил компьютер!");
                        break;
                    }
                    if (isFull()) {System.out.println("Ничья");
                        break;}
                }
        }

    }
    public static boolean winCheck(char symb){
        for (int i = 0; i < size; i++) {
            int countI=0;
            int countJ=0;
            for (int j = 0; j < size; j++) {

                if (map[i][j]==symb){
                    int fi =i;
                    int fj = j;
                    while (map[fi][fj] == symb) {
                            countJ++;
                            fj++;
                            if (countJ == dots_to_win) return true;
                            if (fj==size)break;
                    }
                        fi = i;
                        fj = j;
                    while (map[fi][fj] == symb) {
                        countI++;
                        fi++;
                        if (countI == dots_to_win) return true;
                        if (fi==size)break;
                    }
                    countI=0;
                    fi = i;
                    fj = j;
                    while (map[fi][fj] == symb){
                        countI++;
                        fi++;
                        fj++;
                        if (countI == dots_to_win)return true;
                        if (fi==size || fj == size)break;
                    }
                    countI=0;

                    fi = i;
                    fj = j;
                    while (map[size-fi-1][fj] == symb){
                        countI++;
                        fi++;
                        fj++;
                        if (countI == dots_to_win)return true;
                        if (fi==size || fj == size)break;
                    }
                    countI=0;
                    countJ=0;

                }
            }

        }

        return false;
    }
    public static void prewinCheck(char symb, char opsymb){
        boolean win = false;
        for (int i = 0; i < size; i++) {
            int countJ=0;
            for (int j = 0; j < size; j++)
            {
                if (map[i][j]==symb ){
                    int fi =i;
                    int fj = j;
                    while (map[fi][fj] == symb) {
                            //if (map[fi][fj]==symb)
                            countJ++;
                            fj++;
                        System.out.println(fj);
                    if (fj<size && countJ == dots_to_win - 1 && map[fi][fj] != opsymb && !win) {map[fi][fj] = symb;win =true;}
                        System.out.println(fj+ " "+ countJ);
                    if (fj<=size && countJ == dots_to_win -1 && map[fi][fj-1] == opsymb && map[fi][fj-dots_to_win]!=opsymb  && !win)
                        {map[fi][fj-dots_to_win]=symb;
                            win =true;}
                    if (fj<=size && fj-dots_to_win>=0 && countJ == dots_to_win && map[fi][fj-dots_to_win]!=opsymb && !win)
                            {map[fi][fj-dots_to_win]=symb;
                               win =true;}
                        if (fj==size)break;
                    }
                }
            }
        }


        for (int j = 0; j < size; j++) {
            int countI=0;
            for (int i = 0; i < size; i++) {


                if (map[i][j]==symb ){
                    int fi =i;
                    int fj =j;
                    while (map[fi][fj] == symb) {
                            countI++;
                        fi++;
                        //System.out.println(fi);
                        if (fi<size && countI == dots_to_win - 1 && map[fi][fj] != opsymb && !win) {map[fi][fj] = symb;win =true;}
                        if (fi<=size && countI == dots_to_win - 1 && fi-dots_to_win>=0  && map[fi-dots_to_win][fj]!=opsymb && !win)
                            {map[fi-dots_to_win][fj]=symb;
                           win = true;}
                        if (fi<=size && fi-dots_to_win>=0 && countI == dots_to_win && map[fi-dots_to_win][fj]!=opsymb && !win)
                        {map[fi-dots_to_win][fj]=symb;
                            win =true;}
                        if (fi==size)break;
                    }
                }
            }
        }
        boolean diag = false;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                int countIJ=0;
                if (map[i][j]==symb ){
                    int fi =i;
                    int fj = j;
                    while (map[fi][fj] == symb) {
                            countIJ++;
                            fi++;
                            fj++;
                        if (fj<=size-1&&fi<=size-1) {
                            if (countIJ == dots_to_win - 1 && map[fi][fj] != opsymb){
                                map[fi][fj] = symb; diag =true;}
                            else if (fj < size && fi < size && fj - dots_to_win >= 0 && fi - dots_to_win >= 0) {
                                if (countIJ == dots_to_win - 1 && map[fi - dots_to_win][fj - dots_to_win] != opsymb)
                                    map[fi - dots_to_win][fj - dots_to_win] = symb; diag = true;
                            }
                        }
                        if (fi==size)break;
                        if (fj==size)break;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int countIJ=0;

                if (map[i][j]==symb ){
                    int fi =i;
                    int fj = j;
                    while (map[fi][fj] == symb) {
                            countIJ++;
                            fj++;
                        if (fi>0)
                            fi--;
                        System.out.println("fi "+fi+" "+"fj "+fj);
                      if (fj<=size-1&&fi<=size-1) {
                            if (countIJ == dots_to_win - 1 && map[fi][fj] != opsymb && !diag){
                              map[fi][fj] = symb;}
                            else if (fj<size &&fi<size && fj-dots_to_win>=0 && fi-dots_to_win>=0) {
                                if (countIJ == dots_to_win - 1 && map[fi - dots_to_win][fj + dots_to_win] != opsymb)
                                    map[fi - dots_to_win][fj + dots_to_win] = symb;
                            }
                        }
                        if (fi==size)break;
                        if (fj==size)break;
                    }
                }
            }
        }
        if (dots_to_win==3) {
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size - 1; j++) {
                    if (map[i][j - 1] == symb && map[i][j + 1] == symb && map[i][j] != opsymb && !win) {
                        map[i][j] = symb;
                        win = true;
                    }
                }
            }
            for (int j = 0; j < size; j++) {
                for (int i = 1; i < size - 1; i++) {
                    if (map[i - 1][j] == symb && map[i + 1][j] == symb && map[i][j] != opsymb && !win) {
                        map[i][j] = symb;
                        win = true;
                    }
                }
            }
            for (int i = 1; i < size - 1; i++) {
                for (int j = 1; j < size - 1; j++) {
                    if (map[i - 1][j - 1] == symb && map[i + 1][j + 1] == symb && map[i][j] != opsymb && !win) {
                        map[i][j] = symb;
                        win = true;
                    }
                }
            }
            for (int i = 1; i < size - 1; i++) {
                for (int j = 1; j < size - 1; j++) {
                    if (map[i - 1][j + 1] == symb && map[i + 1][j - 1] == symb && map[i][j] != opsymb && !win) {
                        map[i][j] = symb;
                        win = true;
                    }
                }
            }
        }

    }
    public static void p_1_Turn(){
        int x,y;
        do {
            System.out.println("Введите координаты X Y");
            x = sc.nextInt()-1;
            y = sc.nextInt()-1;
        }while (!validCell(x,y));
        System.out.println("Вы сделали ход в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = dot_X;
    }
    public static void p_2_Turn() {
        int x, y;        int[] a= new int[2];

        if (size % 2 != 0 && validCell(size / 2, size / 2)) {
            x = size / 2;
            y = size / 2;
            System.out.println("Компьютер сделал ход в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = dot_O;

        } else {

            prewinCheck(dot_O,dot_X);
            if (winCheck(dot_O))return;

            do {
                System.out.println("Ход ИИ. Введите координаты X Y");
                printMap();
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!validCell(x, y));
            System.out.println("Компьютер сделал ход в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = dot_O;
        }
    }
    public static boolean validCell(int x, int y){
        if (x>=size || x<0 || y>=size || y<0)return false;
        if (map[y][x]==dot_Empty)return true;
        return false;
    }

    public static void initMap(int size){
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = dot_Empty;
            }
        }
    }
    public static void printMap(){
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i+1)+ " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static boolean isFull(){

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j]==dot_Empty)return false;
            }
        }
        return true;
    }
    public static int choose(){
        int x;
        do {
            System.out.println("Ходите первыми? 1 - да, 0 - нет");
             x = sc.nextInt();
        }while (x<0 || x>1);
        if (x==1)return 1;
        return 0;
    }

}
