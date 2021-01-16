package dev.rhodium.client.module;

import java.util.ArrayList;
import java.util.stream.Collectors;

import dev.rhodium.Rhodium;
import dev.rhodium.client.module.render.ClickGUIModule;

/**
 * @author Hoosiers 12/06/2020
 */

public class ModuleManager {
    public ArrayList<Module> modules;

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public ArrayList<Module> getModulesByCategory(Category category) {
        return modules.stream().filter(module -> module.getCategory() == category).collect(Collectors.toCollection(ArrayList::new));
    }

    public void addModule(Module module) {
        if (!modules.contains(module)) {
            this.modules.add(module);
        }
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean isModuleEnabled(Module module) {
        return module.isEnabled();
    }

    public void onUpdate() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }

    //todo: onGuiRender and onWorldRender...need to set up a basic clickgui and events first

    public void onGuiRender() {
        for (Module module : modules) {
            module.onGuiRender();
        }
    }

    public void onWorldRender() {
        for (Module module : modules) {
            module.onWorldRender();
        }
    }

    public ModuleManager() {
        modules = new ArrayList<>();
        modules.add(new ClickGUIModule());
        Rhodium.LOGGER.info("Loaded ModuleManager!");
    }
}