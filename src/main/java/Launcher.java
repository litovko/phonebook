import commands.SupportedCommands;
import controllers.FrontController;
import controllers.SimpleCommandLineController;
import executors.Executor;
import views.SimpleConsoleView;

class Launcher
{
    public static void main(String[] args)
    {

        FrontController fc = FrontController.getInstance();
        fc.init(
                SimpleConsoleView.getInstance(),
                new Executor(SimpleCommandLineController.getInstance(), new SupportedCommands())
        );

        fc.process();

    }
}
