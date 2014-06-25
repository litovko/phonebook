package commands;

import services.StorageService;

/**
 * User: rgordeev
 * Date: 25.06.14
 * Time: 17:49
 */
public interface PersistedCommand
{
    StorageService getStorage();
}
