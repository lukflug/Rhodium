package dev.rhodium.backend.setting.type;

import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.Type;
import dev.rhodium.client.module.Module;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;

/**
 * @author Hoosiers 12/07/2020
 */

public class KeybindSetting extends Setting<Integer> /*implements com.lukflug.panelstudio.settings.KeybindSetting*/ {
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
        return this.defaultKey;
    }

	/*@Override
	public String getKeyName() {
		return getKeyName(key);
	}*/
	
	public static String getKeyName (int key) {
		String translationKey=InputUtil.Type.KEYSYM.createFromCode(key).getTranslationKey();
		String translation=new TranslatableText(translationKey).getString();
		if (!translation.equals(translationKey)) return translation;
		return InputUtil.Type.KEYSYM.createFromCode(key).getLocalizedText().getString();
	}
}
