package register.sk.tuke.register;

public class Main {

    public static void main(String[] args) throws Exception {
        var register = new Register(20);

        register.addPerson(new Person("Janko Hrasko", "0900123456"));

        var ui = new ConsoleUI(register);
        ui.run();
    }
}
