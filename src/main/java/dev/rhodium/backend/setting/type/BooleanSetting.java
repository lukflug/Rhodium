package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class BooleanSetting extends Setting {

    private boolean value;
    private final boolean defaultValue;

    public BooleanSetting(String name, String description, Module module, boolean value) {
        super(Type.BOOLEAN, name, description, module);

        this.value = value;
        this.defaultValue = value;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return getValue();
    }

    public boolean getDefaultValue() {
        return this.defaultValue;
    }
}
