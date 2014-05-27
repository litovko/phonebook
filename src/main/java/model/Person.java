package model;

import java.util.Set;

/**
 * Created by user on 27.05.14.
 */
public class Person
{
    public Person(String name, Set<Phone> phones) {
        this.name = name;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        if (obj instanceof Person)
            return name.equals(((Person)obj).getName());

        return false;
    }

    private String name;
    private Set<Phone> phones;

}
