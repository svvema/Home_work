import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
static private HashMap<Integer,String> phoneBook = new HashMap<>();

    public PhoneBook() {
    }
    public void add(int number, String fam){
        phoneBook.put(number, fam);
    }
    public void get(String fam){
        System.out.println("Ищем по имени: " + fam);
        Set<HashMap.Entry<Integer,String>> set = phoneBook.entrySet();
        for (Map.Entry<Integer,String> map:set) {
            if (map.getValue() == fam)
                System.out.println("Number: "+map.getKey() + " Name: " + map.getValue());
        }
    }
}
