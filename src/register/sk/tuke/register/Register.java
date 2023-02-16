package register.sk.tuke.register;

import java.util.List;

public interface Register {
    public int getCount();
    public Person getPerson(int index);
    public void addPerson(Person person) throws RegisterException;
    public List<Person> findPersonByName(String name);
    public List<Person> findPersonByPhoneNumber(String phoneNumber);
    public void removePerson(Person person);

}
