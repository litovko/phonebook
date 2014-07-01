package executors;

import commands.Command;
import commands.factories.CommandFactory;
import controllers.ApplicationContext;
import controllers.CommandLineController;
import model.Params;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class Executor
{
    public Executor(CommandLineController controller, CommandFactory commandFactory)
    {
        //this.model = model;
        this.controller = controller;
        this.commandFactory = commandFactory;
    }



//    public Executor(CommandLineController controller, CommandFactory commandFactory)
//    {
//        this(controller, commandFactory);
//    }


    public void execute(String commandLine)
    {
        Params params = getController().parseCommandLineString(commandLine);
        Command command = getCommandFactory().createCommand(params);

        command.execute(ap);

        //System.out.println(params.toString());
    }

    public void init(ApplicationContext ap)
    {
        this.ap = ap;
    }

//    public Book getModel()
//    {
//        return model;
//    }

    public CommandLineController getController()
    {
        return controller;
    }

    public CommandFactory getCommandFactory()
    {
        return commandFactory;
    }

    //private Book model;
    private CommandLineController controller;
    private CommandFactory commandFactory;
    private ApplicationContext ap;
}
