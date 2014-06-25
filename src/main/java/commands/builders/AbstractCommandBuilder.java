package commands.builders;

import services.StorageService;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 25
 */
public abstract class AbstractCommandBuilder implements CommandBuilder
{
    private StorageService storage;

    public AbstractCommandBuilder(StorageService storageService)
    {
        this.storage = storageService;
    }

    public StorageService getStorage() {
        return storage;
    }
}
