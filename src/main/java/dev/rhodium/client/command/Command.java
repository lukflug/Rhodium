package dev.rhodium.client.command;

import net.minecraft.client.MinecraftClient;

/**
 * @author Hoosiers 12/08/2020
 */

public abstract class Command {
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final String[] alias;
    private final String description;

    public Command(String name, String[] alias, String description) {
        this.name = name;
        this.alias = alias;
        this.description = description;
    }

    public final String getName() {
        return this.name;
    }

    public final String[] getAlias() {
        return this.alias;
    }

    public final String getDescription() {
        return this.description;
    }

    public abstract void onCommand(String[] args);
}