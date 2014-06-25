package commands;

import services.StorageService;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 25
 */
public abstract class AbstractCommand implements Command, PersistedCommand
{
    public AbstractCommand(StorageService storage)
    {
        this.storage = storage;
    }

    @Override
    public StorageService getStorage()
    {
        return storage;
    }

    private StorageService storage;
}
