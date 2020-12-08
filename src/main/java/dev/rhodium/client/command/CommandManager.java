package dev.rhodium.client.command;

import dev.rhodium.Rhodium;

import java.util.ArrayList;

/**
 * @author Hoosiers 12/08/2020
 */

public class CommandManager {

    public ArrayList<Command> commands;

    public CommandManager() {
        commands = new ArrayList<>();

        Rhodium.LOGGER.info("Loaded CommandManager!");
    }
}