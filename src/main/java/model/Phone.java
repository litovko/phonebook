package model;

import java.util.Set;

/**
 * Created by user on 27.05.14.
 */
public class Phone
{
    public Phone(Person person, String phone) {
        this.person = person;
        this.phone = phone;
    }

    public Person getPerson() {
        return person;
    }

    public String getPhone() {
        return phone;
    }

    private Person person;
    private String phone;
}
