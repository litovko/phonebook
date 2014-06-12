package commands;

import model.Params;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public abstract class CommandFacroryBase implements CommandFactory
{

    @Override
    public Command createCommand(Params params)
    {
        CommandBuilder builder = getBuilders().get(params.getCommandName());

        return (builder == null)?createUnknownCommand(params):builder.createCommand(params);
    }

    @Override
    public Command createUnknownCommand(Params params) {
        return UnknownCommand.getInstance();
    }

    public void register(String commandName, CommandBuilder builder)
    {
        getBuilders().put(commandName, builder);
    }

    public Iterator<String> enumerateBuilders()
    {
        return getBuilders().keySet().iterator();
    }

    public Map<String, CommandBuilder> getBuilders()
    {
        if (builders == null) builders = createBuilders();

        return builders;
    }

    protected Map<String, CommandBuilder> createBuilders()
    {
        return new HashMap<String, CommandBuilder>();
    }

    private Map<String, CommandBuilder> builders;
}
