package dev.rhodium.backend.setting.type;

import java.awt.Color;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;

public class ColorSetting extends Setting<Color> /*implements com.lukflug.panelstudio.settings.ColorSetting*/ {
	public final boolean rainbowEnabled,alphaEnabled;
	private boolean rainbow;
	
	public ColorSetting(Color value, boolean rainbow, String name, boolean rainbowEnabled, boolean alphaEnabled, String description, Module module) {
		super(value, name, description, module, Type.COLOR);
		this.rainbow=rainbow;
		this.rainbowEnabled=rainbowEnabled;
		this.alphaEnabled=alphaEnabled;
	}
	
	@Override
	public Color getValue() {
		if (rainbow) return Color.getHSBColor((System.currentTimeMillis()%(360*32))/(360f * 32),1,1);
		return super.getValue();
	}

	//@Override
	public Color getColor() {
		return super.getValue();
	}

	//@Override
	public boolean getRainbow() {
		return rainbow;
	}

	//@Override
	public void setRainbow(boolean value) {
		rainbow=value;
	}
}
