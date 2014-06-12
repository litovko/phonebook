package commands;

import model.Book;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class UnknownCommand implements Command
{
    private static final Command instance = new UnknownCommand();

    public static Command getInstance()
    {
        return instance;
    }

    public static final String NAME = "unknown";

    @Override
    public void execute(Book model)
    {
        System.out.println("you've entered unknown command");
    }

    @Override
    public String getName()
    {
        return NAME;
    }
}
