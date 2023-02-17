package register.sk.tuke.register;

import java.util.Scanner;

import static java.util.Objects.isNull;

public class ConsoleUI {

    private final Register arrayRegister;
    private final Scanner scanner = new Scanner(System.in);

    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    }

    public ConsoleUI(Register arrayRegister) {
        this.arrayRegister = arrayRegister;
    }

    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT -> printRegister();
                case ADD -> addToRegister();
                case UPDATE -> updateRegister();
                case REMOVE -> removeFromRegister();
                case FIND -> findInRegister();
                case EXIT -> {
                    return;
                }
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
            try {
                selection = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "This option is not exist");
                return Option.EXIT;
            }
        } while (selection <= 0 || selection > Option.values().length);

        return Option.values()[selection - 1];
    }

    private void printRegister() {
        arrayRegister.sort();
        for (int i = 0; i < arrayRegister.getCount(); i++) {
            if (!isNull(arrayRegister.getPerson(i)))
                System.out.println((i + 1) + "." + arrayRegister.getPerson(i));
        }
    }

    private void addToRegister() {
        System.out.println("Enter Name: ");
        var name = readLine();
        System.out.println("Enter Phone Number starting from 0, example \"0905526632\": ");
        var phoneNumber = readLine();

        try {
            Person person = new Person(name, phoneNumber);
            System.out.println("You want to add: " + person);
            arrayRegister.addPerson(person);
            System.out.println("Person was added successfully");

        } catch (RegisterException e) {
            System.out.println("Exception:" + e);
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "Try again");
            return;
        }

    }
    private void updateRegister() {
        System.out.println("Enter index of person: ");
        var index = Integer.parseInt(readLine());
        var person = arrayRegister.getPerson(index - 1);
        System.out.println("View: " + person);

        System.out.println("Enter new Name: ");
        var name = readLine();
        if (!isNull(name) && !name.isEmpty()) {
            person.setName(name);
            System.out.println("Name was changed");
        }
        System.out.println("Enter new Phone Number starting from 0, example \"0905526632\" ");
        var phoneNumber = readLine();
        if (!isNull(phoneNumber) && !phoneNumber.isEmpty()) {
            try {
                person.setPhoneNumber(phoneNumber);
                System.out.println("Phone number was changed");
            } catch (IllegalArgumentException e) {
                System.out.println("Phone number is not valid, try again");
                e.printStackTrace();
            }
        }
        System.out.println("Changes were successfully: " + person);
    }

    private void findInRegister() {
        System.out.println("Enter name or phoneNumber: ");
        String isPhoneNumber = "[-+]?\\d+";
        var input = readLine();

        if (isNull(input) || input.isEmpty()) {
            return;
        }

        if (input.matches(isPhoneNumber)) {
            if (!isNull(arrayRegister.findPersonByPhoneNumber(input))) {
                arrayRegister.findPersonByPhoneNumber(input).forEach(System.out::println);
            } else {
                System.out.println("Person not found");
            }
        } else {
            if (!isNull(arrayRegister.findPersonByName(input))) {
                arrayRegister.findPersonByName(input).forEach(System.out::println);
            } else {
                System.out.println("Person not found");
            }
        }
    }

    private void removeFromRegister() {
        System.out.println("Enter index of person: ");
        var index = Integer.parseInt(readLine());
        if (index <= 0 || index > arrayRegister.getCount()) {
            System.out.println("Index is not exists");
            return;
        }
        var person = arrayRegister.getPerson(index - 1);

        System.out.println("This person will be remove from register: " + person + " Please confirm Y/N");
        var input = readLine();
        if (input.equals("Y")) {
            arrayRegister.removePerson(person);
        }
    }
}
