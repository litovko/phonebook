package commands;

import services.StorageService;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 25
 */
public abstract class AbstractCommand implements Command
{
    protected AbstractCommand(StorageService storage)
    {
        this.storage = storage;
    }

    protected StorageService storage;
}
