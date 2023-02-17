package register.sk.tuke.register;

public class Main {
    public static void main(String[] args) throws Exception {
        var register = new ArrayRegister(20);
        var registerList = new ListRegister();
        initRegister(register);
        initRegister(registerList);

        var ui = new ConsoleUI(register);
        ui.run();
    }

    private static void initRegister(Register register) throws RegisterException {
        register.addPerson(new Person("Janko Hrasko", "0900 123 456"));
        register.addPerson(new Person("Janko Mrkvicka", "0900 321 456"));
        register.addPerson(new Person("Adam Novak", "0900 321 323"));
    }
}
