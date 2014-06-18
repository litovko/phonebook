package commands;

import controllers.ApplicationContext;
import model.Book;
import model.Params;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class ExitCommand implements Command, CommandBuilder {

    public static final String NAME = "exit";

    @Override
    public void execute(Book model, ApplicationContext ap) {
        ap.exit();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Command createCommand(Params params) {
        return new ExitCommand();
    }
}
