package controllers;

import model.Params;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public interface CommandLineController
{
    Params parseCommandLineString(String commandLine);
}
