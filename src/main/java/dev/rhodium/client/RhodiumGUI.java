package dev.rhodium.client;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.ClickGUI;
import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.mc16.MinecraftGUI;
import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.ColorComponent;
import com.lukflug.panelstudio.settings.EnumComponent;
import com.lukflug.panelstudio.settings.KeybindComponent;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.NumberSetting;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.FixedDescription;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;

import dev.rhodium.Rhodium;
import dev.rhodium.backend.setting.Setting;
import dev.rhodium.backend.setting.type.ColorSetting;
import dev.rhodium.backend.setting.type.EnumSetting;
import dev.rhodium.client.module.Category;
import dev.rhodium.client.module.Module;
import dev.rhodium.client.module.render.ClickGUIModule;
import net.minecraft.client.MinecraftClient;

public class RhodiumGUI extends MinecraftGUI {
	private final Toggleable colorToggle;
	private final GUIInterface guiInterface;
	private final Theme theme;
	private final ClickGUI gui;
	private static final int WIDTH=100,HEIGHT=12;

	public RhodiumGUI() {
		colorToggle=new SimpleToggleable(false);
		guiInterface=new GUIInterface(true) {
			@Override
			protected String getResourcePrefix() {
				return "coolhack:gui/";
			}
			
			@SuppressWarnings("resource")
			@Override
			public void drawString(Point pos, String s, Color c) {
				if (matrixStack==null) return;
				end();
				MinecraftClient.getInstance().textRenderer.drawWithShadow(matrixStack,s,pos.x+2,pos.y+2,c.getRGB());
				begin();
			}
			
			@SuppressWarnings("resource")
			@Override
			public int getFontWidth(String s) {
				return MinecraftClient.getInstance().textRenderer.getWidth(s)+4;
			}

			@SuppressWarnings("resource")
			@Override
			public int getFontHeight() {
				return MinecraftClient.getInstance().textRenderer.fontHeight+4;
			}
		};
		theme=new GameSenseTheme(new SettingsColorScheme(ClickGUIModule.activeColor,ClickGUIModule.inactiveColor,ClickGUIModule.backgroundColor,ClickGUIModule.outlineColor,ClickGUIModule.fontColor,ClickGUIModule.opacity),HEIGHT,2,5);
		gui=new ClickGUI(guiInterface,new FixedDescription(new Point(0,0)));
		int x=10;
		for (Category category: Category.values()) {
			DraggableContainer panel=new DraggableContainer(category.toString(),null,theme.getPanelRenderer(),new SimpleToggleable(false),new SettingsAnimation(ClickGUIModule.animationSpeed),null,new Point(x,10),WIDTH);
			gui.addComponent(panel);
			for (Module module: Rhodium.INSTANCE.moduleManager.getModulesByCategory(category)) {
				CollapsibleContainer container=new CollapsibleContainer(module.getName(),null,theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation(ClickGUIModule.animationSpeed),module);
				panel.addComponent(container);
				for (Setting<?> setting: Rhodium.INSTANCE.settingManager.getSettingsForModule(module)) {
					if (setting instanceof Toggleable) container.addComponent(new BooleanComponent(setting.getName(),setting.getDescription(),theme.getComponentRenderer(),(Toggleable)setting));
					else if (setting instanceof NumberSetting) container.addComponent(new NumberComponent(setting.getName(),setting.getDescription(),theme.getComponentRenderer(),(NumberSetting)setting,((NumberSetting) setting).getMinimumValue(),((NumberSetting) setting).getMaximumValue()));
					else if (setting instanceof EnumSetting) container.addComponent(new EnumComponent(setting.getName(),setting.getDescription(),theme.getComponentRenderer(),(EnumSetting<?>)setting));
					else if (setting instanceof ColorSetting) container.addComponent(new ColorComponent(setting.getName(),setting.getDescription(),theme.getContainerRenderer(),new SettingsAnimation(ClickGUIModule.animationSpeed),theme.getComponentRenderer(),(ColorSetting)setting,((ColorSetting)setting).alphaEnabled,((ColorSetting)setting).rainbowEnabled,colorToggle));
				}
				container.addComponent(new KeybindComponent(theme.getComponentRenderer(),module));
			}
			x+=WIDTH+10;
		}
	}

	@Override
	protected ClickGUI getGUI() {
		return gui;
	}

	@Override
	protected GUIInterface getInterface() {
		return guiInterface;
	}

	@Override
	protected int getScrollSpeed() {
		return ClickGUIModule.scrollSpeed.getValue();
	}
}