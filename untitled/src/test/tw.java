package test;

/**
 * Created by svvem on 28.04.2016.
 */
class Solution
{

    public static void main(String[] args) {
        System.out.println(convertToSeconds(1));
        System.out.println(convertToSeconds(3));
    }
    public static int convertToSeconds(int hour){
        int sec=hour*3600;
        return sec;
    }
    //напишите тут ваш код
}
