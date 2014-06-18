package services;

import model.Book;
import model.Person;
import model.Phone;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class JDBCStorageService implements StorageService
{
    @Override
    public void add(String personName, String phone, Book book)
    {
        TransactionScript.getInstance().addPerson(personName, phone);
    }

    @Override
    public List<Person> list(Book book) {
        return TransactionScript.getInstance().listPersons("1");
    }

    public static final class TransactionScript
    {
        private static final TransactionScript instance = new TransactionScript();

        public static TransactionScript getInstance() {
            return instance;
        }

        public TransactionScript()
        {
            String url      = "jdbc:postgresql://192.168.56.101/phonebook";
            String login    = "postgres";
            String password = "123456";

            try
            {
                connection = DriverManager.getConnection(url, login,
                        password);
            } catch (Exception e)
            {
                e.printStackTrace();
            };
        }

        public List<Person> listPersons(String book_id)
        {
            List<Person> result = new ArrayList<>(10);

            try
            {
                PreparedStatement statement = connection.prepareStatement(
                        "select name, phone from book b \n" +
                        "inner join person p on b.id = p.book_id \n" +
                        "inner join phone ph on p.id = ph.person_id\n" +
                        "where b.id = ?");

                statement.setInt(1, Integer.valueOf(book_id));

                ResultSet r_set = statement.executeQuery();

                while (r_set.next())
                {
                    Person p = new Person(r_set.getString("name"));
                    Phone ph = new Phone(p, r_set.getString("phone"));
                    p.getPhones().add(ph);
                    result.add(p);
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return result;
        }

        public void addPerson(String person, String phone)
        {
            try
            {
                PreparedStatement addPerson = connection.prepareStatement("insert into person (book_id, name) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                PreparedStatement addPhone  = connection.prepareStatement("insert into phone (person_id, phone) values (?, ?)", Statement.RETURN_GENERATED_KEYS);

                addPerson.setInt(1, 1);
                addPerson.setString(2, person);
                addPerson.execute();

                ResultSet auto_pk = addPerson.getGeneratedKeys();
                while (auto_pk.next())
                {
                    int id = auto_pk.getInt("id");
                    addPhone.setInt(1, id);
                    addPhone.setString(2, phone);
                    addPhone.execute();
                }



            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        private Connection connection;
    }
}
