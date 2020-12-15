package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class KeybindSetting extends Setting<Integer> {
    private int key;
    private final int defaultKey;

    public KeybindSetting(int key, String name, String description, Module module) {
        super(key, name, description, module, Type.KEYBIND);

        this.key = key;
        this.defaultKey = key;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getDefaultKey() {
        return this.key;
    }
}
