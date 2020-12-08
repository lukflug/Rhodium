package dev.rhodium.client.module;

import dev.rhodium.Rhodium;
import dev.rhodium.backend.setting.type.*;

import java.awt.*;

/**
 * @author Hoosiers 12/06/2020
 */

public abstract class Module {

    private final String name;
    private final Category category;
    private Color color;

    private boolean enabled;
    private boolean drawn;
    private int bind;

    public Module(String name, Category category, Color color) {
        this.name = name;
        this.category = category;
        this.color = color;

        enabled = false;
        drawn = true;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled = true) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    public boolean isDrawn() {
        return this.drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public int getBind() {
        return this.bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public void toggle() {
       setEnabled(!isEnabled());
    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }

    public void onUpdate() {

    }

    public void onGuiRender() {

    }

    public void onWorldRender() {

    }

    /** Setting registry functions below! */

    protected BooleanSetting registerBoolean(String name, String description, boolean value) {
        BooleanSetting setting = new BooleanSetting(name, description, this, value);
        Rhodium.INSTANCE.settingManager.addSetting(setting);
        return setting;
    }

    protected DoubleSetting registerDouble(String name, String description, double value, double min, double max, boolean isLimited) {
        DoubleSetting setting = new DoubleSetting(name, description, this, value, min, max, isLimited);
        Rhodium.INSTANCE.settingManager.addSetting(setting);
        return setting;
    }

    protected EnumSetting registerEnum(String name, String description, Enum value) {
        EnumSetting setting = new EnumSetting(name, description, this, value);
        Rhodium.INSTANCE.settingManager.addSetting(setting);
        return setting;
    }

    protected IntegerSetting registerInteger(String name, String description, int value, int min, int max, boolean isLimited) {
        IntegerSetting setting = new IntegerSetting(name, description, this, value, min, max, isLimited);
        Rhodium.INSTANCE.settingManager.addSetting(setting);
        return setting;
    }

    protected KeybindSetting registerKeybind(String name, String description, int value) {
        KeybindSetting setting = new KeybindSetting(name, description, this, value);
        return setting;
    }
}