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
    public Book getBook(Book book) {
        List<Book> b= manager.createQuery("select b from model.Book b where b.id=1").getResultList();
        if (b.isEmpty()) return null;
        return  b.get(0);
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
        if (getBook(book)==null) manager.persist(book);

        manager.getTransaction().commit();

    }

    @Override
    public List<Person> list(Book book) {
        return manager.createQuery("select p from model.Person p").getResultList();
    }

    @Override
    public void close() {
        manager.getEntityManagerFactory().close();
    }

    private EntityManager manager;
}
