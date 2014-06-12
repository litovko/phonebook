package model;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class Params
{
    private String commandName;
    private String commandArgs;

    public Params(String commandName, String commandArgs)
    {
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }


    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s", getCommandName(), getCommandArgs());
    }
}
