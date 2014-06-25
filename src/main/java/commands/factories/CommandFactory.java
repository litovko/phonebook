package commands.factories;

import commands.Command;
import model.Params;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public interface CommandFactory
{
    Command createCommand(Params params);

    Command createUnknownCommand(Params params);
}
