package commands.builders;

import com.google.inject.Inject;
import commands.Command;
import commands.CommandAdd;
import commands.UnknownCommand;
import commands.factories.ConsoleCommandsFactory;
import model.Params;
import org.apache.commons.lang3.StringUtils;
import services.StorageService;

/**
* User: rgordeev
* Date: 25.06.14
* Time: 17:21
*/
public class CommandAddBuilder extends AbstractCommandBuilder
{

    @Inject
    public CommandAddBuilder(StorageService storageService)
    {
        super(storageService);
    }

    @Override
    public Command createCommand(Params params)
    {
        String[] args = null;

        if (StringUtils.isNotEmpty(params.getCommandArgs()))
            args = StringUtils.split(params.getCommandArgs());

        if (args == null || args.length != 2)
            return ConsoleCommandsFactory.getInstance().createUnknownCommand(params);

        Command add = new CommandAdd(args[0], args[1], getStorage());

        return add;

    }
}
