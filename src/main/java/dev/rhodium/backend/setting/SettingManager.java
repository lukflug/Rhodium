package dev.rhodium.backend.setting;

import dev.rhodium.Rhodium;
import dev.rhodium.client.module.Module;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Hoosiers 12/07/2020
 */

public class SettingManager {

    public ArrayList<Setting> settings;

    public SettingManager() {
        this.settings = new ArrayList<>();

        Rhodium.LOGGER.info("Loaded SettingManager!");
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsForModule(Module module) {
        ArrayList<Setting> settings = new ArrayList<>();
        settings.addAll(getSettings().stream().filter(setting -> setting.getModule() == module).collect(Collectors.toList()));

        return settings;
    }

    public void addSetting(Setting setting) {
        if (!getSettings().contains(setting)) {
            this.settings.add(setting);
        }
    }

    public void removeSetting(Setting setting) {
        if (getSettings().contains(setting)) {
            this.settings.remove(setting);
        }
    }

    public Setting getSettingByName(String name) {
        return getSettings().stream().filter(setting -> setting.getName() == name).findFirst().orElse(null);
    }

    public Setting getSettingByConfigName(String configName) {
        return getSettings().stream().filter(setting -> setting.getConfigName() == configName).findFirst().orElse(null);
    }
}