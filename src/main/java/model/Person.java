package model;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by user on 27.05.14.
 */
/*
 some changes for commit and merge
*/
/* 
add new comment in branch dev
*/
public class Person
{
    public Person(String name)
    {
        this.name = name;
        this.phones = new HashSet<>();
    }

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
            //return name.equals(((Person)obj).getName());
		return StringUtils.equals(((Person)obj).getName(), this.name);

        return false;
    }


    private String name;
    private Set<Phone> phones;
    private String lastname;

}
