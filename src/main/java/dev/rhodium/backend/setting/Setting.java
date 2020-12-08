package dev.rhodium.backend.setting;

import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public abstract class Setting {

    private final Type type;
    private final String name;
    private final String configName;
    private final String description;
    private final Module module;

    public Setting(Type type, String name, String description, Module module) {
        this.type = type;
        this.name = name;
        this.configName = name.replace(" ", "");
        this.description = description;
        this.module = module;
    }

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getConfigName() {
        return this.configName;
    }

    public String getDescription() {
        return this.description;
    }

    public Module getModule() {
        return this.module;
    }
}