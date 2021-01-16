package dev.rhodium.backend.setting.type;

import com.lukflug.panelstudio.settings.Toggleable;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class BooleanSetting extends Setting<Boolean> implements Toggleable {
    public BooleanSetting(String name, String description, Module module, boolean value) {
        super(value, name, description, module, Type.BOOLEAN);
    }

	@Override
	public boolean isOn() {
		return getValue();
	}

	@Override
	public void toggle() {
		setValue(!getValue());
	}
}
