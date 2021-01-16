package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class EnumSetting<T extends Enum<T>> extends Setting<T> implements com.lukflug.panelstudio.settings.EnumSetting {
    public EnumSetting(T value, String name, String description, Module module) {
        super(value, name, description, module, Type.ENUM);
    }

	@Override
	public String getValueName() {
		return getValue().toString();
	}

	@Override
	public void increment() {
		T[] array=getValue().getDeclaringClass().getEnumConstants();
		int index=getValue().ordinal()+1;
		if (index>=array.length) index=0;
		setValue(array[index]);
	}
}