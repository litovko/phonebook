package services;

import com.google.inject.Inject;
import model.Book;
import model.Person;
import model.Phone;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 25
 */
public class HibernateStorageService implements StorageService
{
    @Inject
    public HibernateStorageService(EntityManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void add(String personName, String phone, Book book)
    {
        Person person = new Person(personName);
        Phone  ph     = new Phone(person, phone);
        person.getPhones().add(ph);

        book.getPersons().add(person);

        manager.getTransaction().begin();
        manager.persist(ph);
        manager.persist(person);
        manager.persist(book);

        manager.getTransaction().commit();

    }

    @Override
    public List<Person> list(Book book) {
        return manager.createQuery("select p from model.Person p").getResultList();
    }

    private EntityManager manager;
}
