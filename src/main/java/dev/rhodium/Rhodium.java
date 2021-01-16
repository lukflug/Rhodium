package dev.rhodium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import dev.rhodium.backend.setting.SettingManager;
import dev.rhodium.client.RhodiumGUI;
import dev.rhodium.client.command.CommandManager;
import dev.rhodium.client.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

/**
 * @author Hoosiers 12/06/2020
 */

public class Rhodium implements ModInitializer {
    public static String modName = "Rhodium";
    public static String modVersion = "v1.0.0";

    public static Rhodium INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger(modName);

    public Rhodium() {
        INSTANCE = this;
    }

    public ModuleManager moduleManager;
    public SettingManager settingManager;
    public CommandManager commandManager;
    public RhodiumGUI clickGui;

    @Override
    public void onInitialize() {
        LOGGER.info("Starting up " + modName + " " + modVersion + "!");

        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        clickGui = new RhodiumGUI();
        // TODO Temporary code to bind GUI, remove when event system is done
        KeyBinding keyBinding=KeyBindingHelper.registerKeyBinding(new KeyBinding("key.rhodium.gui",InputUtil.Type.KEYSYM,GLFW.GLFW_KEY_O,"categoy.rhodium.client"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
        	if (keyBinding.wasPressed()) MinecraftClient.getInstance().openScreen(clickGui);
        });
    }
}