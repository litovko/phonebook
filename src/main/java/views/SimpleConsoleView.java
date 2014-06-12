package views;

import java.util.Scanner;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class SimpleConsoleView implements ConsoleView
{
    private static final ConsoleView instance = new SimpleConsoleView();

    public static ConsoleView getInstance()
    {
        return instance;
    }

    private SimpleConsoleView()
    {
        in = new Scanner(System.in);
    }

    @Override
    public String getNextCommandLine()
    {
        System.out.print(">> ");
        return in.nextLine();
    }

    private Scanner in;
}
