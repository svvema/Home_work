import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] ar = {"A","B","C","AA","B","A","C","BB","DD","A","D","AA","C","AA","AA","A","J","A"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(ar));
        List<String> unique = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();


        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            String a = iterator.next();

            if (!map.containsKey(a))
            map.put(a,1);
            else map.replace(a,map.get(a)+1);
        }
        Set<HashMap.Entry<String,Integer>> set = map.entrySet();
        for (Map.Entry<String,Integer> maps : set){
            if (maps.getValue()==1)unique.add(maps.getKey());
        }
        System.out.println("Unique: " + unique);
        System.out.println("Count: " + map);

    PhoneBook phoneBook = new PhoneBook();

    phoneBook.add(154135,"sdfa");
    phoneBook.add(4315,"sdfa");
    phoneBook.add(15532135,"sdfa");
    phoneBook.add(1354,"ags");
    phoneBook.add(5367265,"ags");
    phoneBook.add(26135,"sdfa");
    phoneBook.add(26188035,"sdfa");
    phoneBook.add(2613512,"sdfa");
    phoneBook.add(13548070,"ags");

    phoneBook.get("ags");
    }
}
