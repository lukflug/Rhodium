package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class IntegerSetting extends Setting<Integer> {
    private final int min;
    private final int max;
    private final boolean isLimited;

    public IntegerSetting(int value, String name, String description, Module module, int min, int max, boolean isLimited) {
        super(value, name, description, module, Type.INTEGER);

        this.min = min;
        this.max = max;
        this.isLimited = isLimited;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public boolean isLimited() {
        return this.isLimited;
    }
}