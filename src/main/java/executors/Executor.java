package executors;

import commands.Command;
import commands.CommandFactory;
import controllers.CommandLineController;
import controllers.SimpleCommandLineController;
import model.Book;
import model.Params;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class Executor
{
    public Executor(Book model, CommandLineController controller, CommandFactory commandFactory)
    {
        this.model = model;
        this.controller = controller;
        this.commandFactory = commandFactory;
    }



    public Executor(CommandLineController controller, CommandFactory commandFactory)
    {
        this(new Book(), controller, commandFactory);
    }


    public void execute(String commandLine)
    {
        Params params = getController().parseCommandLineString(commandLine);
        Command command = getCommandFactory().createCommand(params);

        command.execute(getModel());

        //System.out.println(params.toString());
    }

    public Book getModel()
    {
        return model;
    }

    public CommandLineController getController()
    {
        return controller;
    }

    public CommandFactory getCommandFactory()
    {
        return commandFactory;
    }

    private Book model;
    private CommandLineController controller;
    private CommandFactory commandFactory;
}
