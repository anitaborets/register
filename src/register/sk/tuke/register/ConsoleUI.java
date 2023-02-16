package register.sk.tuke.register;

import java.util.Scanner;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /**
     * register.Register of persons.
     */
    private Register register;

    /**
     * scanner
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    };

    public ConsoleUI(Register register) {
        this.register = register;
    }

    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    printRegister();
                    break;
                case ADD:
                    addToRegister();
                    break;
                case UPDATE:
                    updateRegister();
                    break;
                case REMOVE:
                    removeFromRegister();
                    break;
                case FIND:
                    findInRegister();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private Option showMenu() {
        System.out.println("Menu.");
        for (var option : Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");

        var selection = -1;
        do {
            System.out.println("Option: ");
            selection = Integer.parseInt(readLine());
        } while (selection <= 0 || selection > Option.values().length);

        return Option.values()[selection - 1];
    }

    //TODO: Implement the method printRegister
    private void printRegister() {
        throw new UnsupportedOperationException("Method printRegister not yet implemented");
    }

    private void addToRegister() {
        System.out.println("Enter Name: ");
        var name = readLine();
        System.out.println("Enter Phone Number: ");
        var phoneNumber = readLine();

        register.addPerson(new Person(name, phoneNumber));
    }

    //TODO: Implement the method updateRegister
    private void updateRegister() {
        throw new UnsupportedOperationException("Method updateRegister not yet implemented");
    }

    //TODO: Implement the method findInRegister
    private void findInRegister() {
        throw new UnsupportedOperationException("Method findInRegister not yet implemented");
    }

    private void removeFromRegister() {
        System.out.println("Enter index: ");
        var index = Integer.parseInt(readLine());
        var person = register.getPerson(index - 1);
        register.removePerson(person);
    }
}
