package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by user on 27.05.14.
 */
@Entity
public class Phone
{
    public Phone() {
    }

    public Phone(Person person, String phone) {
        this.person = person;
        this.phone = phone;
    }

    @ManyToOne
    public Person getPerson() {
        return person;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
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

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Long id;
    private Person person;
    private String phone;
}
