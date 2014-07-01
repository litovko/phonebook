package services;

import model.Book;
import model.Person;

import java.util.List;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public interface StorageService
{
    void add(String personName, String phone);

    List<Person> list();
    void close();
    Book getBook();
}
