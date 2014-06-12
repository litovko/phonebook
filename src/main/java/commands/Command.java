package commands;

import model.Book;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public interface Command
{
    void execute(Book model);

    String getName();
}
