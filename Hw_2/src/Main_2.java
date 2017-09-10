import java.util.Arrays;

public class Main_2 {
    public static void main(String[] args) {
    int [] row = {1, 1, 1, 2, 1};
        System.out.println("6. Array = " + Arrays.toString(row));
        System.out.println("Result: " + checkBalance(row));
    int [] row2 = {2, 1, 1, 2, 1};
        System.out.println("\nArray = " + Arrays.toString(row2));
        System.out.println("Result: " + checkBalance(row2));
    int [] row3 = {10, 10};
        System.out.println("\nArray = " + Arrays.toString(row3));
        System.out.println("Result: " + checkBalance(row3));
    int [] row4 = {1,2,3,4,10};
        System.out.println("\nArray = " + Arrays.toString(row4 ));
        System.out.println("Result: " + checkBalance(row4));

    int [] row5 = {1,2,3,4,5};
        System.out.println("\n7. Array = " + Arrays.toString(row5));
        carousel(row5,2);
        System.out.println("Result = " + Arrays.toString(row5));
    int [] row6 = {1,2,3,4,5};
    carousel(row6,-2);
        System.out.println("\nArray = " + Arrays.toString(row6));
        carousel(row6,-2);
        System.out.println("Result = " + Arrays.toString(row6));
    }
    //////////////////////////////////////////
    //6.
    public static boolean checkBalance(int []a){
        boolean b = false;
        int left, right;
        left = 0;
        for (int i=0; i<a.length;i++){
            right =0;
            left += a[i];
            for (int j=i+1; j<a.length;j++){
                 right += a[j];
            }
            if (left == right ){ b = true; break;}
        }
        return b;
    }
    //////////////////////////////////////////
    //7.
    public static void carousel(int []a, int n){
        int mem = 0;
        for (int i=0; i<n;i++){
            mem = a[a.length-1];
           for (int j=a.length-1; j>0;j--){
            a[j] = a[j-1];
           }
           a[0]=mem;
        }
        for (int i=0; i>n;i--){
            mem = a[0];
           for (int j=0; j<a.length-1;j++){
            a[j] = a[j+1];
           }
           a[a.length-1]=mem;
        }
    }
}
