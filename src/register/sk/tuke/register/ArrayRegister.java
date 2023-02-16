package register.sk.tuke.register;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
    /**
     * register.Person array.
     */
    private Person[] persons;

    /**
     * Number of persons in this register.
     */
    private int count;

    /**
     * Constructor creates an empty register with maximum size specified.
     *
     * @param size maximum size of the register
     */
    public ArrayRegister(int size) {
        persons = new Person[size];
        count = 0;
    }

    /**
     * Returns the number of persons in this register.
     *
     * @return the number of persons in this register
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the maximum number of persons in this register.
     *
     * @return the maximum number of persons in this register.
     */
    public int getSize() {
        return persons.length;
    }

    /**
     * Returns the person at the specified position in this register.
     *
     * @param index index of the person to return
     * @return person the person at the specified position in this register
     */
    public Person getPerson(int index) {
        return persons[index];
    }

    /**
     * Appends the specified person to the end of this register.
     *
     * @param person person to append to this register
     */
    public void addPerson(Person person) throws RegisterException {

        if (!isExist(person)) {
            persons[count] = person;
            count++;
        } else {
            throw new RegisterException();
        }
        sortArray();
    }

    /**
     * Returns the person with specified name in this register or <code>null</code>,
     * if match can not be found.
     *
     * @param name name of a person to search for
     * @return person with specified phone number
     */
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


    /**
     * Returns the person with specified phone number in this register or <code>null</code>,
     * if match can not be found.
     *
     * @param phoneNumber phone number of a person to search for
     * @return person with specified phone number
     */
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

    /**
     * Removes the specified person from the register.
     *
     * @param person person to remove
     */
    public void removePerson(Person person) {
        if (!isNull(person)) {
            List<Person> list = new ArrayList((Arrays.asList(persons)));
            list.remove(person);
            persons = list.toArray(new Person[list.size()]);
        }
        sortArray();

    }

    private boolean isExist(Person person) {
        for (Person value : persons) {
            if (!isNull(value)) {
                return value.getName().equals(person.getName()) || value.getPhoneNumber().equals(person.getPhoneNumber());
            }
        }
        return false;
    }
    private void sortArray() {
        List<Person> sortedPerson = Arrays.asList(persons);
        List<Person> sortedPerson2 = sortedPerson.stream().sorted(Comparator.nullsLast(Comparator.comparing(Person::getName))).toList();
        persons = sortedPerson2.toArray(new Person[sortedPerson.size()]);
    }
}
