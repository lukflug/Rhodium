package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    public EnumSetting(T value, String name, String description, Module module) {
        super(value, name, description, module, Type.ENUM);
    }
}