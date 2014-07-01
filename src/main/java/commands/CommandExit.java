package commands;

import controllers.ApplicationContext;
import services.StorageService;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class CommandExit extends AbstractCommand
{

    public static final String NAME = "exit";

    public CommandExit(StorageService storage)
    {
        super(storage);
    }

    @Override
    public void execute(ApplicationContext ap) {
        ap.exit(); getStorage().close();
    }

    @Override
    public String getName() {
        return NAME;
    }

}
