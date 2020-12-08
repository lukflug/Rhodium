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
    private Enum[] values;

    public EnumSetting(String name, String description, Module module, Enum value, Enum[] values) {
        super(Type.ENUM, name, description, module);

        this.value = value;
        this.defaultValue = value;
        this.values = values;
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

    public Enum[] getValues() {
        return this.values;
    }

    public void setValues(Enum[] values) {
        this.values = values;
    }
}