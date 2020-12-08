package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class IntegerSetting extends Setting {

    private int value;
    private final int defaultValue;
    private final int min;
    private final int max;
    private final boolean isLimited;

    public IntegerSetting(String name, String description, Module module, int value, int min, int max, boolean isLimited) {
        super(Type.INTEGER, name, description, module);

        this.value = value;
        this.defaultValue = value;
        this.min = min;
        this.max = max;
        this.isLimited = isLimited;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public int getDefaultValue() {
        return this.defaultValue;
    }

    public boolean isLimited() {
        return this.isLimited;
    }
}