package dev.rhodium.client;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.lwjgl.glfw.GLFW;

import com.lukflug.panelstudio.base.Animation;
import com.lukflug.panelstudio.base.Context;
import com.lukflug.panelstudio.base.IToggleable;
import com.lukflug.panelstudio.component.IScrollSize;
import com.lukflug.panelstudio.container.GUI;
import com.lukflug.panelstudio.layout.ChildUtil.ChildMode;
import com.lukflug.panelstudio.layout.IComponentAdder;
import com.lukflug.panelstudio.layout.ILayout;
import com.lukflug.panelstudio.layout.PanelAdder;
import com.lukflug.panelstudio.layout.PanelLayout;
import com.lukflug.panelstudio.mc16.MinecraftGUI;
import com.lukflug.panelstudio.popup.FixedPositioner;
import com.lukflug.panelstudio.popup.PanelPositioner;
import com.lukflug.panelstudio.popup.PopupTuple;
import com.lukflug.panelstudio.setting.IBooleanSetting;
import com.lukflug.panelstudio.setting.ICategory;
import com.lukflug.panelstudio.setting.IClient;
import com.lukflug.panelstudio.setting.IColorSetting;
import com.lukflug.panelstudio.setting.IEnumSetting;
import com.lukflug.panelstudio.setting.IModule;
import com.lukflug.panelstudio.setting.INumberSetting;
import com.lukflug.panelstudio.setting.ISetting;
import com.lukflug.panelstudio.setting.SettingsAnimation;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.IColorScheme;
import com.lukflug.panelstudio.theme.ITheme;

import dev.rhodium.Rhodium;
import dev.rhodium.backend.setting.type.BooleanSetting;
import dev.rhodium.backend.setting.type.ColorSetting;
import dev.rhodium.backend.setting.type.DoubleSetting;
import dev.rhodium.backend.setting.type.EnumSetting;
import dev.rhodium.backend.setting.type.IntegerSetting;
import dev.rhodium.client.module.Category;
import dev.rhodium.client.module.render.ClickGUIModule;
import net.minecraft.util.Formatting;

public class RhodiumGUI extends MinecraftGUI {
	private final GUIInterface guiInterface;
	private final ITheme theme;
	private final GUI gui;
	private static final int WIDTH=100;

