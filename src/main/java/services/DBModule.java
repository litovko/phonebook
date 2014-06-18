package services;

import com.google.inject.AbstractModule;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 18
 */
public class DBModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(StorageService.class).to(JDBCStorageService.class);
    }
}
