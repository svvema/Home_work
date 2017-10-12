import java.util.Arrays;

public class Calc {
    public static void main(String[] args) {
        // int[] ar = {1,2,4,4,2,3,4,1,7};
        int[] ar = {4, 3, 1, 4};
        //   int[] ar = {1,2,3,1,2,3,6,1,7};
        //    System.out.println(Arrays.toString(arrWork1(ar)));
        //  System.out.println(Arrays.toString(ar).contains("5"));
        System.out.println(arrWork2(ar));
    }

    public static int[] arrWork1(int[] arr) {
        int[] arrOut;
        if (Arrays.toString(arr).contains("4")) ;
        else throw new RuntimeException("4 not found");

        int pos = 0;
        for (int i = 0; i < arr.length; i++) if (arr[i] == 4) pos = i;
        arrOut = new int[arr.length - pos - 1];
        int j = 0;
        for (int i = pos + 1; i < arr.length; i++, j++) arrOut[j] = arr[i];

        return arrOut;
    }

    public static boolean arrWork2(int[] arr) {
        boolean b1 = false;
        boolean b4 = false;
        for(int i : arr){
            if (i == 1)
            b1 = true;
            else if (i == 4)
                b4 = true;
            else return false;
        }return b1&&b4;
    }

}
