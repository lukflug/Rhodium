package dev.rhodium;

import dev.rhodium.backend.setting.SettingManager;
import dev.rhodium.client.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    @Override
    public void onInitialize() {
        LOGGER.info("Starting up " + modName + " " + modVersion + "!");

        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
    }
}