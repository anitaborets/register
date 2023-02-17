package register.sk.tuke.register;

import java.util.*;

public class ListRegister implements Register {
    private final List<Person> persons = new ArrayList<>();

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public int getSize() {
        return persons.size();
    }

    @Override
    public Person getPerson(int index) {
        return persons.get(index);
    }

    @Override
    public void addPerson(Person person) throws RegisterException {
        if (!persons.contains(person)) {
            persons.add(person);
        } else {
            throw new RegisterException();
        }
    }

    @Override
    public List<Person> findPersonByName(String name) {
        String nameForFind = name.toLowerCase().replace(" ", "");
        List<Person> result = persons.stream().filter(p -> p.getName().toLowerCase(Locale.ROOT).replace(" ", "").contains(nameForFind))
                .toList();
        return result;
    }

    @Override
    public List<Person> findPersonByPhoneNumber(String phoneNumber) {
        List<Person> result = persons.stream().filter(p -> p.getPhoneNumber()
                        .equals(phoneNumber.replaceFirst("0", "+421").replace(" ", "")))
                .toList();
        return result;
    }

    @Override
    public void removePerson(Person person) {
        persons.remove(person);
    }

    @Override
    public void sort() {
        persons.sort(Comparator.comparing((Person::getName)));
    }
}
