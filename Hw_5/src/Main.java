public class Main {
    public static void main(String[] args) {
Employee e1 = new Employee("Ivan Ivanich Ivanov","boss","s@s.ru","331351351",10000,42);
    //e1.info();
    Employee[] empArray = new Employee[5];
        empArray[0] = e1;
        empArray[1] = new Employee("Petr PE", "preBoss","pp@s.ru","2252352", 8000,41);
        empArray[2] = new Employee("Oleg KE", "worker","ok@s.ru","613152", 2000,31);
        empArray[3] = new Employee("Kate VS", "co-worker","kv@s.ru","81735", 1000,22);
        empArray[4] = new Employee("Ann YE", "cleaner","ay@s.ru","0797614", 5,61);
        for (int i = 0; i < empArray.length; i++) {
            if (empArray[i].age>=40) {empArray[i].info();
                System.out.println();
            }
        }
    }
}
