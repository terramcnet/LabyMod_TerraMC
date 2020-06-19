package net.terramc.addon;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

public class Main extends LabyModAddon {

    //private static ModuleCategory TERRAMCNET_CATEGORY;

    @Override
    public void onEnable() {
        //ModuleCategoryRegistry.loadCategory(TERRAMCNET_CATEGORY = new ModuleCategory("TerraMCnet", true, new ControlElement.IconData("terramcnet/textures/logo.png")));
        this.getApi().registerServerSupport(this, new TerraMCnetServer());
        this.getApi().getEventManager().register(new ServerMessageListener());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }
}
