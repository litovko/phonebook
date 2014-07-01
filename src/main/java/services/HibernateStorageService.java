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
    public Book getBook() {
       if (book != null) return book;


        //List<Book> books = manager.createQuery("select b from model.Book").setMaxResults(1).getResultList();
        List<Book> books= manager.createQuery("select b from model.Book b where b.id=1").getResultList();
        if (books.isEmpty()) {
            this.book = new Book();
            manager.persist(this.book);

        }
        else this.book=books.get(0);
        return this.book;
    }

    @Override
    public void add(String personName, String phone)
    {

        Person person = new Person(personName);
        Phone  ph     = new Phone(person, phone);
        person.getPhones().add(ph);
        Book b =getBook();
        this.book.getPersons().add(person);

        manager.getTransaction().begin();
        manager.persist(ph);
        manager.persist(person);


        manager.getTransaction().commit();

    }

    @Override
    public List<Person> list() {
        return manager.createQuery("select p from model.Person p").getResultList();
    }

    @Override
    public void close() {
        manager.getEntityManagerFactory().close();
    }

    private EntityManager manager;
    private Book book;
}
