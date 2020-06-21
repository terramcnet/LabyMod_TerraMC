package net.terramc.addon.modules;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;

public class NickModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Nick";
    }

    @Override
    public String getControlName() {
        return "Nick";
    }

    @Override
    public String getDisplayValue() {
        return TerraMCnetServer.getNickName();
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.NAME_TAG);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Nick";
    }

    @Override
    public String getDescription() {
        return "Zeigt dir deinen aktuellen Nick auf TerraMC.net an";
    }

    @Override
    public ModuleCategory getCategory() {
        return Main.TERRAMCNET_CATEGORY;
    }

    @Override
    public int getSortingId() {
        return 3;
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.displayNickName & TerraMCnetServer.getNickName() != null;
    }
}
