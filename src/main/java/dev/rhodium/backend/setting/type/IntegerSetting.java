package dev.rhodium.backend.setting.type;

import com.lukflug.panelstudio.settings.NumberSetting;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

/**
 * @author Hoosiers 12/07/2020
 */

public class IntegerSetting extends Setting<Integer> implements NumberSetting {
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

	@Override
	public double getMaximumValue() {
		return max;
	}

	@Override
	public double getMinimumValue() {
		return min;
	}

	@Override
	public double getNumber() {
		return getValue();
	}

	@Override
	public int getPrecision() {
		return 0;
	}

	@Override
	public void setNumber(double value) {
		setValue((int)Math.round(value));
	}
}