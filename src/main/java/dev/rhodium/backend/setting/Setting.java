package dev.rhodium.backend.setting;

import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public abstract class Setting<T> {
    private T value;
    private final T defaultValue;
    private final String name;
    private final String configName;
    private final String description;
    private final Module module;
    private final Type type;

    public Setting(T value, String name, String description, Module module, Type type) {
        this.value = value;
        this.defaultValue = value;
        this.name = name;
        this.configName = name.replace(" ", "");
        this.description = description;
        this.module = module;
        this.type = type;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getDefaultValue() {
        return this.defaultValue;
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

    public Type getType() {
        return type;
    }
}