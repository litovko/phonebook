package services;

import configs.DBConnection;
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
    public void add(String personName, String phone)
    {
        if(this.book==null){
            Book b=getBook();
        }
        TransactionScript.getInstance().addPerson(personName, phone);
    }

    @Override
    public List<Person> list() {
        if(this.book==null){
            Book b=getBook();
        }
        return TransactionScript.getInstance().listPersons(this.book.getId().toString());
    }

    @Override
    public void close() {
        TransactionScript.getInstance().close();
    }

    @Override
    public Book getBook() {
        return book == null ? book = new Book(TransactionScript.getInstance().getBook("1")) : book;


    }

    public static final class TransactionScript
    {
        private static final TransactionScript instance = new TransactionScript();

        public static TransactionScript getInstance() {
            return instance;
        }

        public TransactionScript()
        {
            String url      = DBConnection.JDBC.url();
            String login    = DBConnection.JDBC.username();
            String password = DBConnection.JDBC.password();

            try
            {
                connection = DriverManager.getConnection(url, login,
                        password);
            } catch (Exception e)
            {
                e.printStackTrace();
            };
        }
        public  Long getBook(String book_id)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(
                        "select id from book b \n" +
                                "where b.id = ?");

                statement.setInt(1, Integer.valueOf(book_id));

                ResultSet r_set = statement.executeQuery();
                if( r_set.next()){
                    return r_set.getLong("id");
                }


            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return newBook();
        }
        public Long newBook()
        {
            try
            {

                PreparedStatement addBook = connection.prepareStatement("insert into book (id) values (1)", Statement.RETURN_GENERATED_KEYS);

                addBook.execute();
                ResultSet auto_pk = addBook.getGeneratedKeys();
                while (auto_pk.next())
                {
                    return auto_pk.getLong("id");
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return 1l;
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
        public void close()
        {
            try
            {
                connection.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            };

        }

        private Connection connection;
       ;
    }
    private Book book;
}
