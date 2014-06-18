package controllers;

import executors.Executor;
import model.Book;
import model.Params;
import views.ConsoleView;
import views.SimpleConsoleView;

import java.util.Scanner;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class FrontController
{
    private static final FrontController instance = new FrontController();

    public static FrontController getInstance()
    {
        return instance;
    }

    private FrontController()
    {
       this.ap = new AppContext();
    }

    public void init(ConsoleView view, Executor executor)
    {
        this.view = view;
        this.executor = executor;
    }

    public void process()
    {
        String cmd_line = null;

        Scanner in = new Scanner(System.in);
        exit       = false;

        getExecutor().init(ap);

        do
        {
            cmd_line = getView().getNextCommandLine();

            getExecutor().execute(cmd_line);




        } while (!exit);
    }

    public ConsoleView getView()
    {
        return view;
    }

    public Book getModel()
    {
        return model;
    }

    public Executor getExecutor()
    {
        return executor;
    }

    private ConsoleView view;
    private Book        model;
    private Executor    executor;
    private boolean     exit;
    private ApplicationContext ap;

    private class AppContext implements ApplicationContext
    {
        @Override
        public void exit() {
            FrontController.this.exit = true;
        }
    }

}
