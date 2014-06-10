package commands;

import model.Params;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public interface CommandBuilder
{
    Command createCommand(Params params);
}
