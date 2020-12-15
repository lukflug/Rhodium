package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class DoubleSetting extends Setting<Double> {
    private final double min;
    private final double max;
    private final boolean isLimited;

    public DoubleSetting(double value, String name, String description, Module module, double min, double max, boolean isLimited) {
        super(value, name, description, module, Type.DOUBLE);

        this.min = min;
        this.max = max;
        this.isLimited = isLimited;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public boolean isLimited() {
        return this.isLimited;
    }
}