	public RhodiumGUI() {
		guiInterface=new GUIInterface(true) {
			@Override
			protected String getResourcePrefix() {
				return "coolhack:gui/";
			}
		};
		theme=new GameSenseTheme(new IColorScheme() {
			@Override
			public void createSetting(ITheme theme, String name, String description, boolean hasAlpha, boolean allowsRainbow, Color color, boolean rainbow) {
				ClickGUIModule.instance.registerColor(name, description, allowsRainbow, hasAlpha, color, rainbow);
			}

			@Override
			public Color getColor(String name) {
				return ((ColorSetting)Rhodium.INSTANCE.settingManager.getSettingsForModule(ClickGUIModule.instance).stream().filter(setting->setting.getName().equals(name)).findFirst().orElse(null)).getValue();
			}
		},9,4,5,": "+Formatting.GRAY);
		
		IClient client=()->Arrays.stream(Category.values()).map(category->new ICategory() {
			@Override
			public String getDisplayName() {
				return category.toString();
			}

			@Override
			public Stream<IModule> getModules() {
				return Rhodium.INSTANCE.moduleManager.getModulesByCategory(category).stream().map(module->new IModule() {
					@Override
					public String getDisplayName() {
						return module.getName();
					}

					@Override
					public IToggleable isEnabled() {
						return new IToggleable() {
							@Override
							public boolean isOn() {
								return module.isEnabled();
							}

							@Override
							public void toggle() {
								module.toggle();
							}
						};
					}

					@Override
					public Stream<ISetting<?>> getSettings() {
						return Rhodium.INSTANCE.settingManager.getSettingsForModule(module).stream().map(setting->{
							if (setting instanceof BooleanSetting) return new IBooleanSetting() {
								@Override
								public String getDisplayName() {
									return setting.getName();
								}

								@Override
								public void toggle() {
									((BooleanSetting)setting).setValue(!((BooleanSetting)setting).getValue());
								}

								@Override
								public boolean isOn() {
									return ((BooleanSetting)setting).getValue();
								}
							};
							else if (setting instanceof EnumSetting) return new IEnumSetting() {
								@Override
								public String getDisplayName() {
									return setting.getName();
								}

								@Override
								public void increment() {
									((EnumSetting<?>)setting).increment();
								}

								@Override
								public String getValueName() {
									return ((EnumSetting<?>)setting).getValueName();
								}

								@Override
								public Stream<String> getAllowedValues() {
									return Arrays.stream(((EnumSetting<?>)setting).getValue().getClass().getEnumConstants()).map(value->value.toString());
								}
							};
							else if (setting instanceof IntegerSetting) return new INumberSetting() {
								@Override
								public String getDisplayName() {
									return setting.getName();
								}

								@Override
								public double getNumber() {
									return ((IntegerSetting)setting).getValue();
								}

								@Override
								public void setNumber(double value) {
									((IntegerSetting)setting).setValue((int)Math.round(value));
								}

								@Override
								public double getMaximumValue() {
									return ((IntegerSetting)setting).getMax();
								}

								@Override
								public double getMinimumValue() {
									return ((IntegerSetting)setting).getMin();
								}

								@Override
								public int getPrecision() {
									return 0;
								}
							};
							else if (setting instanceof DoubleSetting) return new INumberSetting() {

								@Override
								public String getDisplayName() {
									return setting.getName();
								}

								@Override
								public double getNumber() {
									return ((DoubleSetting)setting).getValue();
								}

								@Override
								public void setNumber(double value) {
									((DoubleSetting)setting).setValue(value);
								}

								@Override
								public double getMaximumValue() {
									return ((DoubleSetting)setting).getMax();
								}

								@Override
								public double getMinimumValue() {
									return ((DoubleSetting)setting).getMin();
								}

								@Override
								public int getPrecision() {
									return 2;
								}
							};
							else if (setting instanceof ColorSetting) return new IColorSetting() {
								@Override
								public String getDisplayName() {
									return setting.getName();
								}

								@Override
								public Color getValue() {
									return ((ColorSetting)setting).getValue();
								}

								@Override
								public void setValue(Color value) {
									((ColorSetting)setting).setValue(value);
								}

								@Override
								public Color getColor() {
									return ((ColorSetting)setting).getColor();
								}

								@Override
								public boolean getRainbow() {
									return ((ColorSetting)setting).getRainbow();
								}

								@Override
								public void setRainbow(boolean rainbow) {
									((ColorSetting)setting).setRainbow(rainbow);
								}
							};
							return new ISetting<Void>() {
								@Override
								public String getDisplayName() {
									return setting.getName();
								}

								@Override
								public Void getSettingState() {
									return null;
								}

								@Override
								public Class<Void> getSettingClass() {
									return Void.class;
								}
							};
						});
					}
				});
			}
		});

		// Define GUI object
		gui=new GUI(guiInterface,theme.getDescriptionRenderer(),new FixedPositioner(new Point(0,0)));
        BiFunction<Context,Integer,Integer> scrollHeight=(context,componentHeight)->{
        	return Math.min(componentHeight,Math.max(12*4,RhodiumGUI.this.height-context.getPos().y-12));
        };
        Supplier<Animation> animation=()->new SettingsAnimation(()->ClickGUIModule.animationSpeed.getValue(),()->guiInterface.getTime());
		PopupTuple popupType=new PopupTuple(new PanelPositioner(new Point(0,0)),false,new IScrollSize() {
			@Override
			public int getScrollHeight (Context context, int componentHeight) {
				return scrollHeight.apply(context,componentHeight);
			}
		});
        // Populate GUI
        IComponentAdder classicPanelAdder=new PanelAdder(gui,false,()->true,title->title) {
        	@Override
			public int getScrollHeight (Context context, int componentHeight) {
				return scrollHeight.apply(context,componentHeight);
			}
		};
		ILayout classicPanelLayout=new PanelLayout(WIDTH,new Point(10,10),WIDTH+10,0,animation,scancode->scancode==GLFW.GLFW_KEY_DELETE||scancode==GLFW.GLFW_KEY_BACKSPACE,level->ChildMode.DOWN,ChildMode.DOWN,popupType);
		classicPanelLayout.populateGUI(classicPanelAdder,client,theme);
	}

	@Override
	protected GUI getGUI() {
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