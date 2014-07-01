package commands;

import controllers.ApplicationContext;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public interface Command
{
    void execute(ApplicationContext ap);

    String getName();
}
