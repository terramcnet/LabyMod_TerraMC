package net.terramc.addon.modulesStaff;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.StaffSettings;


public class ReportsModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Offene Reports";
    }

    @Override
    public String getControlName() {
        return "Offene Reports";
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(TerraMCnetServer.getReports().size());
    }

    @Override
    public String getDefaultValue() {
        return "0";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.REDSTONE);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Offene Reports";
    }

    @Override
    public String getDescription() {
        return "Zeigt Offene Reports auf TerraMC.net an";
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.isStaff() & StaffSettings.showReports;
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return Main.TERRAMCNET_STAFF_CATEGORY;
    }
}
