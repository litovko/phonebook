package commands.builders;

import com.google.inject.Inject;
import commands.Command;
import commands.CommandList;
import model.Params;
import services.StorageService;

/**
* User: rgordeev
* Date: 25.06.14
* Time: 17:22
*/
public class CommandListBuilder extends AbstractCommandBuilder
{
    @Inject
    public CommandListBuilder(StorageService storageService) {
        super(storageService);

    }

    @Override
    public Command createCommand(Params params)
    {
        return new CommandList(getStorage());
    }

}
