package dev.rhodium.client.module;

import dev.rhodium.Rhodium;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Hoosiers 12/06/2020
 */

public class ModuleManager {

    public ArrayList<Module> modules;

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public ArrayList<Module> getModulesByCategory(Category category) {
        ArrayList<Module> modules = new ArrayList<>();
        modules.addAll(getModules().stream().filter(module -> module.getCategory() == category).collect(Collectors.toList()));

        return modules;
    }

    public void addModule(Module module) {
        if (!getModules().contains(module)) {
            this.modules.add(module);
        }
    }

    public void removeModule(Module module) {
        if (getModules().contains(module)) {
            this.modules.remove(module);
        }
    }

    public Module getModuleByName(String name) {
        return getModules().stream().filter(module -> module.getName() == name).findFirst().orElse(null);
    }

    public boolean isModuleEnabled(Module module) {
        return module.isEnabled();
    }

    public void onUpdate() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }

    //todo: onGuiRender and onWorldRender...need to set up a basic clickgui and events first

    public void onGuiRender() {

    }

    public void onWorldRender() {

    }

    public ModuleManager() {
        modules = new ArrayList<>();

        Rhodium.LOGGER.info("Loaded ModuleManager!");
    }
}