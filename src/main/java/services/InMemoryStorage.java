package services;

import controllers.CommandLineController;
import controllers.SimpleCommandLineController;
import model.Book;
import model.Params;
import model.Person;
import model.Phone;

import java.util.*;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class InMemoryStorage implements StorageService
{
    @Override
    public void add(String personName, String phone, Book book)
    {
        Person person = new Person(personName);

        person.getPhones().add(new Phone(person, phone));
        book.getPersons().add(person);
    }

    @Override
    public List<Person> list(Book book)
    {
        List<Person> copy = new ArrayList<>(book.getPersons());
        Collections.sort(copy, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return copy;
    }
    public void del (String personName, Book book)
    {
        Set<Person> sp = book.getPersons();
        for (Person p : sp)
            if (personName.equals(p.getName())) {
                sp.remove(p);
                System.out.println("Person:"+ personName+" deleted!");
                return;
            }
        System.out.println("Does not found person:"+ personName+"!");

    }
    @Override
    public void update(String personName, String phone, Book book)
    {

        Set<Person> sp = book.getPersons();

        for (Person p : sp)
            if (personName.equals(p.getName())) {
                p.getPhones().add(new Phone(p,phone));
                System.out.println("Person:"+ personName+" updated by phone:"+phone);
                return;
            }
        System.out.println("Does not found person:"+ personName+"!");
    }
}
