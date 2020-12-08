package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class EnumSetting extends Setting {

    private Enum value;
    private final Enum defaultValue;

    public EnumSetting(String name, String description, Module module, Enum value) {
        super(Type.ENUM, name, description, module);

        this.value = value;
        this.defaultValue = value;
    }

    public Enum getValue() {
        return this.value;
    }

    public void setValue(Enum value) {
        this.value = value;
    }

    public Enum getDefaultValue() {
        return this.defaultValue;
    }
}