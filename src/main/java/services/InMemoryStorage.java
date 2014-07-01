package services;

import model.Book;
import model.Person;
import model.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class InMemoryStorage implements StorageService
{
    @Override
    public void add(String personName, String phone)
    {
        Person person = new Person(personName);

        person.getPhones().add(new Phone(person, phone));
        book.getPersons().add(person);
    }

    @Override
    public List<Person> list()
    {
        List<Person> copy = new ArrayList<Person>(book.getPersons());
        Collections.sort(copy, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return copy;
    }

    @Override
    public void close() {

    }

    @Override
    public Book getBook() {
        return book == null ? book = new Book() : book;

    }
    private Book book;
}
