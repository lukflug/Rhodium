package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class DoubleSetting extends Setting {

    private double value;
    private final double defaultValue;
    private final double min;
    private final double max;
    private final boolean isLimited;

    public DoubleSetting(String name, String description, Module module, double value, double min, double max, boolean isLimited) {
        super(Type.DOUBLE, name, description, module);

        this.value = value;
        this.defaultValue = value;
        this.min = min;
        this.max = max;
        this.isLimited = isLimited;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getDefaultValue() {
        return this.defaultValue;
    }

    public boolean isLimited() {
        return this.isLimited;
    }
}