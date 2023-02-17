package register.sk.tuke.register;

import java.util.*;

import static java.util.Objects.isNull;

public class ArrayRegister implements Register {
    private Person[] persons;
    private int count;

    public ArrayRegister(int size) {
        persons = new Person[size];
        count = 0;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getSize() {
        return persons.length;
    }

    @Override
    public Person getPerson(int index) {
        return persons[index];
    }

    @Override
    public void addPerson(Person person) throws RegisterException {
        if (!isExist(person)) {
            persons[count] = person;
            count++;
        } else {
            throw new RegisterException();
        }
    }

    @Override
    public List<Person> findPersonByName(String name) {
        List<Person> result = new ArrayList<>();
        String nameForFind = name.toLowerCase().replace(" ", "");
        for (Person value : persons) {
            if (!isNull(value)) {
                String personName = value.getName().toLowerCase(Locale.ROOT).replace(" ", "");
                if (personName.contains(nameForFind)) {
                    result.add(value);
                }
            }
        }
        return result;
    }

    @Override
    public List<Person> findPersonByPhoneNumber(String phoneNumber) {
        List<Person> result = new ArrayList<>();
        String phoneNumberForFind = phoneNumber.replaceFirst("0", "+421").replace(" ", "");
        for (Person person : persons) {
            if (!isNull(person)) {
                if (person.getPhoneNumber().contains(phoneNumberForFind)) {
                    result.add(person);
                }
            }
        }
        return result;
    }

    @Override
    public void removePerson(Person person) {
        if (!isNull(person)) {
            List<Person> list = new ArrayList((Arrays.asList(persons)));
            list.remove(person);
            persons = list.toArray(new Person[list.size()]);
        }
    }

    @Override
    public void sort() {
        List<Person> sortedPerson = Arrays.asList(persons);
        List<Person> sortedPerson2 = sortedPerson.stream().sorted(Comparator.nullsLast(Comparator.comparing(Person::getName))).toList();
        persons = sortedPerson2.toArray(new Person[sortedPerson.size()]);
    }

    private boolean isExist(Person person) {
        String phoneNumberForFind = person.getPhoneNumber().replaceFirst("0", "+421").replace(" ", "");
        for (Person value : persons) {
            if (!isNull(value)) {
                return value.getName().equals(person.getName()) || (value.getPhoneNumber().equals(phoneNumberForFind));
            }
        }
        return false;
    }

}
