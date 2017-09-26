import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    String[] list = {"A","B","C"};
        System.out.println(Arrays.toString(list));
    change(list,2,0);
        System.out.println(Arrays.toString(list));

    Integer[] list1 = {1,2,3};
        System.out.println(Arrays.toString(list1));
    change(list1,1,0);
        System.out.println(Arrays.toString(list1));

    ArrayList arrayList = transf(list);
        System.out.println(arrayList);
    ArrayList arrayList1 = transf(list1);
        System.out.println(arrayList1);


        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());


        System.out.println(appleBox.getBoxWeight());
        System.out.println(orangeBox.getBoxWeight());
        System.out.println(appleBox.compare(orangeBox));
        System.out.println(orangeBox.compare(appleBox));
    Box<Apple> appleBox1 = new Box<>();
    appleBox1.addFruit(new Apple());
        System.out.println("V1 " + appleBox.getVault());
        System.out.println("V2 " + appleBox1.getVault());
        moveFruits(appleBox,appleBox1);
        System.out.println(appleBox.getBoxWeight());
        System.out.println(appleBox1.getBoxWeight());
        System.out.println("V1 " + appleBox.getVault());
        System.out.println("V2 " + appleBox1.getVault());
        appleBox1.moveFruits(appleBox);
        System.out.println(appleBox.getBoxWeight());
        System.out.println(appleBox1.getBoxWeight());
        System.out.println("V1 " + appleBox.getVault());
        System.out.println("V2 " + appleBox1.getVault());
    }





    public static <T> void  change(T[] arr, int n1, int n2){
        if (arr.length>1 && n1<arr.length && n1>=0 && n2<arr.length && n2>=0){
    T buff = arr[n1];
    arr[n1] = arr[n2];
    arr[n2] = buff;}
    }

    public static <T>ArrayList transf(T[] arr){
        return new ArrayList<T>(Arrays.asList(arr));
    }

    public static <T extends Fruit>Box moveFruits(Box<T> from, Box<T> to){
        if  (from.equals(to))return from;
        else {
        for (int i = 0; i < from.getVault().size() ; i++) {
            to.getVault().add(from.getVault().get(i));
        }
        from.getVault().clear();}
        return to;
    }

}
