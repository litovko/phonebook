package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 27.05.14.
 */
@Entity
public class Person
{
    public Person() {
    }

    public Person(String name)
    {
        this.name = name;
        this.phones = new HashSet<>();
    }

    public Person(String name, Set<Phone> phones) {
        this.name = name;
        this.phones = phones;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "person")
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

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    private Long id;
    private String name;
    private Set<Phone> phones;
    //private String lastname;

}
