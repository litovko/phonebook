package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 27.05.14.
 */
@Entity
public class Book {

    public Book() {
        persons = new HashSet<>();
    }

    public Book(Set<Person> persons) {
        this.persons = persons;
    }

    @OneToMany
    public Set<Person> getPersons()
    {
        return persons;
    }

    /**
     * {@link http://www.objectdb.com/api/java/jpa/GenerationType}
     * @return идентификатор первичного ключа
     */
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

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    private Long id;
    private Set<Person> persons;
}
