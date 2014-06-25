package commands;

import services.StorageService;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 25
 */
public abstract class AbstractCommandBuilder implements CommandBuilder
{
    protected StorageService service;

    public AbstractCommandBuilder(StorageService storageService)
    {
        this.service = storageService;
    }

    public StorageService getService() {
        return service;
    }
}
