package commands.builders;

import com.google.inject.Inject;
import commands.Command;
import commands.CommandExit;
import model.Params;
import services.StorageService;

/**
 * User: rgordeev
 * Date: 25.06.14
 * Time: 17:23
 */
public class CommandExitBuilder extends AbstractCommandBuilder
{
    @Inject
    public CommandExitBuilder(StorageService storageService)
    {
        super(storageService);
    }

    @Override
    public Command createCommand(Params params)
    {
        return new CommandExit(getStorage());
    }
}
