package commands;

import com.google.inject.Inject;
import controllers.ApplicationContext;
import model.Book;
import model.Params;
import services.StorageService;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class ExitCommand extends AbstractCommand implements CommandBuilder {

    public static final String NAME = "exit";

    @Inject
    public ExitCommand(StorageService storage)
    {
        super(storage);
    }

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
        return new ExitCommand(this.storage);
    }

}
