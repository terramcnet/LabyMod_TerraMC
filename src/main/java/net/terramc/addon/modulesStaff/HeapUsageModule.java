package net.terramc.addon.modulesStaff;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.StaffSettings;

public class HeapUsageModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "RAM";
    }

    @Override
    public String getControlName() {
        return "RAM Auslastung";
    }

    @Override
    public String getDisplayValue() {
        return TerraMCnetServer.getHeapUsage();
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
        return "RAM Auslastung";
    }

    @Override
    public String getDescription() {
        return "Zeigt die aktuelle RAM Auslastung des Servers an";
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
        return Main.enabled & Main.isAdmin() & StaffSettings.showHeapUsage;
    }

}
