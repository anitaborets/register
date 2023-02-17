package register.sk.tuke.register;

import java.util.Comparator;

public class Person implements Comparator<Person> {
    private String name;
    private String phoneNumber;

    public Person(String name, String phoneNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String nameNew) {
        if (!isValidName(nameNew)) {
            throw new IllegalArgumentException("Name is not valid");
        }
        name = nameNew;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumberNew) {
        if (!isValidPhoneNumber(phoneNumberNew)) {
            throw new IllegalArgumentException("Phone number is not valid");
        }
        phoneNumber = formattedPhoneNumber(phoneNumberNew);
    }

    public String toString() {
        return name + " (" + phoneNumber + ")";
    }

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        final String regex = "^[0]\\s?\\d{3}\\s?\\d{3}\\s?\\d{3}";
        return phoneNumber.matches(regex);
    }

    private String formattedPhoneNumber(String phoneNumberNew) {
        String formattedNumber = " ";
        if (!phoneNumberNew.isEmpty()) {
            formattedNumber = phoneNumberNew.replaceFirst("0", "+421").replace(" ", "");
        }
        return formattedNumber;
    }

    private boolean isValidName(String name) {
        final String regex = "\\w*\\s\\w*";
        return name.matches(regex);
    }
}


