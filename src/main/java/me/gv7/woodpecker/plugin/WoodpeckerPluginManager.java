package me.gv7.woodpecker.plugin;

import me.gv7.woodpekcer.vuldb.Log4jRCEPlugin;

public class WoodpeckerPluginManager implements IPluginManager {
    public void registerPluginManagerCallbacks(IPluginManagerCallbacks pluginManagerCallbacks) {
        pluginManagerCallbacks.registerVulPlugin(new Log4jRCEPlugin());
    }
}
