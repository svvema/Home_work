import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //////////////////////////////////////////
        //1.
        int[] ar = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("1. Array = " + Arrays.toString(ar));
        for (int i=0; i<ar.length; i++){
            if (ar[i]==0)ar[i]=1;
            else ar[i]=0;
        }
        System.out.println("Result = " +Arrays.toString(ar));
        System.out.println();
        //////////////////////////////////////////
        //2.
        int[] ar2 = new int[8];
        for (int i=1; i<ar2.length; i++){
        ar2[i]=ar2[i-1]+3;
        }
        System.out.println("2. Result = " +Arrays.toString(ar2));
        System.out.println();
        //////////////////////////////////////////
        //3.
        int[] ar3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("3. Array = " + Arrays.toString(ar3));
        for (int i=0; i<ar3.length; i++){
            if (ar3[i]<6)ar3[i]=ar3[i]*2;
        }
        System.out.println("Result = " +Arrays.toString(ar3));
        System.out.println();
        //////////////////////////////////////////
        //4.
        System.out.println("4.");

        int[][] ar4 = new int [13][13];

        for (int i=0; i<ar4.length;i++){
            for (int j=0; j<ar4[0].length; j++){
                if (i==j || j==ar4[0].length-i-1)ar4[i][j]=1;
                System.out.print(ar4[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
        //////////////////////////////////////////
        //5.
        int[] ar5 = { 1, 5, 3, 2, 11, -4, 5, 2, 4, 8, 9, 1,3,0};
        int max, min;
        max = min = ar5[0];

        for (int e:ar5){
        if (e>=max)max=e;
        if (e<=min)min=e;
        }
        System.out.println("5. Array = " + Arrays.toString(ar5));
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
    }
}
