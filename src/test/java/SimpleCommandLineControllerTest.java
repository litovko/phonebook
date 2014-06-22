import controllers.CommandLineController;
import controllers.SimpleCommandLineController;
import model.Params;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class SimpleCommandLineControllerTest
{
    @Test
    public void testForNull()
    {
        CommandLineController c = SimpleCommandLineController.getInstance();
        Params p = c.parseCommandLineString(null);

        assertEquals("", p.getCommandName());
        assertEquals("", p.getCommandArgs());
    }

    @Test
    public void testForEmpty()
    {
        CommandLineController c = SimpleCommandLineController.getInstance();
        Params p = c.parseCommandLineString("");

        assertEquals("", p.getCommandName());
        assertEquals("", p.getCommandArgs());
    }

    @Test
    public void testForSingleCommand()
    {
        CommandLineController c = SimpleCommandLineController.getInstance();
        Params p = c.parseCommandLineString("command");

        assertEquals("command", p.getCommandName());
        assertEquals("", p.getCommandArgs());

        p = c.parseCommandLineString("   command   ");

        assertEquals("command", p.getCommandName());
        assertEquals("", p.getCommandArgs());
    }

    @Test
    public void testForCommandAndSingleArg()
    {
        CommandLineController c = SimpleCommandLineController.getInstance();
        Params p = c.parseCommandLineString("command arg");

        assertEquals("command", p.getCommandName());
        assertEquals("arg", p.getCommandArgs());

        p = c.parseCommandLineString("    command     arg     ");

        assertEquals("command", p.getCommandName());
        assertEquals("arg", p.getCommandArgs());
    }

    @Test
    public void testForCommandAndMultiArgs()
    {
        CommandLineController c = SimpleCommandLineController.getInstance();
        Params p = c.parseCommandLineString("command arg1 agr2 agr3");

        assertEquals("command", p.getCommandName());
        assertEquals("arg1 agr2 agr3", p.getCommandArgs());

        p = c.parseCommandLineString("    command    arg1    agr2    agr3");

        assertEquals("command", p.getCommandName());
        assertEquals("arg1 agr2 agr3", p.getCommandArgs());
    }
}
