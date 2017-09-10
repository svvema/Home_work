import java.util.Random;
import java.util.Scanner;

public class Main_2 {
    public static void main(String[] args) {
    String [] words =  {"apple", "orange", "lemon", "banana", "apricot", "avocado",
            "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak",
            "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
            "pepper", "pineapple", "pumpkin", "potato"};
    String guess = words[random(words.length)];
        System.out.println("Guessed word: "+guess);
        game(guess);
    }
    public static int random(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }
    public  static String read(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static void game(String g){
        String ans;
       do {
            ans = read();
        }while (!hint(g,ans));
        System.out.println("You Guessed! You win!");
    }
    public static boolean hint(String g, String s){
    String comp = "";
        if (g.length()!=15) {
            int wl = g.length();
                for (int i = 0; i < 15-wl; i++) {
                    g+="#";
                }
            }
            if (g.length() > s.length()) {
                int wordLength=    g.length() - s.length();
                for (int i = 0; i < wordLength+(15-g.length()); i++) {
                    s+="#";
                }
            }
        for (int i=0; i<g.length();i++){
            if (g.charAt(i) == s.charAt(i)) comp+=g.charAt(i);
            else comp+="#";
        }
        if (g.equals(s)) return true;
        else {
            System.out.println(comp);
            System.out.println("Did not guess! Try again!");
            return false;
        }
    }
}
