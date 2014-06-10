package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 27.05.14.
 */
public class Book {

    public Book() {
        persons = new HashSet<>();
    }

    public Book(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Person> getPersons()
    {
        return persons;
    }

    private Set<Person> persons;
}
