package dev.rhodium.client.module.render;

import java.awt.Color;

import dev.rhodium.backend.setting.type.ColorSetting;
import dev.rhodium.backend.setting.type.IntegerSetting;
import dev.rhodium.client.module.Category;
import dev.rhodium.client.module.Module;

public class ClickGUIModule extends Module {
	public static ColorSetting activeColor;
	public static ColorSetting inactiveColor;
	public static ColorSetting backgroundColor;
	public static ColorSetting outlineColor;
	public static ColorSetting fontColor;
	public static IntegerSetting opacity;
	public static IntegerSetting animationSpeed;
	public static IntegerSetting scrollSpeed;
	
	public ClickGUIModule() {
		super("ClickGUI",Category.RENDER,Color.BLUE);
		activeColor=registerColor("Active Color","The main color.",true,false,Color.RED,false);
		inactiveColor=registerColor("Inactive Color","The color for inactive modules.",true,false,Color.BLACK,false);
		backgroundColor=registerColor("Background Color","The background color for settings.",true,false,new Color(30,30,30),false);
		outlineColor=registerColor("Outline Color","The color for panel outlines.",true,false,Color.RED,false);
		fontColor=registerColor("Font Color","The main text color.",true,false,Color.WHITE,false);
		opacity=registerInteger("Opacity","The GUI opacity",150,0,255,true);
		animationSpeed=registerInteger("Animation Speed","The speed for GUI animations.",200,0,1000,true);
		scrollSpeed=registerInteger("Scroll Speed","The speed for GUI scrolling",10,1,20,true);
	}
}
