public class Employee {
    public String fio;
    public String position;
    public String email;
    public String phoneNumber;
    public int salary;
    public int age;

    public Employee(String fio, String position, String email, String phoneNumber, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }
    public void info(){
        System.out.println("fio: "+ this.fio);
        System.out.println("position: "+ this.position);
        System.out.println("email: "+ this.email);
        System.out.println("phoneNumber: "+ this.phoneNumber);
        System.out.println("salary: "+ this.salary);
        System.out.println("age: "+ this.age);
    }
}
