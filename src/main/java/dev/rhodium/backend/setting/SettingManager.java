package dev.rhodium.backend.setting;

import dev.rhodium.Rhodium;
import dev.rhodium.client.module.Module;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Hoosiers 12/07/2020
 */

public class SettingManager {
    public ArrayList<Setting<?>> settings;

    public SettingManager() {
        this.settings = new ArrayList<>();

        Rhodium.LOGGER.info("Loaded SettingManager!");
    }

    public ArrayList<Setting<?>> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting<?>> getSettingsForModule(Module module) {
        return settings.stream().filter(it -> it.getModule().getClass().isAssignableFrom(module.getClass())).collect(Collectors.toCollection(ArrayList::new));
    }

    public void addSetting(Setting<?> setting) {
        if (!settings.contains(setting)) {
            this.settings.add(setting);
        }
    }

    public void removeSetting(Setting<?> setting) {
        this.settings.remove(setting);
    }

    public Setting<?> getSettingByName(String name) {
        return settings.stream().filter(setting -> setting.getName().equals(name)).findFirst().orElse(null);
    }

    public Setting<?> getSettingByConfigName(String configName) {
        return settings.stream().filter(setting -> setting.getConfigName().equals(configName)).findFirst().orElse(null);
    }

    public ArrayList<Setting<?>> getSettingByType(Type type) {
        return settings.stream().filter(setting -> setting.getType() == type).collect(Collectors.toCollection(ArrayList::new));
    }
}