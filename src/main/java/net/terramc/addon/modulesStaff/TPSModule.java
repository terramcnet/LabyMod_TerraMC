package net.terramc.addon.modulesStaff;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.StaffSettings;

public class TPSModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Server-TPS";
    }

    @Override
    public String getControlName() {
        return "Server-TPS";
    }

    @Override
    public String getDisplayValue() {
        return TerraMCnetServer.getServerTps();
    }

    @Override
    public String getDefaultValue() {
        return "0.0";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.REDSTONE_TORCH_ON);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Server-TPS";
    }

    @Override
    public String getDescription() {
        return "Zeigt die aktuelle TPS des Servers an";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return Main.TERRAMCNET_STAFF_CATEGORY;
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.isAdmin() & StaffSettings.showTPS;
    }

}
